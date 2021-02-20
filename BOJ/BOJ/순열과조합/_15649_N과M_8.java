package BOJ.순열과조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//자기자신부터 시작해서 중복을 허용하는 조합
public class _15649_N과M_8 {
	static int N, M;
	static int[] arr, save;
	static StringBuilder sb = new StringBuilder();
	
	static void combination(int cnt, int start) {
		if(M == cnt) {
			for(int i =0; i< M; i++) {
				sb.append(save[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start; i< N; i++) {
			save[cnt] = arr[i];
			combination(cnt+1,i); //i부터 시작
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
		combination(0,0);
		System.out.println(sb.toString());
	}
}

