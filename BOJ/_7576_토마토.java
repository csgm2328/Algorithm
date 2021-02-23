import java.io.*;
import java.util.*;

//토마토가 떨어져 있어도 동시에 익음을 퍼뜨리기 시작해야한다
// 입력받을 때 미리 익은 토마토 체크
// BFS하면서 cnt를 가지고 다니면서 가장 최대값을 체크한다
// 1로도 가야하나?
// 입력받을 때 익은 토마토 위치 체크하고 거기서 퍼지면 안그래도 됨
public class _7576_토마토 {
	static StringBuilder sb = new StringBuilder();
	static int R, C, day; // 1 ~ 15
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int Max = -1;
	static int[][] arr;
	static boolean[][] visited;

	static class Triple {
		int x;
		int y;
		int day;

		public Triple(int x, int y, int day) {
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		visited = new boolean[R][C];
		Queue<Triple> q = new LinkedList<Triple>();
		int zerocnt = 0;

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) // 익은 토마토 위치 저장
					q.offer(new Triple(i, j, 0));
			}
		}
		// BFS
		while (!q.isEmpty()) {
			int cu_row = q.peek().x;
			int cu_col = q.peek().y;
			int cu_day = q.peek().day;
			q.poll();

			if (Max < cu_day)
				Max = cu_day;

			for (int dir = 0; dir < 4; dir++) {
				int NextR = cu_row + dr[dir];
				int NextC = cu_col + dc[dir];
				if (0 <= NextR && NextR < R && 0 <= NextC && NextC < C) {
					if (arr[NextR][NextC] == 0) { // 안익었으면
						arr[NextR][NextC] = 1; // 익음
						q.offer(new Triple(NextR, NextC, cu_day + 1));
					}
				}
			}
		}
		System.out.print(zerocnt == 0 ? Max : -1);
	}
}
