import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 빙산
// 녹음: 4방 탐색으로 바다 인접 -1
// 0이 안된 지역 세고, 그 지점으로 부터 시작해서 bfs그룹핑 하고
// bfs로 그룹핑한 지역의 수가 빙산지역 수 보다 작으면 그때 분리 된 시간이다

public class _2573_빙산 {
	static int N, M; // 3 ~ 300
	static Ice[][] map;

	static class Ice {
		int height;
		boolean melt;

		public Ice(int height) {
			this.height = height;
			this.melt = false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new Ice[N][M];
		save = new int[2];
		int total = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = new Ice(Integer.parseInt(st.nextToken()));
				if (map[i][j].height > 0) {
					total++;
					save[0] = i;
					save[1] = j;
				}
			}
		}
		//원래부터 분리되어있는지 체크
		int grouping = bfs(save[0], save[1]); // 빙산 덩어리 수
		if (total > grouping) { // 한 덩어리 수가 모자라면 분리된거
			System.out.println(0);
			System.exit(0);
		}
		int year = 0;
		while (true) {
			year++;
			total = melt(); // 안녹은 수
			if (total == 0) { // 한덩어리인채로 종료
				year = 0;
				break;
			}
			grouping = bfs(save[0], save[1]); // 빙산 덩어리 수
			if (total > grouping) // 덩어리 수가 모자라면 분리된거
				break;
		}
		System.out.println(year);
	}

	private static int bfs(int r, int c) {
		int cnt = 1;
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { r, c });
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur[0] + dr[dir];
				int nc = cur[1] + dc[dir];
				if (map[nr][nc].height == 0 || visited[nr][nc])
					continue;
				else {
					q.offer(new int[] { nr, nc });
					visited[nr][nc] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}

	static int dr[] = { 0, 0, 1, -1 };
	static int dc[] = { 1, -1, 0, 0 };
	static int[] save;

	private static int melt() {
		for (int i = 1; i < N - 1; i++)
			for (int j = 1; j < M - 1; j++)
				map[i][j].melt = false; //방금 녹은 여부 초기화 >>문제 오류때문에 1시간 낭비 개빡침

		int total = 0; // 남을 빙산의 수
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				if (map[i][j].height > 0) {
					int sea = 0;
					for (int dir = 0; dir < 4; dir++) {
						int nr = i + dr[dir];
						int nc = j + dc[dir];
						if (map[nr][nc].height == 0 && !map[nr][nc].melt) // 방금 지워진거는 패스
							sea++;
					}
					map[i][j].height -= sea;
					if (map[i][j].height > 0) { // 안녹은거
						total++;
						save[0] = i; // bfs 시작포인트
						save[1] = j;
					} else {
						map[i][j].height = 0;
						map[i][j].melt = true;
					}

				}
			}
		}
		return total;
	}
}
