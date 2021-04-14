import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 1h 30m
 * BFS + Bitmasking
 * 열쇠 보유상태를 포함한 3차원 visited
 * */
public class _1194_달이차오른다_가자 {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M;
	static char[][] arr;
	static boolean[][][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static class xyc {
		int r, c, cnt, key_state;

		public xyc(int r, int c, int cnt, int key_state) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.key_state = key_state;
		}
	}

	public static void main(String[] args) throws IOException {
		// global init
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new char[N][M];
		visited = new boolean[N][M][64]; // key 종류 6개
		int[] start = new int[2];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == '0') {
					start[0] = i;
					start[1] = j;
				}
			}
		}
		bfs(start);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	static int ans = Integer.MAX_VALUE;

	private static void bfs(int[] start) {
		Queue<xyc> q = new LinkedList<xyc>();
		q.offer(new xyc(start[0], start[1], 0, 0));
		visited[start[0]][start[1]][0] = true;

		while (!q.isEmpty()) {
			xyc cur = q.poll();
			if (arr[cur.r][cur.c] == '1') { // 종료 조건
//				ans = Math.min(ans, cur.cnt);
				ans = cur.cnt;
				return;
			}

			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				if (isInBoundary(nr, nc) && !visited[nr][nc][cur.key_state] && arr[nr][nc] != '#') {
					// 키 찾음 근데 이미 그 키있으면 패스--> 실수포인트!! 패스하는게 아니라 new_state가 터지므로 원래의 상태를 푸시해야함
					if ('a' <= arr[nr][nc] && arr[nr][nc] <= 'f' && !AlreadyGet(nr, nc, cur.key_state)) {
						int new_state = (int) (cur.key_state + Math.pow(2, 'f' - arr[nr][nc]));
						visited[nr][nc][new_state] = true;
						q.offer(new xyc(nr, nc, cur.cnt + 1, new_state));
					} 
					
					else if ('A' <= arr[nr][nc] && arr[nr][nc] <= 'F') { // 문 만남
						if (Openable(nr, nc, cur.key_state)) { 
							visited[nr][nc][cur.key_state] = true;
							q.offer(new xyc(nr, nc, cur.cnt + 1, cur.key_state));
						} else
							continue;
					}
					
					else { //. 이나 0 이나 1
						visited[nr][nc][cur.key_state] = true;
						q.offer(new xyc(nr, nc, cur.cnt + 1, cur.key_state));
					}
				}
			}
		} // end while

	}
	private static boolean AlreadyGet(int nr, int nc, int key_state) {
		// 이미있는 키를 찾으러 왔다면
		int new_key = 'f' - arr[nr][nc];
		if ((key_state & (1 << new_key)) != 0)
			return true;
		return false;
	}	
	
	private static boolean Openable(int nr, int nc, int key_state) {
		// 그 문에 해당하는 키가 있는지 bitmasking check
		int door = 'F' - arr[nr][nc];
		if ((key_state & (1 << door)) != 0)
			return true;
		return false;
	}

	private static boolean isInBoundary(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
