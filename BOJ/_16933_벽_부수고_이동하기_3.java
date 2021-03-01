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

//밤에는 부술수 없다
//부순횟수를 3차원배열로 확인하자
//그러면 아주 간단하다
//하지만 움직이는 각 경우의 부술수 있는 횟수를 visit에 저장하고
//그것보다 적게 부수고 그 위치에 도달할 수 있는 경우의 수만  push한다면
//visit의 3차원 배열로 최대 10번인 해당횟수를 사용했는지 체크하는것보다
//빠르다
//밤에  기다리지 않고 wait+1만하고 진행하게 되면
//cnt는 같지만 wait가 더 작은 경우가 도착하지 못하는 반례가 발생한다
//하지만 여기서 wait를 부순횟수와 같이 체크하게 되면 무한루프가 생길 수 있다 {7,1} <-> {9,2}
//wait와 cnt의 합 체크도 마찬가지
//그래서 밤에 기다리는 거를 wait+1만 하고 처리를 다음으로 미루고(push)
//안 기다린게 먼저 도착하게 되고 늦게 도착하지만 cnt가 작은 경우도 다 체크하게 된다.

/**
 반례
5 3 10
001
110
011
010
010
: 7

10 20 10
00100010110010001010
10110101110101011101
01101010001010011011
11101000011101011110
01100111011111000011
11000000001111111101
01100010010101111001
00000100110010100101
10011010100100010110
11011001110000010100
: 30
 */
public class _16933_벽_부수고_이동하기_3 {
	static int R, C, K;
	static char[][] arr;
	private static int[][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class xycc {
		int r, c, cnt, crash, wait;
		boolean day;

		public xycc(int r, int c, int cnt, int crash, int wait, boolean day) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.crash = crash;
			this.wait = wait; // 밤에 벽을 만났을때
			this.day = day; // 낮부터
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
			for (int j = 0; j < C; j++) {
				arr[i][j] = x[j];
				if (x[j] == '0') // 벽을 뚫수 없어도 벽이 아닌곳은 가게끔
					visited[i][j] = -1;
			}
		}
		bfs(0, 0);
	}

	private static void bfs(int i, int j) {
		int[] dr = { 0, 1, -1, 0 }; // 상하좌우 뭘 먼저하든 속도 상관없음
		int[] dc = { 1, 0, 0, -1 };
		int Min_cnt = Integer.MAX_VALUE;
		int Min_wait = Integer.MAX_VALUE;
		int Min = 0;

		Queue<xycc> q = new LinkedList<xycc>();
		visited[i][j] = K; // 몇 번 부수기 가능한 곳에서 왔는지
		q.offer(new xycc(i, j, 1, K, 0, true)); //  + wait, day 낮부터

		while (!q.isEmpty()) {
			xycc cur = q.poll();
			if (cur.r == R - 1 && cur.c == C - 1) {
				if (Min_cnt >= cur.cnt) {
					Min_cnt = cur.cnt;
					if (Min_wait > cur.wait) {
						Min_wait = cur.wait;
						Min = Min_cnt + Min_wait;
					}
				}
			}
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				// 낮에만 벽 부수기 가능
				// 4방탐색을 하는데 진행할 수 있다면
				// 낮에는 그냥 그냥 하고
				// 밤일 때는
				// 밤에는 wait카운트 +1
				// 반례: 벽을 부수고 오는 수는 같지만 낮과 밤을 만난 경우가 다른 경우에 누락이 발생한다
				// 이렇게 복잡하게 생각하지 말고 하루 기다리는 걸 wait +1하고 큐뒤로 던져야함
				if (InBoundary(nr, nc) && visited[nr][nc] < cur.crash) {// 범위 안
					if (arr[nr][nc] == '0') { // 벽아닐떄
						q.offer(new xycc(nr, nc, cur.cnt + 1, cur.crash, cur.wait, !cur.day));
						visited[nr][nc] = cur.crash;
					} else { // 벽일때
						if (cur.day) {// 낮에
							q.offer(new xycc(nr, nc, cur.cnt + 1, cur.crash - 1, cur.wait, !cur.day));
							visited[nr][nc] = cur.crash;
						} else {
//								q.offer(new xycc(nr, nc, cur.cnt + 1, cur.crash - 1, cur.wait + 1, cur.day));
							// 큐 맨뒤로 보낸다음에 나중에 처리한다..................crash 기록도 나중으로 미뤘다면 3시간전에 끝났을텐데
							q.offer(new xycc(cur.r, cur.c, cur.cnt, cur.crash, cur.wait + 1, !cur.day));
						}
					}
				}
			}
		}

		System.out.print(Min == 0 ? -1 : Min);
//		System.out.println("for문: " + forc);
//		System.out.println("print전 큐 남은거: " + q.size());
//		System.out.println("큐에 집어넣은 수: " + offerc);

	}

	private static boolean InBoundary(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
