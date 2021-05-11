import java.io.*;
import java.util.*;

import javax.naming.directory.DirContext;

// 로봇 청소기
// bfs + bitMasking
// 더러운 곳을 0~9로 표시해놓고 3차원 방문 체크로 구분하면서 탐색한다
// 최대 10개니까 3차원이 2^10개의 경우를 표시해야하고
// 2^0부터 or연산으로 state갱신
// 로봇청소기의 state가 2^10-1일때 끝
public class _4991_로봇청소기 {
	static int N, M;
	static StringBuilder sb = new StringBuilder();

	static class Vaccum {
		int r, c, cnt, state;

		public Vaccum(int r, int c, int cnt, int state) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.state = state;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		while (true) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0)
				break;
			char[][] map = new char[N][M];

			int dirtyCnt = 0;
			int[] start = new int[2];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					if (map[i][j] == '*') {
						map[i][j] = (char) (dirtyCnt + '0');
						dirtyCnt++;
					} else if (map[i][j] == 'o')
						start = new int[] { i, j };
				}
			}
			boolean[][][] visited = new boolean[N][M][1 << dirtyCnt];// 최대 2^10
			ans = -1;
			bfs(map, visited, start, dirtyCnt);
			sb.append(ans + "\n");
		}
		System.out.println(sb.toString());
	}
	static int ans;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static void bfs(char[][] map, boolean[][][] visited, int[] start, int dirtyCnt) {
		Queue<Vaccum> q = new LinkedList<Vaccum>();
		q.offer(new Vaccum(start[0], start[1], 0, 0));
		visited[start[0]][start[1]][0] = true;
		while (!q.isEmpty()) {
			Vaccum cur = q.poll();
			// 다 청소했는지 확인
			if (cur.state == (1 << dirtyCnt) - 1) {
				ans = cur.cnt;
				break;
			}
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				// 방문안했고
				if (isInBoundary(nr, nc) && !visited[nr][nc][cur.state] && map[nr][nc] != 'x') {
					if ('0' <= map[nr][nc] && map[nr][nc] <= '9') {
						int newState = cur.state | (int) Math.pow(2,(map[nr][nc] - '0'));
						q.offer(new Vaccum(nr, nc, cur.cnt + 1, newState));
						visited[nr][nc][newState] = true;
					}
					else {
						q.offer(new Vaccum(nr, nc, cur.cnt+1, cur.state));
						visited[nr][nc][cur.state] = true;
					}
				}
			}
		} // end while
	}

	private static boolean isInBoundary(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
