
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//물 높이보다 높은 거를 찾으면 들어가서 BFS한다음에
//그걸 check할 수도 있지만
//들어가면서 -1씩 해주면 탐색 중에 check를 하지 않아도 됨
//dfs는 328 -110ms임
//왜일까? 큐작업이 이정도걸리는건가
//그리고 못살아남는거 있으면 바로 끝내는게 더 오래걸림 0~100까지하는거보다
public class _2468_안전영역 {
	static int[][] arr;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N, Max = -1;
	static boolean flag = true;
	static boolean[][] visited;

	static class xy {
		int x, y;

		public xy(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int Precipitation = 0; // 강수량
		while (flag) {
			int sum = 0;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] > Precipitation && !visited[i][j]) {
						DFS(i, j, Precipitation);
//						BFS(i, j, Precipitation);
						sum++; // dfs 들어갈 때 마다
					}
				}
			}
//			System.out.println(Precipitation + " " + sum);
			if (sum == 0) { // 살아남은 지역없으면 끝
				flag = false;
			} else {
				if (Max < sum)
					Max = sum;
			}
			Precipitation++;
		} // end while 강수량 변화

		System.out.print(Max);
	}

	static void BFS(int i, int j, int Precipitation) {
		Queue<xy> q = new LinkedList<xy>();
		visited[i][j] = true;
		q.offer(new xy(i, j));

		while (!q.isEmpty()) {
			int cu_row = q.peek().x;
			int cu_col = q.peek().y;
			q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int NextR = cu_row + dr[dir];
				int NextC = cu_col + dc[dir];

				if (NextR >= 0 && NextR < N && NextC >= 0 && NextC < N) {// 범위 안이면
					if (arr[NextR][NextC] > Precipitation && !visited[NextR][NextC]) {// 미방문이고
						visited[NextR][NextC] = true;
						q.offer(new xy(NextR, NextC));
					}
				}
			} // end dir
		} // end while
	}

	static void DFS(int cu_row, int cu_col, int Precipitation) {
		// 들어오면 높이 줄이기 || check
		visited[cu_row][cu_col] = true;
		// 갈 곳 없으면 자동 탈출

		// 동작
		for (int dir = 0; dir < 4; dir++) {
			int NextR = cu_row + dr[dir];
			int NextC = cu_col + dc[dir];
			if (NextR >= 0 && NextR < N && NextC >= 0 && NextC < N) {// 범위 안이면
				if (arr[NextR][NextC] > Precipitation && !visited[NextR][NextC]) {// 미방문이고
					DFS(NextR, NextC, Precipitation);
				}
			}
		}
	}
}
