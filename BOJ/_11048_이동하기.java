import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class _11048_이동하기 {
	static BufferedReader input;
	static StringBuilder output;
	static StringTokenizer st;
	static int N, M;
	static int[] dr = { 1, 0, 1 };
	static int[] dc = { 0, 1, 1 };

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		output = new StringBuilder();
		// global init
		st = new StringTokenizer(input.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[N][M];
		dp[0][0] = arr[0][0];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int dir = 0; dir < 3; dir++) {
					int nr = i + dr[dir];
					int nc = j + dc[dir];
					if (isInBoundary(nr, nc) && dp[nr][nc] < dp[i][j] + arr[nr][nc])
						dp[nr][nc] = dp[i][j] + arr[nr][nc];
				}
			}
		}
		System.out.print(dp[N - 1][M - 1]);

	}

	private static boolean isInBoundary(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
