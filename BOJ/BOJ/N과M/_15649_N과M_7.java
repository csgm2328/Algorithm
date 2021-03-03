package BOJ.N과M;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//중복순열 check없음
public class _15649_N과M_7 {
	static int N, M;
	static int[] arr, save;
	static StringBuilder sb = new StringBuilder();
	
	static void combination(int cnt) {
		if(M == cnt) {
			for(int i =0; i< M; i++) {
				sb.append(save[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 0; i< N; i++) {
			save[cnt] = arr[i];
			combination(cnt+1);
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
		combination(0);
		System.out.print(sb.toString());
	}
}

