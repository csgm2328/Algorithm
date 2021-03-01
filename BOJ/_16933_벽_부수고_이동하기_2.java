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

//벽 부수기 K만큼 가능
public class _16933_벽_부수고_이동하기_2 {
	static int R, C, K;
	static char[][] arr;
	private static int[][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

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

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// global init
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new char[R][C];
		visited = new int[R][C];

		for (int i = 0; i < R; i++) {
			char[] x = br.readLine().toCharArray();
			for(int j =0; j< C ;j++) {
				arr[i][j] = x[j];
				if(x[j] == '0') 
					visited[i][j] = -1; //벽을 뚫수 없어도 벽이 아닌곳은 가게끔 
			}
		}
		bfs(0, 0);
	}

	private static void bfs(int i, int j) {
		int[] dr = { 0, 1, -1, 0 }; //상하좌우 뭘 먼저하든 속도 상관없음
		int[] dc = { 1, 0, 0, -1 };
		int Min = Integer.MAX_VALUE;

		int forcnt = 0;
		int offercnt =0;
		Queue<xycc> q = new LinkedList<xycc>();
		visited[i][j] = K; // 이번엔 몇번 부숴서 왔는지가 아니라 몇번 더 부술수 있는지를 쓸거임
		q.offer(new xycc(i, j, 1, K));

		while (!q.isEmpty()) {
			xycc cur = q.poll();
			if (cur.r == R - 1 && cur.c == C - 1) {
				System.out.println(cur.cnt);
				Min = cur.cnt;
//				break;
			}
			for (int dir = 0; dir < 4; dir++) {
				forcnt++;
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];

				if (InBoundary(nr, nc) && visited[nr][nc] < cur.crash) {// 범위 안 & 더 적은 부수기로 갈수 있을때
					//visit과 현재 부수기가 같을 때는 가면 안됨
					if (arr[nr][nc] == '0') { // 벽아닐떄
//						visited[nr][nc] = cur.crash;
						q.offer(new xycc(nr, nc, cur.cnt + 1, cur.crash));
					} else { // 벽일때
//						visited[nr][nc] = cur.crash-1;
						q.offer(new xycc(nr, nc, cur.cnt + 1, cur.crash - 1));
					}
					offercnt++;
					visited[nr][nc] = cur.crash;
					//몇 부수기 가능에서 왔는지 기록하는건데
					//미리 같은 부수기 가능에서 또 큐에 넣는걸 막을 수 있음 1172 -250ms
				}
			}
		}

		System.out.print(Min == Integer.MAX_VALUE ? -1 : Min);
		System.out.println("\nfor문: " + forcnt);
		System.out.println("print전 큐 남은거: " + q.size());
		System.out.println("큐에 집어넣은 횟수: " + offercnt);
	}

	private static boolean InBoundary(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
