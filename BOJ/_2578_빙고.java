import java.io.*;
import java.util.*;

//빙고
//10:45 ~ 11:40
public class _2578_빙고 {
	static StringBuilder sb = new StringBuilder();
	static int N;
//	static long min, max; // 1조 + 100만
//	static long sum;
	static List<int[]> me;
	static int[][] bingo, na;
	static int[] MC; // 위치알려고 한줄로
//	static boolean flag = true;
//	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 5;
		me = new ArrayList<int[]>(N * N);
		na = new int[N * N + 1][];
		MC = new int[N * N];
		bingo = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
//				me.add(Integer.parseInt(st.nextToken()), new int[] { i, j });
				na[Integer.parseInt(st.nextToken())] = new int[] { i, j };
			}
		}
		int index = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				MC[index++] = Integer.parseInt(st.nextToken());
			}
		}
		index = 0;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
//				int[] x = me.get(MC[index++]);
				int[] x = na[MC[index++]];
				bingo[x[0]][x[1]] = 1;
				cnt++;
				if (cnt >= 12) { // 맞은게 12개 넘을때부터 체크
					if (bingocheck()) {
						System.out.print(index);
						System.exit(0);
					}
				}
			}
		}
	}

	private static boolean bingocheck() {
		int sum = 0;
		if (diag_check1())
			sum++;
		if (diag_check2())
			sum++;
		for (int i = 0; i < N; i++) {
			if (garo_check(i)) {
				sum++;// 가로 체크
				if (sum >= 3) // 합이 3이면 빙고!
					return true;
			}
		}
		for (int j = 0; j < N; j++) {
			if (sero_check(j)) {
				sum++;// 세로 체크
				if (sum >= 3) // 합이 3이면 빙고!
					return true;
			}
		}

		return false;
	}

	private static boolean sero_check(int cu_col) {
		for (int i = 0; i < N; i++) {
			if (bingo[i][cu_col] != 1)
				return false;
		}
		return true;
	}

	private static boolean garo_check(int cu_row) {
		for (int i = 0; i < N; i++) {
			if (bingo[cu_row][i] != 1)
				return false;
		}
		return true;
	}

	// 대각선은 두개밖에없으니까 먼저 따로 해주자 \ 방향
	private static boolean diag_check1() {
		for (int i = 0; i < N; i++) {
			if (bingo[i][i] != 1)
				return false;
		}
		return true;
	}

	// /방향
	private static boolean diag_check2() {
		for (int i = 0; i < N; i++) {
			if (bingo[N - 1 - i][i] != 1)
				return false;
		}
		return true;
	}
}
