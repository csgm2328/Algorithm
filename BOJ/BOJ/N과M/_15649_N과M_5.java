package BOJ.N과M;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//비트마스킹으로 순열짜기
public class _15649_N과M_5 {
	static int N, M;
	static int[] arr, save;
	static StringBuilder sb = new StringBuilder();
	
	static void permutation(int idx, int start, int flag) {
		if (idx == M) {
			for(int i =0; i< M; i++) {
				sb.append(save[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 0; i < N; i++) { //전에 있는 것도 뽑아야하니까
			if ((flag & 1 << i) != 0)
				continue;
			save[idx] = arr[i];
			permutation(idx + 1, i+1, flag | 1 << i);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bw.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		save = new int[M];

		st = new StringTokenizer(bw.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		permutation(0,0, 0);
		System.out.println(sb.toString());
	}
}

