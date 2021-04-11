package SW_TEST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//오히려 물에서부터 시작하는 bfs
//토마토랑 똑같은데 각자 도는 bfs에서 현재꺼가 더작으면 visit[]을 갱신한다
//물웅덩이는 가장자리에서만 시작 알아서 그렇게됨
//각땅에서 물에도달하는 최소 경로의 합

public class _10966_물놀이를가자 {
	static class xyc {
		int r, c, cnt;

		public xyc(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int[][] visited;
	static char[][] arr;
	static List<int[]> water = new ArrayList<int[]>();
	static Queue<xyc> q = new LinkedList<xyc>();
	static int T, N, M;
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new char[N][M];
			visited = new int[N][M];

			for (int[] x : visited) // 가장 작은 수 넣을꺼니까 맥스초기화
				Arrays.fill(x, Integer.MAX_VALUE);

			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					arr[i][j] = s.charAt(j);
					if (arr[i][j] == 'W') {
						visited[i][j] = 0; 
						q.offer(new xyc(i, j, 0)); // 처음 부터 시작
					}
				}
			}

			while (!q.isEmpty()) {
				xyc cur = q.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nr = cur.r + dr[dir];
					int nc = cur.c + dc[dir];
					if (0 <= nr && nr < N && 0 <= nc && nc < M) {
						if (visited[nr][nc] > cur.cnt + 1) { // 다음칸이 지금에서 한칸 더 가는거보다 클때 방문
							visited[nr][nc] = cur.cnt + 1; // 더 최소경로로 방문 할 수 있으면 갱신
							q.offer(new xyc(nr, nc, cur.cnt + 1));
						}
					} // end check boundary
				}
			} // end while

			// visited 탐색
			int sum = 0;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					sum += visited[i][j];

			sb.append("#" + tc + " " + sum + "\n");
			for (int[] x : visited) 
				Arrays.fill(x, Integer.MAX_VALUE);
		} // end tc
		System.out.println(sb.toString());
	}
}
