
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _4963_섬의개수 {
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 }; // 8방탐색
	static int[] dc = { 0, 0, -1, 1, -1, 1, 1, -1 };

	static class xy {
		int x, y;

		public xy(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		boolean flag = true;
		while (flag) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0) {
				flag = false;
				break;
			}

			int[][] arr = new int[h][w];
			boolean[][] visited = new boolean[h][w];
			int sum = 0;

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Queue<xy> q = new LinkedList<xy>();
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (arr[i][j] == 1 && !visited[i][j]) {
						sum++;

						visited[i][j] = true;
						q.offer(new xy(i, j));
						// bfs
						while (!q.isEmpty()) {
							int cu_row = q.peek().x;
							int cu_col = q.peek().y;
							q.poll();

							for (int dir = 0; dir < 8; dir++) {
								int NextR = cu_row + dr[dir];
								int NextC = cu_col + dc[dir];

								int cnt = 0;
								if (NextR >= 0 && NextR < h && NextC >= 0 && NextC < w) {// 범위 안이면
									if (arr[NextR][NextC] == 1 && !visited[NextR][NextC]) {// 미방문이고
										visited[NextR][NextC] = true;
										q.offer(new xy(NextR, NextC));
									}
								}
							}
						}
					}
				}
			}
			sb.append(sum + "\n");
		}
		System.out.print(sb.toString());
	}
}
