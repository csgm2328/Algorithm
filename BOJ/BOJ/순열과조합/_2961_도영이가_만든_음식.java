package BOJ.순열과조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _2961_도영이가_만든_음식 {
	static int N, min = Integer.MAX_VALUE;
	static boolean[] check;
	static ArrayList<Pair> arr = new ArrayList<_2961_도영이가_만든_음식.Pair>();

	static class Pair {
		int sour;
		int bitter;

		public Pair(int sour, int bitter) {
			this.sour = sour;
			this.bitter = bitter;
		}
	}

	// check를 통해 인덱스끝까지 갔을때 거기서 계산
	static void powerset1(int idx) {
		if (idx == N) {
			int sum = 0;
			int cu_s = 1;
			int cu_b = 0;
			for (int i = 0; i < arr.size(); i++) {
				if (check[i]) {
					cu_s *= arr.get(i).sour;
					cu_b += arr.get(i).bitter;
				}
			}
			if (cu_s == 1 && cu_b == 0) { // 아무것도 안고른경우 뺴고
				return;
			}
			System.out.println(cu_s + " " + cu_b);
			sum = Math.abs(cu_s - cu_b);
			if (min > sum)
				min = sum;
			return;
		}
		check[idx] = true;
		powerset1(idx + 1);
		check[idx] = false;
		powerset1(idx + 1);
	}

	static void powerset(int idx, int cu_s, int cu_b) {
		if (idx == N) {// 인덱스 넘지않도록
			if (cu_s == 1 && cu_b == 0) { // 아무것도 안고른경우 뺴고
				return;
			}
			int sum = Math.abs(cu_s - cu_b);
			System.out.println(cu_s + " " + cu_b);
			if (min > sum) {
				min = sum;
			}
			return;
		}
		// 현재껄 고르는 경우
		powerset(idx + 1, cu_s * arr.get(idx).sour, cu_b + arr.get(idx).bitter);
		// 안고르는경우
		powerset(idx + 1, cu_s, cu_b);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bw.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bw.readLine());
			int sour = Integer.parseInt(st.nextToken());
			int bitter = Integer.parseInt(st.nextToken());
			arr.add(new Pair(sour, bitter));
		}
		check = new boolean[N];
		//for문과 비트마스킹
//		for (int i = 0; i < (1 << N); i++) {
//			int a = 1, b = 0;
//			for (int j = 0; j < N; j++) {
//				if ((i & (1 << j)) != 0) {
//					a *= arr.get(j).sour;
//					b += arr.get(j).bitter;
//				}
//			}
//			if (!(a == 1 && b == 0)) {
//				int sum = a - b;
//				if (sum < 0)
//					sum = -sum;
//				if (min > sum)
//					min = sum;
//			}
//		}
//		powerset(0, 1, 0);
		powerset1(0);
		System.out.println(min);
	}
}
