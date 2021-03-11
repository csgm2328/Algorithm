package SW_TEST;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//가장 많은 core에 전원이 들어오게 하는 전선의 합 중 최소
//코어에 겹치지 않게 전선을 연결할 수 있는 경우의 수 구하기

//강의 풀이
//1. 가장자리  코어 배제
//2. 시뮬레이션이 아닌 부분집합으로
public class _1767_프로세서_연결하기 {
	//전역 변수 초기화 체크 필수 제발
	static int T, N;
	static int totalcore, line_temp;
	static int Max, Min;
	static int[][] arr;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visited = new boolean[N][N];
			totalcore = 0;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 1 && !(i == 0 || i == N - 1 || j == 0 || j == N - 1)) // 가장 자리배제
						totalcore++;
				}
			}

			// 코어에 전원을 연결할 수 있는경우
			// 1. 1칸범위의 4방 탐색하면서 0인곳
			// --> 그 방향으로 범위가 나갈때까지 1을 안만나면 2를 표시
			// 2. 4방 중에 범위가 나가는 곳이 있으면
			// 근데 최소 전선길이를 구해야하므로
			// 가능한 경우를 다해봐야함
			// 코어가 벽에 붙어있다면 안보는 걸로 해야 효율적
			// 경우의 수 들어갈때마다 상태배열을 매개변수로
			Max = -1;
			Min = Integer.MAX_VALUE;
			backtrack(0, arr, 0, 0);
			sb.append("#" + tc + " " + Min + "\n");
		}
		System.out.println(sb.toString());
	}

	// 실수한 부분
	// core가 연결된 카운트와 코어를 탐색한 카운트 두개를 세야함
	private static void backtrack(int index, int[][] origin, int core_cnt, int line_sum) {

		if (index == totalcore) { // 다 방문 했으면
			if (Max < core_cnt) { //
				Max = core_cnt;
				Min = line_sum;
				//System.out.printf("%d, %d\n", Max, Min);
				
			} else if (Max == core_cnt)
				Min = Math.min(Min, line_sum);
			return;
		}

		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {
				if (origin[i][j] == 1 && !visited[i][j]) {// 코어 찾으면
					visited[i][j] = true; // 방문처리 먼저
					for (int dir = 0; dir < 4; dir++) {// 4방 탐색
						int NextR = i + dr[dir];
						int NextC = j + dc[dir];

						// if (NextR >= 0 && NextR < N && NextC >= 0 && NextC < N) {// 가장자리 배제했으므로 생략
						if (arr[NextR][NextC] == 0 && connectable(NextR, NextC, dir)) {// 연결 가능한지 쭉 가보기
							origin = connect_line(origin, NextR, NextC, dir);// 전선 표시
							backtrack(index + 1, origin, core_cnt + 1, line_sum + line_temp);
							origin = disconnect_line(origin, NextR, NextC, dir); // 전선 다시지움
//							if(line_temp == 1) {
//								visited[i][j] = false;
//								break;//도착지까지 1일 때  방해하는 거 없으므로다른 경우 안봐도됨
//							}
						} else { // 코어 연결 불가한 경우
							backtrack(index + 1, origin, core_cnt, line_sum);
						}
						// }
//						else { // 가장자리이면 항상 되는 경우로 생각하고 dir를 돌면 안됨 그래서 가장자리는 배제하고 시작해야함
//							backtrack(index+1, origin, core_cnt+1,line_sum);
//							// break하고 싶지만 밑에랑 부딪힘
//						}
					}
					// 더이상 유효한 경우의 수가 없다면 return해줘야함 아니면 false처리한 다음거를 찾으러 가서 오류
					visited[i][j] = false;
					return;
				} // end if 코어찾기
			}
		} // end for
	}

	// 전선 다시 지우기
	private static int[][] disconnect_line(int[][] origin, int NextR, int NextC, int dir) {
		while (NextR >= 0 && NextR < N && NextC >= 0 && NextC < N) {
			origin[NextR][NextC] = 0;
			NextR += dr[dir];
			NextC += dc[dir];
		}
		return origin;
	}

	// 전선 표시
	private static int[][] connect_line(int[][] origin, int NextR, int NextC, int dir) {
		line_temp = 0; // 해당 코어의 선 개수
		while (NextR >= 0 && NextR < N && NextC >= 0 && NextC < N) {
			origin[NextR][NextC] = 2;
			line_temp++;
			NextR += dr[dir];
			NextC += dc[dir];
		}
		return origin;
	}

	private static boolean connectable(int cu_row, int cu_col, int dir) {
		boolean flag = true;
		int NextR = cu_row;
		int NextC = cu_col;
		while (true) {
			NextR += dr[dir];
			NextC += dc[dir];
			if (NextR >= 0 && NextR < N && NextC >= 0 && NextC < N) { // 범위나갈때까지 검사
				if (arr[NextR][NextC] != 0) {
					return false;
				}
			} else
				return true;
		}
	}
}
