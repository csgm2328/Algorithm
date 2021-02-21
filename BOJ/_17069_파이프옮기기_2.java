import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP
//dp[i][j] = dp[i-1][j-1] + 
public class _17069_파이프옮기기_2 {
	static int N; // 16에서 32로 늘음
	static int[][] arr;
	static int[][] dp;
	static long cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new int[N][N];
		dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

			}
		}
		// dp[N][N]에 올수 있는 방향의 값 더하면 됨
		// shape이 매개변수로 전달되질 않으니까 어떻게 할질 모르겠다
		
		int shape = 0;
		for(int i =0; i< N; i++) 
			dp[0][i] = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if (arr[i][j] == 0 && dp[i][j] != 0) { 
					if (shape != 1 && j + 1 < N && arr[i][j + 1] == 0) {
						dp[i][j + 1]++;
						shape = 0;
					}
					if (shape != 0 && j + 1 < N && arr[i + 1][j] == 0) {
						dp[i + 1][j]++;
						shape = 1;
					}
					if (i + 1 < N && j + 1 < N && arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1] == 0) {
						dp[i + 1][j + 1]++;
						shape = 2;
					}
				}
			}
		}

		System.out.print(dp[N - 1][N - 1]);
	}

	// 3에서 4로 증가할때 1+3
	static void dfs(int cu_row, int cu_col, int shape, int start) {
		// 도착
		if (cu_row == start - 1 && cu_col == start - 1) {
			cnt++;
			return;
		}
		if (shape != 1 && cu_col + 1 < start && arr[cu_row][cu_col + 1] == 0)
			dfs(cu_row, cu_col + 1, 0, start);
		if (shape != 0 && cu_row + 1 < start && arr[cu_row + 1][cu_col] == 0)
			dfs(cu_row + 1, cu_col, 1, start);
		// 세군데 의 합이 0이면 대각선 가능
		if (cu_row + 1 < start & cu_col + 1 < start
				&& arr[cu_row + 1][cu_col] + arr[cu_row][cu_col + 1] + arr[cu_row + 1][cu_col + 1] == 0)
			dfs(cu_row + 1, cu_col + 1, 2, start);
	}
}
