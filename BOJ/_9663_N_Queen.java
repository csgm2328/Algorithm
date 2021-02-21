
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _9663_N_Queen {
	static int N, cnt;
	static int[] col_of_Queen;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col_of_Queen = new int[N + 1]; // 1 ~ N;

		int cu_row = 1;
		for (int i = 1; i <= N; i++) {
			col_of_Queen[cu_row] = i;
			backtracking(cu_row + 1);
		}
		System.out.print(cnt);
	}

	static void backtracking(int cu_row) {
		if (cu_row > N) {
			cnt++;
//			System.out.println(Arrays.toString(col_of_Queen));
			return;
		}

		for (int col = 1; col <= N; col++) { // 현재 행에 놓을 후보들
			if (isAvailable(col, cu_row)) { // 이전 퀸들과 비교
				col_of_Queen[cu_row] = col;
				backtracking(cu_row + 1);
			}
		}
	}

	// 행에 놓을 후보들이 이전 퀸들과 싹 다 안겹치는지를 검사해야되는데
	// 이 순서를 반대로 생각해서 헤맸다
	static boolean isAvailable(int col, int cu_row) {
		boolean flag = false;
		for (int row = 1; row < cu_row; row++) { // 이전 행들의 퀸 위치
			if (col_of_Queen[row] == col || // 행과 대각선 체크
					Math.abs(col_of_Queen[row] - col) == Math.abs(row - cu_row)) {
				return false;
			}
		}
		return true;
	}
}