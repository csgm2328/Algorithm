import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2669_직사각형면적합 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static long sum;
	static int[][] arr = new int[1001][1001];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 4;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int row_s = Integer.parseInt(st.nextToken()); 
			int col_s = Integer.parseInt(st.nextToken());
			int row_e = Integer.parseInt(st.nextToken());
			int col_e = Integer.parseInt(st.nextToken());

			for (int j = row_s; j < row_e; j++) {
				for (int k = col_s; k < col_e; k++) {
					if (arr[j][k] == 0) {
						arr[j][k] = 1;
						sum++;
					}
				}
			}
		}
		
		System.out.print(sum);
	}
}
