package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class 한빈이의_Spot_Mart {
	static boolean[] check;
	static int[] arr;
	static int r = 2, N, M; // 두 봉지
	static StringBuilder sb = new StringBuilder();
	static int max= -1;
	
	static void permutation(int cnt, int start) {
		if(r== cnt) {
			int sum =0;
			for(int i = 0; i< N; i++) {
				if(check[i]) {
					sum += arr[i];
					if(sum > M) {
						return;
					}
				}
			}
			if(sum > max) max= sum;
			return;
		}
		
		for(int i= start; i< N; i++) {
			if(check[i]) continue;
			
			check[i] = true;
			permutation(cnt+1, i+1);
			check[i] = false;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N];
			check = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i =0; i< N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			max = -1;
//			permutation(0,0);	// 1500ms
			//두개 고정이니까 반복문으로 정렬하고 작고 큰거에서 가운데로 모이기 140ms
			Arrays.sort(arr);
			for(int i = 0, j = N-1; i<j;) {
				if(arr[i] + arr[j] > M)
					j--; //큰 수가 너무 컸다면
				else if(arr[i] + arr[j] == M){
					max = M;
					break;
				}
				else{
					if(arr[i] + arr[j] > max)
						max = arr[i] + arr[j];
					i++;
				}
				
			}
			sb.append("#" + tc + " " + max+ "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
