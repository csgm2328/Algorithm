import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//안에서 밖으로 들어가면서 위치조정
//150m
//포문 4개로 짜면 쉬움
public class _16926_배열돌리기 {
	static int N, M;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static int[][] rotation(int[][] arr, int n, int m) {
		int[][] ans = new int[N][M];
		int i = 2; //
		int ro = 0; // 안으로 파고드는 횟수
		while (Math.min(n, m) - i >= 0) {
			int cnt = n * m - (n - i) * (m - i); // 밖에 둘레
			int in = 0; // 카운트까지 입력
			int arri = 0 + ro, arrj = -1 + ro; // 기존 배열 시작위치, 정위치보다 한칸 앞
			int ansi = 2 + ro, ansj = 0 + ro; // 답 배열 시작위치
			int ansDir = 3; //
			int arrDir = 0;

			while (in < cnt) { // 다 입력할떄까지 반복
				arri += dr[arrDir]; // 한칸 앞이였으니까 밀고 시작
				arrj += dc[arrDir];
				if (arri >= ro && arri < n + ro && arrj >= ro && arrj < m + ro) { // 파고드는 횟수마다 달라지는 범위
					boolean flag = false;
					while (!flag) { // 기존배열이 가능한 위치에 오면 답배열도 가능한 위치에 있을떄 까지 반복
						ansi += dr[ansDir];
						ansj += dc[ansDir];
						if (ansi >= ro && ansi < n + ro && ansj >= ro && ansj < m + ro) {
							ans[ansi][ansj] = arr[arri][arrj];
							in++;
							flag = true;
						} else {
							// 범위 나가면 되돌리기
							ansi -= dr[ansDir];
							ansj -= dc[ansDir];
							ansDir = (ansDir + 1) % 4; // 다음 방향으로
						}
					}
				} else {
					arri -= dr[arrDir];
					arrj -= dc[arrDir];
					arrDir = (arrDir + 1) % 4;
				}
			}
			n -= i; // 2칸씩 줄어드는 범위
			m -= i;
			ro++; // 안으로 파고듬
		}

		return ans;
	}

//	static void rotation_for(int n, int m) {
//		for (int ro = 0; ro < R; ro++) {
//			for (int i = 0; i < Math.min(n, m) / 2; i++) {
//				
//			}
//		}
//	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < R; i++)
			arr = rotation(arr, N, M);

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				sb.append(arr[x][y] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
