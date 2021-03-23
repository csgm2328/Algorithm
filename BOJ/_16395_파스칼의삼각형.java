import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//n 30일때 1억넘는 수들 있음
public class _16395_파스칼의삼각형 {
	static BufferedReader input;
//	static StringBuilder output;
	static StringTokenizer st;
	static int N, K; //nCk
	public static void main(String[] args) throws NumberFormatException, IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(input.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N][N];
		
		dp[0][0] = 1; //0C0
		for (int i = 1; i < N; i++) { //i : 0 ~ N개에서
			dp[i][0] = 1; //처음
			for(int j = 1; j < i; j++) { //j : 1 ~ i-1개 고르는 경우의 수 k까지만 해도됨
				dp[i][j] = dp[i-1][j-1]+ dp[i-1][j];
			}
			dp[i][i] = 1; //끝
		}
//		
		for(int[] x: dp)
			System.out.println(Arrays.toString(x));
		System.out.println(dp[N-1][K-1]);
	}
}
