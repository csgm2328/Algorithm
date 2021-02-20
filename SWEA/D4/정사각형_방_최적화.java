package D4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//정사각형 방 최적화하기
//2차원을 값으로 가지는 1차원배열로
//입력 값을 인덱스로 놓고 그 입력 값의 위치를 배열에 저장한다.
//이렇게 하면 바로 뒤의 위치로 4방 탐색 한번으로 갈 수 있는지만 체크하면 됨
//거기다 진행한 만큼은 또 볼 필요도 없음
//1146ms --> 300ms
public class 정사각형_방_최적화 {
	static int N;
	static int curIdx, maxIdx, lastIdx, max;
	static int[][] arr;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 출력 최적화 -18ms

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 고객 수

			arr = new int[N * N + 1][]; // 1부터시작

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int idx = Integer.parseInt(st.nextToken()); // 값을 인덱스로
					arr[idx] = new int[] { i, j }; // 그 값의 위치를 배열에 저장
				}
			}
			for (int num = 1; num < N * N; num++) {// 마지막전까지 카운트 탐색
				curIdx = num;
				move(num, 1);
				num = lastIdx; // 4에서 끝났다면 5부터 검사하면 됨
			}
			sb.append("#" + tc + " " + maxIdx + " " + max + "\n");

			max = -1;
			maxIdx = -1;
			lastIdx = -1;
		}
		System.out.println(sb.toString());
	}

	static void move(int num, int cnt) {
		boolean flag = false;
		
		if (num < N * N) { // 시작위치부터 어디까지 갈수 있는지
			for (int dir = 0; dir < 4; dir++) {
				int NextR = arr[num][0] + dr[dir];
				int NextC = arr[num][1] + dc[dir];

				if (NextR >= 0 && NextR < N && NextC >= 0 && NextC < N) {// 범위 안이면
					if (NextR == arr[num + 1][0] && NextC == arr[num + 1][1]) {
						cnt++;
						flag = true;
						move(num + 1, cnt); // 다음 위치로 이어서
						break;
					}
				}
			}
		}
		
		if (!flag) { // 끝
			if (max < cnt) {
				max = cnt;
				maxIdx = curIdx;
			}
		}
		lastIdx = num;
		return;
	}
}
