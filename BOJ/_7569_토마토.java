import java.io.*;
import java.util.*;

// 3차원 토마토
public class _7569_토마토 {
	static StringBuilder sb = new StringBuilder();
	static int R, C, H; // 1 ~ 100
	static int[] dr = { -1, 1, 0, 0, 0, 0 };
	static int[] dc = { 0, 0, -1, 1, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, -1, 1 };

	static int Max = -1;
	static int[][][] arr;
	static boolean[][][] visited;

	static class Triple {
		int x, y, h, day;

		public Triple(int x, int y, int h, int day) {
			super();
			this.x = x;
			this.y = y;
			this.h = h;
			this.day = day;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		arr = new int[R][C][H];
		visited = new boolean[R][C][H];
		Queue<Triple> q = new LinkedList<Triple>();
		int zerocnt = 0;

		for (int h = 0; h < H; h++) {
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					arr[i][j][h] = Integer.parseInt(st.nextToken());
					if (arr[i][j][h] == 1) // 익은 토마토 위치 저장
						q.offer(new Triple(i, j, h, 0));
					else if(arr[i][j][h] == 0)
						zerocnt++;
				}
			}
		}
		// BFS
		while (!q.isEmpty()) {
			int cu_row = q.peek().x;
			int cu_col = q.peek().y;
			int cu_height =q.peek().h;
			int cu_day = q.peek().day;
			q.poll();

			if (Max < cu_day)
				Max = cu_day;

			for (int dir = 0; dir < 6; dir++) {
				int NextR = cu_row + dr[dir];
				int NextC = cu_col + dc[dir];
				int NextH = cu_height + dh[dir];
				if (0 <= NextR && NextR < R && 0 <= NextC && NextC < C && 0<= NextH && NextH < H) {
					if (arr[NextR][NextC][NextH] == 0) { // 안익었으면
						arr[NextR][NextC][NextH] = 1; // 익음
						q.offer(new Triple(NextR, NextC, NextH, cu_day + 1));
						zerocnt--;
					}
				}
			}
		}
		System.out.print(zerocnt == 0 ? Max : -1);
	}
}
