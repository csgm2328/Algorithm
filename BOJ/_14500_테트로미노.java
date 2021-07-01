import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 테트로미노
// 11:50 ~ 01:00
// 맵에다가 5개의 모양 그냥 다 놔보기
// 돌리기 가능

public class _14500_테트로미노 {
	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		max = -1;
		Tetromino();
		System.out.println(max);
	}

	static int[][] dr = { { 0, 0, 0, 0 }, { 0, 0, 1, 1 }, { 0, 1, 2, 2 }, { 0, 1, 2, 2 }, { 0, 1, 1, 2 },
			{ 0, 1, 1, 2 }, { 0, 0, 1, 0 } };
	static int[][] dc = { { 0, 1, 2, 3 }, { 0, 1, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, -1 }, { 0, 0, 1, 1 },
			{ 0, 0, -1, -1 }, { 0, 1, 1, 2 } };
//	static int[] dr3 = ; //90도 회전시 0,0,0,-1  {0,-1,-2,-2} {0,0,0,1} 대칭은 {0,1,2,2}
//	static int[] dc3 = ; //90도 회전시 0,1,2,2  {0,0,0,-1}  {0,-1,-2,-2}    {0,0,0,-1};
//	static int[] dr4 = ; //{0,0,-1,-1}
//	static int[] dc4 = ; //{0,1,1,2};
	static int max;

	private static void Tetromino() {
		for (int i = 0; i < 7; i++) { // 5개의 모양 + 3,4번 모양 대칭합쳐서 7개
			int rotate = calc(i);
			for (int ro = 0; ro <= rotate; ro++) { // 회전 가능
				if (ro != 0)
					swap(i);
				for (int r = 0; r < N; r++) { // 맵
					for (int c = 0; c < M; c++) {
						int temp = 0;
						boolean flag = true;
						for (int dir = 0; dir < 4; dir++) { // 모양은 4조각
							int nr = r + dr[i][dir];
							int nc = c + dc[i][dir];
							int x = isInBoundary(nr, nc);
							if (x == -1) {
								flag = false;
								break;
							} else
								temp += x;
						}
						if (flag)
							max = Math.max(max, temp);
					}
				}
			}
		} // end for
	}

	private static void swap(int i) { // 한번씩 돌리는 게 누적됨
		int[] tmp = new int[4];
		for (int x = 0; x < 4; x++)
			tmp[x] = dr[i][x];
		for (int x = 0; x < 4; x++) // 계속 -로 바뀌고
			dr[i][x] = -dc[i][x];
		for (int x = 0; x < 4; x++) // 그대로
			dc[i][x] = tmp[x];
	}

	private static int calc(int i) {
		if (i == 0)
			return 1;
		else if (i == 1)
			return 0;
		else
			return 3;
	}

	private static int isInBoundary(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < M)
			return map[nr][nc];
		else
			return -1;
	}
}