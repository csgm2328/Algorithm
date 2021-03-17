package SW_TEST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _1767_프로세서_연결하기_A형 {
	static int N;
	static int line_MIN, connect_MAX;

	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			line_MIN = Integer.MAX_VALUE;
			connect_MAX = -1; // 가장자리 빼고 연결된 개수
			N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];

			List<int[]> cores = new ArrayList<int[]>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 1) {
						if (i == 0 || i == N - 1 || j == 0 || j == N - 1) // 가장자리 배제
							continue;
						cores.add(new int[] { i, j });
					}
				}
			}
			backtrack(arr, cores, 0, 0, 0);
			line_MIN = line_MIN == Integer.MAX_VALUE ? 0 : line_MIN;
			sb.append("#" + tc + " " + line_MIN + "\n");
		} // end tc
		System.out.print(sb.toString());
	}

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	private static void backtrack(int[][] map, List<int[]> cores, int connect_cnt, int line_sum,int start) {
		// backtrack: 진행중에 전선수가 초과하면 return;
		if (line_MIN < line_sum) {
			return;
		}
		// 코어 리스트를 돌면서
		// 해당 코어를 일부러 포기하고 다른 걸 연결할때 답인경우
		// 조합으로  coresize C 0~연결할 수있는만큼
		// 해당 코어를 포기하고도 다음 for문으로 마지막 코어까지 진행되고 답이 될수 있는경우 체크함
		for (int i = start; i < cores.size(); i++) {
			int[] x = cores.get(i);
			// 4방탐색하고
			for (int dir = 0; dir < 4; dir++) { 
				int nr = x[0] + dr[dir];
				int nc = x[1] + dc[dir];
				if (checkConnect(map, new int[] { nr, nc }, dir)) { // 그 방향으로 전선 놓을 수있으면
					int[][] copy = new int[N][N];
					for (int k = 0; k < N; k++)
						for (int z = 0; z < N; z++)
							copy[k][z] = map[k][z];
					// 가능하면 copy 배열로 전선 연결해서 다음 코어로
					backtrack(connectCore(copy, new int[] { nr, nc }, dir), cores, connect_cnt + 1,
							line_sum + line_temp, i+1);
				}
			}
		} // end cores.size
			// 코어 다봄
			// 연결이 원래 연결보다 많으면 갱신 같을때는 간선수가 적으면 갱신
		if (connect_MAX < connect_cnt) {
			connect_MAX = connect_cnt;
			line_MIN = line_sum;
		} else if (connect_MAX == connect_cnt) {
			if (line_MIN > line_sum)
				line_MIN = line_sum;
		}
	}

	static int line_temp;

	private static int[][] connectCore(int[][] map, int[] x, int dir) {
		// 전선 놓기
		// 놓을때 연결한 전선 수 세야함
		line_temp = 0;
		int nr = x[0];
		int nc = x[1];
		while (0 <= nr && nr < N && 0 <= nc && nc < N) {
			map[nr][nc] = 2;
			line_temp++;
			nr += dr[dir];
			nc += dc[dir];
		}
		return map;
	}

	private static boolean checkConnect(int[][] map, int[] x, int dir) {
		// 가장자리 까지 전선을 놓을 수 있는지
		int nr = x[0];
		int nc = x[1];
		boolean flag = true;
		while (0 <= nr && nr < N && 0 <= nc && nc < N) {
			if (map[nr][nc] == 0) {
				nr += dr[dir];
				nc += dc[dir];
			} else {
				flag = false;
				break;
			}
		}
		return flag ? true : false;
	}
}
