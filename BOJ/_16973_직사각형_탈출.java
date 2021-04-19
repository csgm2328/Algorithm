import java.io.*;
import java.util.*;

// 직사각형 탈출
// 직사각형 면적을 다 훓거나 벽의 거리를 세거나 하려고했는데
// 직사각형 움직일때마다 사실 검사해야하는 건 움직인 방향으로의 한줄뿐이였다
public class _16973_직사각형_탈출 {
	static int N, M;
	static int H, W, Sr, Sc, Fr, Fc;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
//	static List<int[]> wallList = new ArrayList<int[]>();

	static class xyc {
		int r, c, cnt;

		public xyc(int r, int c, int cnt) {
			super();
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

		arr = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
//				if (arr[i][j] == 1) {
//					wallList.add(new int[] { i, j });
//				}
			}
		}
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		Sr = Integer.parseInt(st.nextToken());
		Sc = Integer.parseInt(st.nextToken());
		Fr = Integer.parseInt(st.nextToken());
		Fc = Integer.parseInt(st.nextToken());

//		if (wallList.size() >= H * W)
//			wallList.clear();
		bfs();
		System.out.println(ans);
	}

	static int ans = -1;

	private static void bfs() {
		//벽을 만나게 되는 꼭지점의 위치를 아예 1로 만들어버려서 탐색을 안하게 만들어버림 : 524ms
//		for (int k = 0; k < wallList.size(); k++) {
//			int y = wallList.get(k).y;
//			int x = wallList.get(k).x;
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					if (y - i < 0 || x - j < 0)
//						continue;
//					arr[y - i][x - j] = 1;
//				}
//			}
//		}
		Queue<xyc> q = new LinkedList<xyc>();
		q.offer(new xyc(Sr, Sc, 0));
		visited[Sr][Sc] = true;

		while (!q.isEmpty()) {
			xyc cur = q.poll();
			if (cur.r == Fr && cur.c == Fc) {
				ans = cur.cnt;
				break;
			}

			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				if (Movable(nr, nc, dir) && !visited[nr][nc]) {
					q.offer(new xyc(nr, nc, cur.cnt + 1));
					visited[nr][nc] = true;
				}
			}
		}

	}

	private static boolean Movable(int nr, int nc, int dir) {
		switch (dir) {
		case 0: // 상 - 이미 벽없는데서 왔기 때문에 새로운 윗줄 하나만 검사하면 끝
			if (nr < 1)
				return false;
			for (int i = 0; i < W; i++)
				if (arr[nr][nc + i] == 1)
					return false;
			break;
		case 1: // 하는 끝줄
			if (nr + H - 1 > N)
				return false;
			for (int i = 0; i < W; i++)
				if (arr[nr + H - 1][nc + i] == 1)
					return false;
			break;
		case 2: // 좌
			if (nc < 1)
				return false;
			for (int i = 0; i < H; i++)
				if (arr[nr + i][nc] == 1)
					return false;
			break;
		case 3: // 우는 끝행
			if (nc + W - 1 > M)
				return false;
			for (int i = 0; i < H; i++)
				if (arr[nr + i][nc + W - 1] == 1)
					return false;
			break;
		default:
			break;
		}
		return true;
	}

//	private static boolean isinWall(int nr, int nc) {
//		// 벽이 직사각형 안에 있는지 검사
//		if (wallList.size() == 0) { // 범위검사
//			for (int i = 0; i < H; i++) {
//				for (int j = 0; j < W; j++) {
//					if (arr[nr + i][nc + j] == 1)
//						return true;
//				}
//			}
//		} else { // 벽 위치 검사
//			for (int i = 0; i < wallList.size(); i++) {
//				int wallR = wallList.get(i)[0];
//				int wallC = wallList.get(i)[1];
//				if (0 <= wallR - nr && wallR - nr < H && 0 <= wallC - nc && wallC - nc < W)
//					return true;
//			}
//		}
//		return false;
//	}
}
