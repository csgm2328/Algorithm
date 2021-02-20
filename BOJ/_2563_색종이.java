
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//그냥 배열에다 표시하면 끝이지만
//계산은 너무 힘듬
public class _2563_색종이 {
	static int[][] arr = new int[100][100];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()) - 1;
			int M = Integer.parseInt(st.nextToken()) - 1;

			for (int i = N; i < N + 10; i++) {
				for (int j = M; j < M + 10; j++) {
					if (arr[i][j] == 0)
						arr[i][j] = 1;
				}
			}
		}
		//1 찾기
		int cnt =0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (arr[i][j] == 1)
					cnt++;
			}
		}
		System.out.print(cnt);
		br.close();
	}
}
