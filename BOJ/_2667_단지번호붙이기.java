
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Collections;

public class _2667_단지번호붙이기 {
	static int[] dr = { -1, 1, 0, 0 }; // 8방탐색
	static int[] dc = { 0, 0, -1, 1 };

	static class xy {
		int x, y, cnt;

		public xy(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		List<Integer> town = new ArrayList<Integer>(); // 단지개수와 단지별 집개수

		for (int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			int j = 0;
			for (char c : line)
				arr[i][j++] += Character.getNumericValue(c);
		}
		int town_sum = 0;
		town.add(town_sum);

		Queue<xy> q = new LinkedList<xy>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					town_sum++;
					town.set(0, town_sum); // 단지 수

					visited[i][j] = true;
					q.offer(new xy(i, j, 0));
					// bfs
					int home = 0;
					while (!q.isEmpty()) {
						int cu_row = q.peek().x;
						int cu_col = q.peek().y;
						int cu_cnt = q.peek().cnt;
						home++;
						q.poll();

						for (int dir = 0; dir < 4; dir++) {
							int NextR = cu_row + dr[dir];
							int NextC = cu_col + dc[dir];

							int cnt = 0;
							if (NextR >= 0 && NextR < N && NextC >= 0 && NextC < N) {// 범위 안이면
								if (arr[NextR][NextC] == 1 && !visited[NextR][NextC]) {// 미방문이고
									visited[NextR][NextC] = true;
									q.offer(new xy(NextR, NextC, cu_cnt + 1));
								}
							}
						}
					}
					// 새로운 단지의 집 수
					town.add(home);
				}
			}
		}
		sb.append(town.get(0) + "\n");
		town.remove(0);
		Collections.sort(town);
		for (int i = 0; i < town.size(); i++)
			sb.append(town.get(i) + "\n");

		System.out.print(sb.toString());
	}
}
