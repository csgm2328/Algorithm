package BOJ.순열과조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//조합
//start를 이용한다면 check도 필요없음
//입력을 정렬하고 시작하면
//조합의 결과는 정렬됨
public class _15649_N과M_6 {
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
			combination(cnt+1,i+1); //i+1부터
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

