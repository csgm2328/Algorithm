import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _10163_색종이 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int last_sum;
	static int[][] arr = new int[101][101];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int row_s = Integer.parseInt(st.nextToken());
			int col_s = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());

			last_sum =0;
			for (int j = row_s; j < row_s + width; j++) {
				for (int k = col_s; k < col_s + height; k++) {
					arr[j][k] = i; // 색종이 번호로 색칠 근데 뒤에서 덮어질 수 있음
					last_sum++; // 마지막은 안세도록
				}
			}
		}//end input
		
		for (int n = 1; n <= N - 1; n++) { // 색종이 개수만큼
			int sum = 0;
			for (int i = 0; i < 101; i++) {
				for (int j = 0; j < 101; j++) {
					if (arr[i][j] == n) // 해당 색종이 번호로 색칠된 거
						sum++;
				}
			}
			sb.append(sum + "\n");
		}
		sb.append(last_sum);
		System.out.print(sb.toString());
	}
}
