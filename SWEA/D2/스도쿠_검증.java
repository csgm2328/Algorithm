package D2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스도쿠_검증 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = 9;
			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 칸 검증
			int flag = 1;
			for (int i = 0; i < N / 3; i++) {
				if (flag == 0)
					break;
				for (int j = 0; j < N / 3; j++) {
					if (flag == 0)
						break;
					boolean[] check = new boolean[N + 1];
					for (int k = i * 3; k < i * 3 + 3; k++) {
						if (flag == 0)
							break;
						for (int z = j * 3; z < j * 3 + 3; z++) {
							if (check[arr[k][z]]) {	//실수좀 하지마라 k z를 안했네
								flag = 0;
								break;
							} else
								check[arr[k][z]] = true;
						}
					}
				}
			}
			// 가로 검증
			if (flag != 0) {	//까먹지말고 계속 해야할때만
				for (int i = 0; i < N; i++) {
					if (flag == 0)
						break;
					boolean[] check = new boolean[N + 1];
					for (int j = 0; j < N; j++) {
						if (check[arr[i][j]]) {
							flag = 0;
							break;
						} else
							check[arr[i][j]] = true;
					}
				}
			}
			// 세로 검증
			if (flag != 0) {
				for (int i = 0; i < N; i++) {
					if (flag == 0)
						break;
					boolean[] check = new boolean[N + 1];
					for (int j = 0; j < N; j++) {
						if (check[arr[j][i]]) {
							flag = 0;
							break;
						} else
							check[arr[j][i]] = true;
					}
				}
			}
			sb.append("#" + tc + " " + flag + "\n");
		}
		System.out.println(sb.toString());
	}
}
