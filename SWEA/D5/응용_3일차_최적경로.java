package D5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//60m
//문제 잘보자 홈 좌표가 두번째다 
public class 응용_3일차_최적경로 {
	static Pair[] arr, save;
	static Pair company, home;
	static boolean[] check;
	static int N; // 고객 수
	static int min = 999999;

	static class Pair { // default 선언은 패키지 내 접근 가능해서 스태틱으로 선언해야함
		int val;
		int idx;

		public int first() {
			return val;
		}

		public int second() {
			return idx;
		}

		public Pair(int val, int idx) {
			this.val = val;
			this.idx = idx;
		}
	}

	static int calc_distance(Pair p1, Pair p2) {
		int x1 = p1.first();
		int y1 = p1.second();
		int x2 = p2.first();
		int y2 = p2.second();
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	static void permutation(int idx) {
		if (idx == N) {
			int distance = 0;
			distance += calc_distance(company, arr[0]);

			for (int i = 0; i < save.length - 1; i++) {
				distance = distance + calc_distance(arr[i], arr[i + 1]);
			}
			distance = distance + calc_distance(arr[arr.length - 1], home);
			if (min > distance)
				min = distance;
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (check[i])
				continue;
			save[idx] = arr[i];
			check[i] = true;
			permutation(idx + 1);
			check[i] = false;
		}
	}
	
	//최적화 - 갱신하면서 가기
	static void permutation_optim(Pair current, int distance, int idx) {
		if (distance >= min ){
			return;
		}
		if(idx == N) {
			distance += calc_distance(current, home);
			if(min > distance) min = distance;
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (check[i])
				continue;
			
			check[i] =true;
			permutation_optim(arr[i], distance + calc_distance(current, arr[i]), idx + 1);
			check[i] = false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 출력 최적화 -18ms

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 고객 수
			arr = new Pair[N];
			save = new Pair[N];
			check = new boolean[N];
			min = 999999; // 초기화 까먹지말고

			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			company = new Pair(x, y);

			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			home = new Pair(x, y);

			for (int i = 0; i < N; i++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				arr[i] = new Pair(x, y);
			}

//			permutation(0);
			permutation_optim(company, 0, 0);
			sb.append("#" + tc + " " + min + "\n");
		}
		System.out.println(sb.toString());
	}
}
