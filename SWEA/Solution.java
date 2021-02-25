import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//가장 많은 core에 전원이 들어오게 하는 전선의 합 중 최소
//강의 풀이
//1. 가장자리  코어 배제
//2. 시뮬레이션이 아닌 부분집합으로
public class Solution {
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

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j] == 1)
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

	private static void backtrack(int index, int[][] origin, int core_cnt, int line_sum) {
		// 코어에서 전선을 연결할 수 있는 경우의 수
		boolean coresearch = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (origin[i][j] == 1 && !visited[i][j]) {// 코어 찾으면
					coresearch = true;
					visited[i][j] = true; // 방문처리 먼저
					boolean pass = true; // 아예 경우의 수가 없는지
					for (int dir = 0; dir < 4; dir++) {// 4방 탐색
						int NextR = i + dr[dir];
						int NextC = j + dc[dir];
						
						if (NextR >= 0 && NextR < N && NextC >= 0 && NextC < N) {// 범위 안이면
							if (arr[NextR][NextC] == 0 && connectable(NextR, NextC, dir)) {// 연결 가능한지 쭉 가보기
								pass = false;
								origin = connect_line(origin, NextR, NextC, dir);// 전선 표시
								backtrack(index+1,origin, core_cnt + 1, line_sum + line_temp);
								origin = disconnect_line(origin, NextR, NextC, dir); // 전선 다시지움
							} 
							else {
								backtrack(index+1,origin, core_cnt, line_sum);
							}
						} else { // 벽에 붙어 있으면 바로 경우의 수 끝
							pass = false;
							backtrack(index+1, origin, core_cnt+1,line_sum);
							// break하고 싶지만 밑에랑 부딪힘
						}
					}

//					if (pass)
//						backtrack(origin, core_cnt, line_sum);
					visited[i][j] = false; // 더이상 유효한 경우의 수가 없다면
					return;

				} // end if 코어찾기
			}
		} // end for
		if (index == totalcore) { // 다 방문 했으면
			if (Max <= core_cnt) { // 
				Max = core_cnt;
//				System.out.println(Max);
				if (Min > line_sum) { // 가장 코어 많이 찾았을때만 검사
					Min = line_sum;
					System.out.println(Min);
//					for(int[] x: origin)
//						System.out.println(Arrays.toString(x));
				}
			}
			return;
		}
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
		line_temp = 0; //해당 코어의 선 개수
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
