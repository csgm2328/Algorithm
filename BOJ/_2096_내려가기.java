import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

//문제 잘읽자 숫자 3개 고정이다
public class _2096_내려가기 {
	static BufferedReader input;
//	static StringBuilder output;
	static StringTokenizer st;
	static int N;
	static int[] dr = { 0, -1, 1 }; //아래, 아래왼, 아래우
	private static int MAX = -1, MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		// global init
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken()); // 1 ~ 10만
		int[] saveMax = new int[3]; // 저장
		int[] saveMin = new int[3];

		// 첫칸은 먼저저장
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < 3; i++) {
			saveMax[i] = saveMin[i] = Integer.parseInt(st.nextToken());
			if (N == 1) { //1일 때
				if (saveMax[i] > MAX) {
					MAX = saveMax[i];
				}
				if (saveMin[i] < MIN) {
					MIN = saveMin[i];
				}
			}
		}

		// 다음 칸부터
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			int[] input = new int[3]; // 입력
			for (int j = 0; j < 3; j++) {
				// 실수포인트 -> 입력과 save를 거꾸로봐서 입력하나 들어오고 save를 봐도 되는 줄 알았는데
				//			입력을 다받고 save 하나씩 보는 거였다
				input[j] = Integer.parseInt(st.nextToken());
			}
			int[] tempMax = new int[3]; // 임시
			int[] tempMin = new int[3]; // 임시
			Arrays.fill(tempMin, Integer.MAX_VALUE);
			for(int j = 0; j< 3; j++) {
				// 최대 저장
				for (int dir = 0; dir < 3; dir++) {
					int nr = j + dr[dir];
					if (isInBoundary(nr) && tempMax[nr] < input[nr] + saveMax[j])
						tempMax[nr] = input[nr] + saveMax[j];
				}
				// 최소 저장
				for (int dir = 0; dir < 3; dir++) {
					int nr = j + dr[dir];
					if (isInBoundary(nr) && tempMin[nr] > input[nr] + saveMin[j])
						tempMin[nr] = input[nr] + saveMin[j];
				}
			}

			// 슬라이딩 윈도우: save 갱신
			for (int k = 0; k < 3; k++) {
				saveMax[k] = tempMax[k];
				if (i == N - 1) {
					if (saveMax[k] > MAX) {
						MAX = saveMax[k];
					}
				}
			}
			// save갱신
			for (int k = 0; k < 3; k++) {
				saveMin[k] = tempMin[k];
				if (i == N - 1) {
					if (saveMin[k] < MIN) {
						MIN = saveMin[k];
					}
				}
			}
		} // end input
		System.out.print(MAX + " " + MIN);
	}

	private static boolean isInBoundary(int r) {
		return 0 <= r && r < 3;
	}
}
