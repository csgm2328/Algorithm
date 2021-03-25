import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2차원 중복조합
//근데 N <=1000 이니까 dp로해야함
public class _1149_RGB거리 {
	static int N;
	static int[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// global init
		N = Integer.parseInt(br.readLine());

		arr = new int[N][3];
		visited = new boolean[N][3];
		int[][] dp = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		// 처음은 그대로
		dp[0][0] = arr[0][0];
		dp[0][1] = arr[0][1];
		dp[0][2] = arr[0][2];
		// 두번째부터 전꺼 세개중에 겹치는 거 빼고 작은거를 골라 더해줌
		// 세가지를 유지하면서 감
		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0]; //이번칸 빨강으로 칠하려면 전칸 노랑,파랑중 작은거 고름
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
			dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + arr[i][2];
		}

//		combination(0,-1, -1, 0);
		System.out.print(Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2])));
	}

	// 중복조합
	static int Min = Integer.MAX_VALUE;
	private static void combination(int cnt, int row, int col, int sum) {
		if (cnt == N) {
			if (Min > sum)
				Min = sum;
			return;
		}
		for (int i = row + 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				if (col != j) {
					visited[i][j] = true;
					combination(cnt + 1, i, j, sum + arr[i][j]);
					visited[i][j] = false;
				}
			}
		}
	}
}
