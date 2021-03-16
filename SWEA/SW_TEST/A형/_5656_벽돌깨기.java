package SW_TEST.A형;
//구슬로 벽돌 좌우로 왔다갔다하면서 깨는데

//벽돌에 써있는 만큼 4방향의 벽돌을 지우고
//연쇄방향
//1이상 벽돌이 깨지면 연쇄적으로 다 터짐
//폭발은 순차적으로 큐에 넣어서
//싹다 터지고 나면 빈칸 채움 --> 밑에서 부터 한열씩보면서 1찾아서 채워넣음
//근데 가장 많이 지울수 있는경우를 찾아야함
//그래서 복사본에 구슬을 쏴바야함 구슬 최대 4개

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//근데 3번의 구슬을 쏴서 가장 많이 벽돌을 꺠야하므로
//쏘는 방법을 순열로 체크하자
//중복순열을 다만들고 끝에서 배열을 깨기시작하면 시간초과
//쏠 위치가 정해지면 바로바로 깬 다음에 그 배열을 다음 재귀에 넘겨준다

public class _5656_벽돌깨기 {
	static int K, N, M; // 구슬 수, 가로세로 <=12,15
	static int MIN;
	static int arr[][];

	static class xyc {
		int r, c, cnt;

		public xyc(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken()); //열
			N = Integer.parseInt(st.nextToken()); //행
			MIN = Integer.MAX_VALUE;

			arr = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			permutation(0, arr);
			sb.append("#" + tc + " " + MIN + "\n");
		} // end tc
		System.out.println(sb.toString());
	}

	private static void permutation(int index, int[][] map) {
		if (index == K) { // 구슬 던지는 경우의수만큼
			// 남은 벽돌 세기
			int remain = countMap(map);
			if (MIN > remain) {
				MIN = remain;
			}
			return;
		}
		
		
		for (int i = 0; i < M; i++) { // NPk 열에서 3개고르는 중복순열 k^M
			//새로운 게 선택될 때마다 현재의 deepcopy로 복사본을 계속 넘겨줘야함
			int[][] copy = new int[N][M];
			for(int k = 0 ; k< N; k++) {
				for(int j =0; j< M; j++) {
					copy[k][j] = map[k][j];
				}
			}
			permutation(index + 1, bomb(copy,i)); //이걸로 copy도 바뀌므로  copy도 계속 새로
		}
	}

	private static int[][] bomb(int[][] map, int col) { // 큐에 들어있는 폭발순서대로
		Queue<xyc> q = new LinkedList<xyc>();
		// 넘어온 열에서 가장 처음만나는 블록 찾기
		for (int i = 0; i < N; i++) {
			if (map[i][col] != 0) {
				q.offer(new xyc(i, col, map[i][col])); // 큐에 넣고 순차폭발 준비
				break;
			}
		}
		while (!q.isEmpty()) {
			xyc cur = q.poll(); // 행,열
			map[cur.r][cur.c] = 0; // 자기꺼 지우고
			for (int i = 1; i < cur.cnt; i++) { // 블록에 써있는 만큼 폭발
				// 상하좌우
				for (int dir = 0; dir < 4; dir++) {
					int nr = cur.r + dr[dir] * i; // 폭발 증가
					int nc = cur.c + dc[dir] * i;
					if (0 <= nr && nr < N && 0 <= nc && nc < M) {
						if (map[nr][nc] == 0)
							continue;
						else if (map[nr][nc] == 1)
							map[nr][nc] = 0;
						else { // 지우면서 1보다 큰거는 큐에 추가(연쇄반응)
								// 연쇄작용을 처리해야하는데 맵에 표시를 해아할 때 나중에 맵에써있는걸 보므로 문제가 됨 그럼 따로 큐에 저장을해야겠따
							q.offer(new xyc(nr, nc, map[nr][nc]));
							map[nr][nc] = 0;
						}
					}
				} // for dir
			}
		} // end while
		//다 했으면 내리기를 빼먹었네
		arrangeMap(map);
		return map;
	}
	private static void arrangeMap(int[][] map) {
		//열을 밑에서부터 보면서 0을 만나면 0이 아닌게 있는지 검사하고 그자리 스위칭
		for(int j = 0; j < M; j++) {
			int bottom = -1; //새로운 열
			for(int i = N-1; i>=0; i--) {
				if(map[i][j] != 0) { //0 아닌걸 찾았는데
					if(bottom == -1) { //스위치할 바닥찾기전에는 패스
						continue;
					}
					else { //바꿀 바닥을 찾았다면 스위칭
						int temp = map[i][j];
						map[i][j] = map[bottom][j];
						map[bottom][j] = temp;
						bottom--; //바텀 한칸 업
					}
				}
				else if(bottom == -1){
					bottom = i; //바꿀 바닥위치는 처음만 갱신
				}
			}
		}
	}
	private static int countMap(int[][] map) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					sum++;
				}
			}
		}
		return sum;
	}
}
