package BOJ.N과M;

import java.io.*;
import java.util.*;

//순열
public class _15649_N과M_1 {
	static StringBuilder sb = new StringBuilder();
	static int N, M, save[], check[];
	
	public static void permutation(int cnt) {
		if(cnt == M) {
			for(int i = 0; i<cnt; ++i)
				sb.append(save[i]).append(' ');
			sb.append('\n');
            return;
		}
		
		for(int i = 1; i<=N; ++i) {
			if(check[i] == 1) continue;
			check[i] = 1;
			save[cnt] = i;
			permutation(cnt+1);
			check[i] = 0;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		check = new int[9];
		save = new int[9];
		
		permutation(0);
		System.out.print(sb);
	}
}