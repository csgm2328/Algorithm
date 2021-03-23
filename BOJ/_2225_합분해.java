import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1개를 고를때는 다 한가지고
//2개를 골라 2를 만드는 방법은
//2개를 골라 1을 만드는 방법 + 1개를 골라 2를 만든 방법 
public class _2225_합분해 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//	static StringBuilder sb = new StringBuilder();
	static int N, K;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(input.readLine());
		// global init
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] dp = new int[N+1]; // N:1 ~ 200
		final int INF = 1000000000;

		dp[0] = 1; // 0을 만드는 방법은 0을 고르는 한가지 경우
		for (int i = 1; i <= K; i++) { //K개를 골라
			for (int j = 1; j <= N; j++) { //그 합이 N이 되는 경우는?
				dp[j] = (dp[j] + dp[j-1]) % INF; 
			}
		}
		System.out.print(dp[N]);
	}
}
