import java.io.*;
import java.util.*;

//캐슬디펜스
//나의 풀이
//결정적인 실수는 적이 두명의 궁수에게 죽을 수 있는데
//bfs를 돌면서 방문처리를 하고 처음에 큐에 궁수 위치를 시작점으로 넣을 때도
//방문처리를 하면 안되는 거였다.

//강의 풀이
//적군의 위치를 따로 배열로 관리하면 쉽다

public class _17135_캐슬디펜스 {
	static int[][] arr;
	static int[] archer; // 궁수 위치
	static int N, M, D;
	static int MAX = -1;

	static int[] dr = { 0, -1, 0 }; // 좌, 상, 우 순서
	static int[] dc = { -1, 0, 1 };

	static class axyc { // 그 궁수가 활을 쐈는지 체크하기위해 궁수번호 저장
		int archer, r, c, cnt;

		public axyc(int archer, int r, int c, int cnt) {
			super();
			this.archer = archer;
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		// 가장 적합한 궁수의 자리를 조합으로 구하고 그 조합으로 게임을 진행해서 가장 많은 킬수를 구한다
		archer = new int[3];
		combination(0, 0);
		System.out.print(MAX);
	}

	private static void combination(int cnt, int start) {
		if (cnt == 3) { // 궁수 3명 다고르면
			// 카피 배열로 디펜스 시작
			int[][] copy = new int[N][M];
			for (int x = 0; x < N; x++)
				for (int y = 0; y < M; y++)
					copy[x][y] = arr[x][y];
			int kill = start_defence(copy, archer);
			if (MAX < kill) {
				MAX = kill;
//				System.out.println(Arrays.toString(archer));
//				System.out.println(MAX);
			}
			return;
		}
		for (int i = start; i < M; i++) {
			archer[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}

	private static int start_defence(int[][] copy, int archer[]) {
		int kill_sum = 0; // 해당 궁수배치로 만든 총 킬수
		int turn = 0; // 행만큼 라운드 반복
		while (turn < N) { // 디펜스 게임시작 라운드 끝마다 적이 없어서 끝내도 되는지 체크
			int[] arrow = { 1, 1, 1 }; // 궁수의 활 사용여부
			Queue<axyc> q = new LinkedList<axyc>(); // bfs방식으로 가장 가까운 적을 찾겠따
//			boolean[][] visited = new boolean[N][M]; // 궁수들이 방문배열을 공유해도된다?
			int kill = 0; // 해당라운드 킬수
			// 라운드 시작하면서 큐에 궁수위치 시작점 셋팅하는데
			for (int i = 0; i < 3; i++) { // 궁수 셋을 순서대로 처리하면 안된다 궁수셋의 시작점을 큐에 연달아 넣고
				int x[] = { N - 1, archer[i] }; // 궁수에서 가장 가까운 바로위
				if (copy[x[0]][x[1]] == 1) { // 적 있으면
					copy[x[0]][x[1]] = 2; // 나중에 2표시보고 kill올릴거임
					arrow[i] = 0; // 해당 궁수 활 씀
					continue; // 잡았으면 큐엔 안넣을거임
				}
				q.offer(new axyc(i, x[0], x[1], 1)); // bfs시작점
			}
			// 각 궁수들이 한번에 못잡고 여기로 왔다면
			// 사정거리를 한단계씩 늘려보면서 차근차근 적을 잡는다
			// 이 경우에서 같은 적이 여러궁수에게 공격당할 수 잇는데 이때를 내비둬야함 <- 실수포인트!!!!!!
			while (!q.isEmpty()) {
				axyc cur = q.poll();
				if (cur.cnt == D || arrow[cur.archer] == 0) {// 거리를 넘거나 해당궁수가 활 쐈으면 패스
					continue;
				}
				for (int dir = 0; dir < 3; dir++) {
					int nr = cur.r + dr[dir];
					int nc = cur.c + dc[dir];
					if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc]) {
						if (copy[nr][nc] > 0) { // 적 발견
							// 왼쪽이나 위에서 찾았다면 바로 킬하고 처리 -> 실수포인트: 위에가 다음궁수가 쏠 위치일 수도 있다
							arrow[cur.archer] = 0;
							copy[nr][nc] = 2;
							break;
						} else { // 0이어도 사정거리 증가를 위해 큐에 넣음
							q.offer(new axyc(cur.archer, nr, nc, cur.cnt + 1));
						}
					} // end check boundary
				}
			} // end while
				// 새로운 턴으로
				// 한칸씩 내려오는 적
				// 새로운 라운드 가기전에 적 남아있느지 체크
			boolean flag = true;
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < M; y++) {
					if (copy[x][y] == 1) {
						flag = false;
					} else if (copy[x][y] == 2) {// 다 0인지 검사하면서 2바뀐거 처리
						copy[x][y] = 0;
						kill++;
					}
				}
			}
			kill_sum += kill;
			if (!flag) { //적이 남아있으면 다음라운드
				new_round(copy);
				turn++;
			} else
				break;
		}
		return kill_sum;
	}
	
	private static void new_round(int[][] copy) {
		// N-2부터 한칸씩 내려서 표시
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				copy[i + 1][j] = copy[i][j];
			}
		}
		for (int j = 0; j < M; j++)
			copy[0][j] = 0; // 맨 윗줄은 0으로
	}
}
