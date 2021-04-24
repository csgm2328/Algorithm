package SW_TEST;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2112_보호필름 {
	static int D, W, K;
	static int[][] arr;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[D][W];

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++)
					arr[i][j] = Integer.parseInt(st.nextToken());
			}
			ans = Integer.MAX_VALUE;
			dfs(0, 0);
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int idx, int changeCnt) {
		if (changeCnt >= ans) { // 현재답 이상 바꾼건 볼 필요없음 (>= 처리로 -200ms)
			return;
		}
		if (idx == D) {
			if (isPass())
				ans = Math.min(ans, changeCnt);
			return;
		}
		

		// 행 선택해서 (원래, A, B) 세 갈래 길로

		// 원래상태저장했다가 끝날때 복구
		int[] origin = new int[W];
		for (int i = 0; i < W; i++) //copy[][]배열로 대신 -30ms
			origin[i] = arr[idx][i];

		dfs(idx + 1, changeCnt);

		for (int i = 0; i < W; i++) //fills -30ms
			arr[idx][i] = 0;
		dfs(idx + 1, changeCnt + 1);

		for (int i = 0; i < W; i++)
			arr[idx][i] = 1;
		dfs(idx + 1, changeCnt + 1);

		// 복구
		for (int i = 0; i < W; i++)
			arr[idx][i] = origin[i];
	}

	private static boolean isPass() {
		for (int j = 0; j < W; j++) {
			int cnt = 1;
			boolean flag = false;
			for (int i = 1; i < D; i++) {
				if (arr[i - 1][j] == arr[i][j]) {
					cnt++;
//					if (cnt >= K) { //실수포인트!! == 말고 >= 해야 K==1일때 체크된다
//						flag = true;
//						break;
//					}
				} else
					cnt = 1;
				if (cnt >= K) { //실수포인트!! k=1일떄 CNT++밖에잇어야 체크된다
					flag = true;
					break;
				}
			}
			if (!flag)
				return false;
		}
		return true;
	}
}
