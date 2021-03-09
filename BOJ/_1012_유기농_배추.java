import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//유기농 배추
public class _1012_유기농_배추 {
	static int T, R, C, K;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int cnt;
	static StringBuilder sb = new StringBuilder();

	static class rc { //class 사용 120 - 4ms
		int r;
		int c;

		public rc(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// global init
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken()); // 가로
			C = Integer.parseInt(st.nextToken()); // 세로
			K = Integer.parseInt(st.nextToken());

			arr = new int[R][C];
			visited = new boolean[R][C];
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				arr[x][y] = 1;
			}

			int count = 0;
			for (int a = 0; a < R; a++) {
				for (int b = 0; b < C; b++) {
					if (!visited[a][b] && arr[a][b] == 1) {
						count++;
						visited[a][b] = true;
						bfs(a, b);
					}
				}
			}
			
			sb.append(count + "\n");
		}
		System.out.print(sb.toString());
	}

	private static void bfs(int a, int b) {
		Queue<rc> queue = new LinkedList<>();
		queue.offer(new rc( a, b ));

		while (!queue.isEmpty()) {
			rc cur = queue.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];

				if (nr >= 0 && nc >= 0 && nr < R && nc < C) {
					if (arr[nr][nc] == 1 && !visited[nr][nc]) {
						queue.offer(new rc( nr, nc ));
						visited[nr][nc] = true;
					}
				}
			}
		}
	}
}
