import java.io.*;
import java.util.*;

public class _1932_정수삼각형 {
	static int N;
	static int[][] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		int[][] dp = new int[N][N];
		
		for(int i =0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<=i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = arr[0][0];
		int max = -1;
		for(int i = 0; i< N-1; i++) {
			for(int j = 0; j<= i; j++) {
				//현재의 바로아래랑 아래의 오른쪽칸 채우기
				dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + arr[i+1][j]);
				dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + arr[i+1][j+1]);
			}
		}
		for(int i =0; i< N; i++)
			max = Math.max(dp[N-1][i], max);
		System.out.println(max);
	}
}
