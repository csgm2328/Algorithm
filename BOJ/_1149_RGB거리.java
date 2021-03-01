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
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// global init
		N = Integer.parseInt(br.readLine());

		arr = new int[N][3];
		visited = new boolean[N][3];
		int[][] dp = new int[N][3];
		int sum = 0;
		int col = -1;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int Min = Integer.MAX_VALUE;
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (i == 0 && Min > arr[i][j]) {
					Min = arr[i][j];
				}
			}
//			dp[0] = Min;
		}
		dp[0][0] = arr[0][0];
		dp[0][1] = arr[0][1];
		dp[0][2] = arr[0][2];

		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2])+ arr[i][1] ;
			dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0])+ arr[i][2];
		}

//		combination(0,-1, -1, 0);
		int Min = Integer.MAX_VALUE;
		for(int i =0; i< 3; i++) {
			if(Min > dp[N-1][i])
				Min = dp[N-1][i];
		}
		System.out.print(Min);
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
