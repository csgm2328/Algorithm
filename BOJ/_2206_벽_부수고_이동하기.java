import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

//4h
//벽 한번 부수기 가능
//배열크기가 1000x1000이므로 경우의 수 탐색은 안됨
//O(N^2) 이므로 for문 돌면 시간초과
public class _2206_벽_부수고_이동하기 {
	static int R, C, chance = 1;
	static int Min, sum;
	static boolean flag; // 최단경로 찾으면 바로종료
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
//	static int[] dr = { 0, 1, 0, -1 }; // 아래 오른쪽 우선
//	static int[] dc = { 1, 0, -1, 0 };

	static class xycc {
		int r, c, cnt, crash;

		public xycc(int r, int c, int cnt, int crash) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.crash = crash;
		}
	}

	static StringBuilder sb = new StringBuilder();
	private static int[][] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// global init
		Min = Integer.MAX_VALUE;
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		visited = new boolean[R][C];
		visit = new int[R][C];
		for(int x[]: visit)
		Arrays.fill(x, Integer.MAX_VALUE);

//		List<int[]> walls = new ArrayList<int[]>();
		for (int i = 0; i < R; i++) {
			char[] in = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				arr[i][j] = in[j] - '0';
//				if (arr[i][j] == 1) {
//					walls.add(new int[] { i, j });
//				}
			}
		}

//		for (int i = 0; i < walls.size(); i++) {
//			// global init
//			sum = 1;
//			visited = new boolean[R][C];
//
//			int[] x = walls.get(i);
//			arr[x[0]][x[1]] = 0; // 벽부수기
//			bfs(0, 0);
//			arr[x[0]][x[1]] = 1; // 상태 복구
//
//			if (sum == 1)
//				continue; // 목적지까지 도달 못했으면
//			if (sum == R + C - 1) {
//				Min = sum;
//				break;
//			}
//			if (Min > sum)
//				Min = sum;
//		}
		bfs(0,0);
//		dfs(0, 0, 1);
		System.out.println(Min == Integer.MAX_VALUE ? -1 : Min);
	}

	private static void bfs(int i, int j) {
		Queue<xycc> q = new LinkedList<xycc>();
		visit[i][j] = 0;
		q.offer(new xycc(i, j, 1, 0));
		// bfs
		while (!q.isEmpty()) {
			xycc cur = q.poll();
			if (cur.r == R - 1 && cur.c == C - 1) {
				Min = cur.cnt;
				break;
			}
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];

				if (nr >= 0 && nr < R && nc >= 0 && nc < C) {// 범위 안이면
					//for문으로 벽 지우는 경우의 수 다 안해봐도 crash 카운트 떄문에 같은 효과를 냄
					//하지만 crash 카운트로 자신보다 큰 초기화상태나 부순경우를 안부수고 갈 경우에만 방문하게된다
					if (visit[nr][nc] <= cur.crash) 
						continue;
					
					if (arr[nr][nc] == 0) {	//벽아닐떄
						visit[nr][nc] = cur.crash;
						q.offer(new xycc(nr, nc, cur.cnt + 1, cur.crash));
					} 
					else { // 벽일때
						if (cur.crash == 0) { // 부실 수있으면
							visit[nr][nc] = cur.crash + 1;
							q.offer(new xycc(nr, nc, cur.cnt + 1, cur.crash + 1));
						}
					}
				}
			}
		}
	}

	private static void dfs(int cu_row, int cu_col, int sum) {
		visited[cu_row][cu_col] = true;
		if (flag)
			return;

		if (cu_row == R - 1 && cu_col == C - 1) {
			if (sum == R + C - 1) {
				Min = sum;
				flag = true;
			}
			if (Min > sum)
				Min = sum;
			return;
		}

		for (int dir = 0; dir < 4; dir++) {
			int NextR = cu_row + dr[dir];
			int NextC = cu_col + dc[dir];

			int cnt = 0;
			if (NextR >= 0 && NextR < R && NextC >= 0 && NextC < C && !visited[NextR][NextC]) {// 범위 안이면
				if (arr[NextR][NextC] == 0) {
					dfs(NextR, NextC, sum + 1);
					visited[NextR][NextC] = false;
				} else if (arr[NextR][NextC] == 1 && crashable(NextR, NextC, dir)) {
					arr[NextR][NextC] = 0;
					chance--;
					dfs(NextR, NextC, sum + 1);
					chance++;
					arr[NextR][NextC] = 1;
					visited[NextR][NextC] = false;
				}
			}
		}
	}

	private static boolean crashable(int NextR, int NextC, int dir) {
		if (chance < 1)
			return false;
		NextR += dr[dir];
		NextC += dc[dir];
		if (NextR >= 0 && NextR < R && NextC >= 0 && NextC < C) {
			if (arr[NextR][NextC] == 1)
				return false; // 다음에 1이 또있다
			else
				return true;
		} else
			return true;
	}
}
