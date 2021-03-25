import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _2579_계단오르기 {
	static BufferedReader input;
	static StringBuilder output;
	static StringTokenizer st;
	static int N;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		output = new StringBuilder();
		// global init
		N = Integer.parseInt(input.readLine());
		int[] steps = new int[N + 1];
		for (int i = 1; i <= N; i++)
			steps[i] = Integer.parseInt(input.readLine());
		
		int[][] dp = new int[N + 1][2];

		dp[1][0] = steps[1]; // 1칸 오르기
		dp[1][1] = steps[1]; // 2칸 오르기 해줘야함

		//결국 짝수계단과 홀수계단이랑 가능한 경우가 같다
		for (int i = 2; i <= N; i++) {
			dp[i][0] = dp[i - 1][1] + steps[i];
			dp[i][1] = Math.max(dp[i - 2][0], dp[i - 2][1]) + steps[i];
		}
//		for(int[] x: dp)
//			System.out.println(Arrays.toString(x));
		System.out.print(Math.max(dp[N][0], dp[N][1]));

	}
}
