import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// dp로 해당칸에서 대각앞,위,왼쪽을 보고 가장 작은값+1이 그릴 수 있는 정사각형 길이를 저장
public class _1915_가장_큰_정사각형 {
	static BufferedReader input;
//	static StringBuilder output;
	static StringTokenizer st;
	static int N, M;
	static int dr[] = { -1, 0, -1 }; // 대각, 왼, 위
	static int dc[] = { -1, -1, 0 };

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(input.readLine());
//		output = = new StringBuilder();
		// global init
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char[][] arr = new char[N][M]; // N:1 ~ 200

		for(int i = 0; i < N; i ++)
			arr[i] = input.readLine().toCharArray(); //charArray 입력 - 700ms????????

		int[][] dp = new int[N][M]; // 해당칸에서 그릴 수 있는 정사각형의 넓이
		// 0행 0열 셋팅 --> 실수포인트: 여기서 max_size 체크안했다
		int max_size = 0; // 돌면서 가장 큰 사이즈 저장
		// 최적화 : 초기셋팅 없이 한번에 했는데 888+30ms?????????
//		for (int i = 0; i < N; i++) {
//			dp[i][0] = arr[i][0];
//			if (max_size < dp[i][0])
//				max_size = dp[i][0];
//		}
//			
//		for (int j = 0; j < M; j++) {
//			dp[0][j] = arr[0][j];
//			if (max_size < dp[0][j])
//				max_size = dp[0][j];
//		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == '0')
					continue;
				int diag =0, left =0, up =0; // 대각, 왼쪽, 위
				if(isInBoundary(i + dr[0], j + dc[0])) //경계 체크 나가면 그냥 0으로
					diag = dp[i + dr[0]][j + dc[0]];
				if(isInBoundary(i + dr[1], j + dc[1]))
					left = dp[i + dr[1]][j + dc[1]];
				if(isInBoundary(i + dr[2], j + dc[2]))
					up = dp[i + dr[2]][j + dc[2]];

//				if (diag == left && left == up) // 세 곳이 다같다면 +1 >> min으로 하면 필요없음
//					dp[i][j] = diag + 1;
				// 실수포인트: 큰게 아니라 작은걸 찾은다음에 +1해야한다
				int min = Math.min(diag, Math.min(left, up));
				dp[i][j] = min + 1;
				// 가장 큰 사이즈 저장
				if (max_size < dp[i][j])
					max_size = dp[i][j];
			}
		}
//		for (int[] x : dp) {
//			System.out.println(Arrays.toString(x));
//		}
		System.out.print(max_size * max_size);
	}

	private static int checkRect(int[][] arr, int sr, int sc) {
		// 정사각형인지 체크
		int size = 0, sero = 1;
		out: for (int i = sr; i < N; i++) {
			int garo = 1;
			for (int j = sc; j < M; j++) {
				if (arr[i][j] == 0)
					break out;
				garo++;
			}
			sero++;
			size = Math.min(garo, sero);
		}
		return size * size;
	}

	private static boolean isInBoundary(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
