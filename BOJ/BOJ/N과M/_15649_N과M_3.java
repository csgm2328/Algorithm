package BOJ.N과M;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//중복순열 check 없음
public class _15649_N과M_3 {

	static int N, M;
	static int[] save;
	static StringBuilder sb = new StringBuilder();

	static void permutation(int idx) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(save[i] + " ");
			}
			sb.append("\n"); // 마지막꺼 넣어주고
			return; 
		}
		
		for (int i = 0; i < N; i++) {
			save[idx] = i + 1;
			permutation(idx + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bw.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		save = new int[M];

		permutation(0);
		System.out.println(sb.toString());
	}
}
