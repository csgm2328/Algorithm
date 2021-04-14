import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 구슬탈출 2
 * 3day
 * 0. 빨간공과 파란공을 따로 생각하지 않는다 
 * 1. next: 빨간공 파란공 상태를 동시에 가지고있는 구슬 class
 * 2. 방문체크: 빨간공과 파란공의 위치를 동시에 표현한 4차원 visit배열이다
 * 3. 이동 : 이동 방향에 앞서있는 공을 먼저 움직여야 한다.
 * 4. 직진: 공 표시를 지우고 두 공이 겹쳐지면 뒤에 온걸 한칸 뒤로
 * 5. 종료: 항상 poll() 직후에 종료 조건을 검사한다. 공들의 상태를 파란공 빨간공순으로 체크
 * 
 * */
public class _13460_구슬탈출2 {
	static int N, M;
	static char[][] arr;
	static boolean[][][][] visited;

	static class rc {
		int r, c;

		public rc(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static class Goosle {
		rc Red, Blue;
		int move;

		public Goosle() {
			move = 0;
		}
		public Goosle(rc red, rc blue, int move) {
			super();
			Red = red;
			Blue = blue;
			this.move = move;
		}
	}

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new char[N][M];
		Goosle goosle = new Goosle();
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 'R') {
					goosle.Red = new rc(i, j);
					arr[i][j] = '.'; // 판에서 공 표시를 지우고 위치로 겹침을 체크한다
				} else if (arr[i][j] == 'B') {
					goosle.Blue = new rc(i, j);
					arr[i][j] = '.';
				}
			}
		}
		visited = new boolean[N][M][N][M];
		bfs(goosle);
		System.out.println(ans);
	}

	static int ans = -1;

	private static void bfs(Goosle goosle) {
		Queue<Goosle> q = new LinkedList<Goosle>();
		q.offer(goosle);
		visited[goosle.Red.r][goosle.Red.c][goosle.Blue.r][goosle.Blue.c] = true;

		while (!q.isEmpty()) {
			Goosle cur = q.poll();
			// 탈출 조건
			// 못움직이는 상태는 자연스럽게 큐 비어진다
			if (cur.move > 10)
				return;
			if (arr[cur.Blue.r][cur.Blue.c] == 'O')
				continue; // 실패시 그만하는게 아니라 건너뜀
			if (arr[cur.Red.r][cur.Red.c] == 'O') {
				ans = cur.move;
				return;
			}

			for (int dir = 0; dir < 4; dir++) {
				Goosle next = Move(cur, dir); // 다음 방향으로 굴려본 구슬들의 상태는
				if (!visited[next.Red.r][next.Red.c][next.Blue.r][next.Blue.c]) { // 새로운 구슬상태라면
					visited[next.Red.r][next.Red.c][next.Blue.r][next.Blue.c] = true;
					next.move++;
					q.offer(next);
				}
			}
		}
	}

	// 공을 굴려보자
	private static Goosle Move(Goosle cur, int dir) {
		Goosle next = new Goosle(cur.Red, cur.Blue, cur.move);
		boolean redFlag = true;
		// 빨간공이 앞서있는지 파란공이 앞서있는지에 따라 굴리는 순서 바뀜
		rc check = straight(next, redFlag, next.Red.r, next.Red.c, dir);
		if (check.r + dr[dir] == next.Blue.r && check.c + dc[dir] == next.Blue.c)
			redFlag = false;

		for (int i = 0; i < 2; i++, redFlag = !redFlag) {
			if (redFlag)
				next.Red = straight(next, redFlag,  next.Red.r, next.Red.c, dir);
			else
				next.Blue = straight(next, redFlag, next.Blue.r, next.Blue.c , dir);
		}
		return next;
	}

	private static rc straight(Goosle next, boolean redFlag, int nr, int nc, int dir) {
		while (arr[nr][nc] == '.') {
			if (redFlag) { // 공 두개가 겹쳐지는 경우 체크
				if (nr == next.Blue.r && nc == next.Blue.c)
					break;
			} else {
				if (nr == next.Red.r && nc == next.Red.c)
					break;
			}
			nr += dr[dir];
			nc += dc[dir];
		}
		if (arr[nr][nc] == 'O')
			return new rc(nr, nc);
		else
			return new rc(nr - dr[dir], nc - dc[dir]);
	}
}
