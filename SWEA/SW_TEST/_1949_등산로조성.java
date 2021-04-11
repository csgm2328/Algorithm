package SW_TEST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//구현 11:15 ~ 1:15
//딱 한번이라는 것도 까먹고
//visit 값으로 높이를 표시하면서 갔다가
//리턴하면 높이랑 crashable, crash 복구하거나
//visit은 체크만하고 높이를 인자로 가지고다니거나 해야하는데
//대충대충한 댓가는 시간낭비만 부른다

public class _1949_등산로조성 {
	static int[][] arr;
	static boolean[][] visited;
	static int N, K;
	static int Max = -1;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			visited = new boolean[N][N];

			List<int[]> bonguri = new ArrayList<int[]>();
			int max_bounguri = -1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					// 봉우리 체크
					if (max_bounguri < arr[i][j]) { // 크거나 같은거 저장
						max_bounguri = arr[i][j];
						bonguri.clear();
						bonguri.add(new int[] { i, j });
					} else if (max_bounguri == arr[i][j])
						bonguri.add(new int[] { i, j });
				}
			}

			for (int i = 0; i < bonguri.size(); i++) {
				int[] x = bonguri.get(i);
				dfs(x[0], x[1], true, K, 1, max_bounguri);
				visited[x[0]][x[1]] = false;
			}
			sb.append("#" + tc + " " + Max + "\n");
			Max = -1;
		} // end tc
		System.out.println(sb.toString());
	}

	private static void dfs(int i, int j, boolean crashable, int crash, int load_length, int height) {
		// 안깍아도 되면 그냥이동
		// 깍아야되면 깍아야되는 높이가 K보다 작거나 같은지 확인
		// 깍으면 다시 경로를 확인해야하므로 return할 때 방문미처리 & 파놓은 곳 복구

		// 그냥 크래쉬여부로만 체크하면 안되는게
		// 1에서 한번 더 파서 갔으면 0이라 이제 못파야되는데 지금 높이를 몰라서 팔수있으면 계쏙판다
		// 현재높이를 가지고다니자
		// 단 한번만 깎을 수 있다고 써있잖아 하

		visited[i][j] = true;

//		if (height == 0) { // 더이상 팔수 없음 0 제한없으니까 없어야함
//			if (Max < load_length) {
//				Max = load_length;
//			}
//			return;
//		}

		for (int dir = 0; dir < 4; dir++) {// 4방 탐색
			int NextR = i + dr[dir];
			int NextC = j + dc[dir];

			if (NextR >= 0 && NextR < N && NextC >= 0 && NextC < N && !visited[NextR][NextC]) {// 가장자리 배제했으므로 생략
				if (arr[NextR][NextC] < height) {
					dfs(NextR, NextC, crashable, crash, load_length + 1, arr[NextR][NextC]);
					visited[NextR][NextC] = false;
				} else if (crashable && arr[NextR][NextC] >= height && arr[NextR][NextC] - height + 1 <= crash) { // 크거나
					// 이렇게 지역변수로 남는 식을 짜면 다음 4방탐색에 영향을 미친다 height갱신으로 밑에 crash도이상해지고
					int diff = arr[NextR][NextC] - height;
//					height = height - (arr[NextR][NextC] - height) - 1; //-높이차이 -1
//					crashable = crashable - (arr[NextR][NextC] - height) - 1; //차이만큼 깍고 한칸더깍아야 갈수있음
					dfs(NextR, NextC, !crashable, crash - diff - 1, load_length + 1, height - 1);
					visited[NextR][NextC] = false;
				}
			}
		}

		// 갈 곳 없으면
		if (Max < load_length) {
			Max = load_length;
		}
	}
}
