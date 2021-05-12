import java.io.*;
import java.util.*;

// 아맞다우산
// bfs + bitMasking
// 챙길거를 0~4로 표시해놓고 3차원 방문 체크로 구분하면서 탐색한다
public class _17244_아맞다우산 {
	static int N, M;

	static class Move {
		int r, c, cnt, state;

		public Move(int r, int c, int cnt, int state) {
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
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];

		int carryCnt = 0;
		int[] start = new int[2];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'X') {
					map[i][j] = (char) (carryCnt++ + '0');
				} else if (map[i][j] == 'S')
					start = new int[] { i, j };
			}
		}
		boolean[][][] visited = new boolean[N][M][1 << carryCnt];// 최대 2^5
		bfs(map, visited, start, carryCnt);
		System.out.println(ans);
	}

	static int ans;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static void bfs(char[][] map, boolean[][][] visited, int[] start, int carryCnt) {
		Queue<Move> q = new LinkedList<Move>();
		q.offer(new Move(start[0], start[1], 0, 0));
		visited[start[0]][start[1]][0] = true;
		while (!q.isEmpty()) {
			Move cur = q.poll();
			// 다 청소했는지 확인
			if (cur.state == (1<<carryCnt) -1 && map[cur.r][cur.c] == 'E')  {
				ans = cur.cnt;
				break;
			}
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				// 방문안했고
				if (isInBoundary(nr, nc) && !visited[nr][nc][cur.state] && map[nr][nc] != '#') {
					if ('0' <= map[nr][nc] && map[nr][nc] <= '4') {
						int newState = cur.state | (int) Math.pow(2, (map[nr][nc] - '0'));
						q.offer(new Move(nr, nc, cur.cnt + 1, newState));
						visited[nr][nc][newState] = true;
					} else {
						q.offer(new Move(nr, nc, cur.cnt + 1, cur.state));
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
