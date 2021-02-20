package BOJ.순열과조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//순열로 중복 조합짜기
//순열 + 비내림차순 체크
//조합보다 2배느림
public class _15649_N과M_4 {

	static int N, M;
	static int[] arr, save;
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();

	static void permutation(int idx) {
		if (idx == M) {
			boolean flag = true; // 출력할지 결정
			// 비 내림차순인지 검사
			for (int i = 1; i < M; i++) {
				if (save[i - 1] > save[i]) { // 앞에꺼가 더 크면 패스
					flag = false;
					break;
				}
			}
			if (flag) {
				for (int i = 0; i < M; i++) {
					sb.append(save[i] + " ");
				}
				sb.append("\n"); // 마지막꺼 넣어주고
			}
			return; //위치 실수!!!!!!!!!!!!!!!!!!!!!!!!
		}
		// 중복순열
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
		check = new boolean[N];

		permutation(0);
		System.out.println(sb.toString());
	}
}


