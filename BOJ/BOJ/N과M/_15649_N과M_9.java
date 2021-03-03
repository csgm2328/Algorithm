package BOJ.N과M;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//순열의 결과에서 중복된 수열 삭제
//before을 이용해서 전 호출 스택에서 저장한거랑 현재 저장될 것 비교
public class _15649_N과M_9 {
	static int N, M;
	static int[] arr = new int[10001];
	static ArrayList<Integer> input, dupl;
	static int[] save, check;
	static StringBuilder sb = new StringBuilder();

	public static void permutation(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < cnt; ++i)
				sb.append(save[i]).append(' ');
			sb.append('\n');

			return;
		}
		int before = 0; // 전에 껄 가지고 있음 중복 체크
		// 9 9 에서
		// 뒤에꺼 9가 before
		// 9 1 갈 때 앞에 9가 다 걸림
		
		for (int i = 0; i < input.size(); ++i) {
			if (check[i] == 1)
				continue;
			else if (check[i] == 0 && (i == 0 || before != input.get(i))) {
				before = input.get(i);
				check[i] = 1;
				save[cnt] = input.get(i);
				permutation(cnt + 1);
				check[i] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bw.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new ArrayList<Integer>();
		dupl = new ArrayList<Integer>();
		check = new int[N];
		save = new int[M];

		st = new StringTokenizer(bw.readLine());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken());
			input.add(x);
		}
//		for (int i = 0; i < N; i++) {
//			int x = Integer.parseInt(st.nextToken());
//			if (!input.contains(x))
//				input.add(x);
//			else {
//				if (!dupl.contains(x))
//					dupl.add(x); // 중복인 수 체크
//			}
//		}
		// 정렬한 후 표시
		Collections.sort(input);

		permutation(0);
//		// 없앤거 출력
//		for (int j = 0; j < dupl.size(); j++) {
//			for (int i = 0; i < M; i++) {
//				sb.append(dupl.get(j) + " ");
//			}
//			sb.append("\n");
//		}
		System.out.print(sb.toString());
	}
}
