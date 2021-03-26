import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//말은 나이트 이동하며 벽뛰어넘기가능, 도착한곳이 벽이면 안됨
//원숭이는 K번만 나이트 이동, 그외에는 4방
// K의기회를 나중에 써야할 수도 있다.
public class _1600_말이_되고픈_원숭이 {
	static BufferedReader br;
	static StringBuilder output;
	static StringTokenizer st;
	static int K, N, M;
	static int[] knightR = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] knightC = { -2, -1, 1, 2, -2, -1, 1, 2 };
	static int[] dr = { 0, -1, 1, 0 };
	static int[] dc = { 1, 0, 0, -1 };
	static int[][] arr, visited;
	private static int MIN = -1;

	static class xyc {
		int r, c, cnt, chance;

		public xyc(int r, int c, int cnt, int chance) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.chance = chance;
		}
	}

	public static void main(String[] args) throws IOException {

		// global init
		br = new BufferedReader(new InputStreamReader(System.in));
		output = new StringBuilder();
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 1 ~ 1000
		N = Integer.parseInt(st.nextToken()); // 1 ~ 1000
		arr = new int[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int[] x : visited)
			Arrays.fill(x, Integer.MAX_VALUE);
		bfs();
		System.out.print(MIN);
	}

	private static void bfs() {
		long stt = System.currentTimeMillis();
		// bfs로 먼저 0의 면적들에다가 면적의 크기를 써놓는다
		// 그래서 벽이 4방탐색한번에 경로를 알아낼 수 있도록
		Queue<xyc> q = new LinkedList<xyc>();
		q.offer(new xyc(0, 0, 0, 0));
		visited[0][0] = 0; // 기회 K번 사용 가능, 더 많은 기회 사용가능할때 방문 가능

		int forcnt = 0;
		int offercnt = 0;
		while (!q.isEmpty()) {
			xyc cur = q.poll();
			if (cur.r == N - 1 && cur.c == M - 1) {
				MIN = cur.cnt;
				break;
			}
			// 4방탐색을 먼저해줘야 중복이 줄어듬 - 40ms
			for (int dir = 0; dir < 4; dir++) {
				forcnt++;
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				if (isInBoundary(nr, nc) && arr[nr][nc] != 1) {
					if (visited[nr][nc] > cur.chance) {
						q.offer(new xyc(nr, nc, cur.cnt + 1, cur.chance));
						offercnt++;
						visited[nr][nc] = cur.chance;
					}
				}
			}
			
			if (cur.chance < K) { // 말처럼 이동 가능
				for (int dir = 0; dir < 8; dir++) {
					forcnt++;
					int nr = cur.r + knightR[dir];
					int nc = cur.c + knightC[dir];
					if (isInBoundary(nr, nc) && arr[nr][nc] != 1) {
						if (visited[nr][nc] > cur.chance+1) { 
							//시간초과 포인트 --> 말이동 때는 말 이동이 의미있는지 확인해야되는데
							//벽부수기와 다른 포인트
							q.offer(new xyc(nr, nc, cur.cnt + 1, cur.chance + 1));
							offercnt++;
							visited[nr][nc] = cur.chance + 1; // 몇번 찬스가 있을때 온 경로임을 표시
							// 벽부수기랑 달리 같은 부수기에서 왔어도 방문하게 해줘야함 그래서 -1
						}
					}
				}
			}
		} // end while
		System.out.println("\nfor문: " + forcnt);
		System.out.println("print전 큐 남은거: " + q.size());
		System.out.println("큐에 집어넣은 횟수: " + offercnt);
////		System.out.println(("종료:" + (System.currentTimeMillis() - stt) / 1000.0));
	}

	private static boolean isInBoundary(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
