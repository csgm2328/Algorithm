import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _12865_평범한배낭 {
	static BufferedReader input;
//	static StringBuilder output;
	static StringTokenizer st;
	static int N, K; // 물건수, 가능무게

	public static void main(String[] args) throws NumberFormatException, IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(input.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N + 1][];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(input.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			arr[i] = new int[] { w, v };
		}
		int[] dp1 = new int[K+1]; 
		//1차원으로 할때 앞에서부터 채우면 같은 물건을 또 고려하게 되고
		// 뒤에서부터 채우면 N번 돌면 2차원과 똑같다
		// 1차원으로 할때 앞에서부터 채우면 같은 물건을 또 고려하게 되고
		// 뒤에서부터 채우면 N번 돌면 2차원과 똑같다
		for (int i = 1; i <= N; i++) { // 물건종류: 1~ 100
			for (int w = K; w >=1; w--) { //중복사용을 막기 위해 뒤에서부터 채운다
				int weight = arr[i][0];
				int value = arr[i][1];
				if (w - weight >= 0) { // weight(5)이면 [j-weight]위치꺼에 더함
					if (dp1[w - weight] + value > dp1[w]) {
						dp1[w] = dp1[w - weight] + value;
					} 
				}
			}
		}
//		int[][] dp = new int[N + 1][K + 1]; // 0행,0열 초기화하려고
//
//		for (int i = 1; i <= N; i++) { //물건종류: 1~ 100
//			for (int j = 1; j <= K; j++) { //배낭무게: 1 ~ 10만
//				// 1행은 1번 물건을 고를지 말지 결정
//				// 2행은 1행을 보면서 2번 물건 고를지말지 결정
//				// j: 열은 무게 0 ~ K
//				int weight = arr[i][0];
//				int value = arr[i][1];
//				if (j - weight >= 0) { // weight(5)이면 [j-weight]위치꺼에 더함
//					dp[i][j] = Math.max(dp[i - 1][j - weight] + value, dp[i - 1][j]);
//				} else
//					dp[i][j] = dp[i - 1][j];
//			}
//		}
//		for (int[] x : dp)
//			System.out.println(Arrays.toString(x));
//		System.out.println(dp[N][K]);
		System.out.println(dp1[K]);
	}
}
