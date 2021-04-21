import java.io.*;
import java.util.*;

// 지금까지는 순열의 다음경우를 구할 때 임시배열에 반영한 후넘겼는데
// 이거는 cctv 개수만큼 순열을 만들면서
// cctv 종류에 따라 다르게 반영해야하므로
// switch문으로 cctv 종류를 구분하고 반영한 임시배열을 다음으로 넘긴다.

public class _15683_감시 {
	static int N, M;
	static List<int[]> cctvList = new ArrayList<int[]>();
	static List<int[]> fiveList = new ArrayList<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 5) 
					fiveList.add(new int[] { i, j });
				else if (1 <= arr[i][j] && arr[i][j] <= 4)
					cctvList.add(new int[] { i, j });
			}
		}
		// 감시시작
		// 실수포인트!!!!!5번을 입력받으면서 처리했더니 반례가 많아진다
		for (int i = 0; i < fiveList.size(); i++) {
			int r = fiveList.get(i)[0];
			int c = fiveList.get(i)[1];
			int[] dir = { 0, 1, 2, 3 };// 4방향 감시표시
			watch(arr, r, c, dir);
		}
		setCCTV(arr, 0);
		System.out.println(MIN);
	}

	static int MIN = Integer.MAX_VALUE;

	private static void setCCTV(int[][] map, int idx) {
		// cctv들 방향설정하는 경우의 수
		if (idx == cctvList.size()) {
			// 다 설정했으면 사각지대 세기
			MIN = Math.min(MIN, getBlindSpots(map));
			return;
		}

		int r = cctvList.get(idx)[0];
		int c = cctvList.get(idx)[1];
		switch (map[r][c]) {
		case 1: // 1번 CCTV경우의 수 4개
			for (int j = 0; j < 4; j++) {
				int[] dir = { j }; // 감시할 방향
				int[][] copy = new int[N][M]; //copy를  for문 밖에서 선언하면 임시배열을 쓰는 의미가 없어진다
				for (int x = 0; x < N; x++)
					for (int y = 0; y < M; y++)
						copy[x][y] = map[x][y];
				setCCTV(watch(copy, r, c, dir), idx + 1);
			}
			break;
		case 2: // 상하, 좌우
			for (int j = 0; j < 2; j++) {
				int[] dir = new int[2];
				if (j == 0) {
					dir[0] = 0;
					dir[1] = 1;
				} else {
					dir[0] = 2;
					dir[1] = 3;
				}
				int[][] copy = new int[N][M];
				for (int x = 0; x < N; x++)
					for (int y = 0; y < M; y++)
						copy[x][y] = map[x][y];
				setCCTV(watch(copy, r, c, dir), idx + 1);
			}
			break;
		case 3: // 직각 4가지
			for (int j = 0; j < 4; j++) {
				int[] dir = new int[2];
				if (j == 0) { // 좌상, 상우, 우하, 하좌
					dir[0] = 0;
					dir[1] = 2;
				} else if (j == 1) {
					dir[0] = 0;
					dir[1] = 3;
				} else if (j == 2) {
					dir[0] = 1;
					dir[1] = 3;
				} else if (j == 3) {
					dir[0] = 1;
					dir[1] = 2;
				}
				int[][] copy = new int[N][M];
				for (int x = 0; x < N; x++)
					for (int y = 0; y < M; y++)
						copy[x][y] = map[x][y];
				setCCTV(watch(copy, r, c, dir), idx + 1);
			}
			break;
		case 4: // 한곳 뺸 네가지
			for (int j = 0; j < 4; j++) {
				int[] dir = new int[3];
				if (j == 0) { // 상,하, 좌, 우 뺴고
					dir[0] = 1;
					dir[1] = 2;
					dir[2] = 3;
				} else if (j == 1) {
					dir[0] = 0;
					dir[1] = 2;
					dir[2] = 3;
				} else if (j == 2) {
					dir[0] = 1;
					dir[1] = 0;
					dir[2] = 3;
				} else if (j == 3) {
					dir[0] = 1;
					dir[1] = 2;
					dir[2] = 0;
				}
				int[][] copy = new int[N][M];
				for (int x = 0; x < N; x++)
					for (int y = 0; y < M; y++)
						copy[x][y] = map[x][y];
				setCCTV(watch(copy, r, c, dir), idx + 1);
			}
			break;
		default:
			break;
		}
	}

	private static int getBlindSpots(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (map[i][j] == 0)
					cnt++;
		return cnt;
	}

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	private static int[][] watch(int[][] copy, int r, int c, int[] dir) {
		// 주어진 방향으로 감시
		for (int i = 0; i < dir.length; i++) {
			int direction = dir[i];
			int nr = r + dr[direction];
			int nc = c + dc[direction];

			while (isInBoundary(nr, nc) && copy[nr][nc] != 6) {
				if (copy[nr][nc] != 0) { // cctv 위치라면 표시 생략
					nr += dr[direction];
					nc += dc[direction];
					continue;
				}
				copy[nr][nc] = -1; // 감시 표시
				nr += dr[direction];
				nc += dc[direction];
			}
		}
		return copy;
	}

	private static boolean isInBoundary(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
