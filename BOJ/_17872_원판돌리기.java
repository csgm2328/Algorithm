import java.io.*;
import java.util.*;

// 원판 돌리기 (약 3시간)
// 원판을 배열로 표시하고 돌린후엔 제자리 복귀가 아니네?
// 돌리고나서 인접하며 같은 수 지우거나 수 조정
// 원본 배열 냅두고
// 복사 배열을 회전해서 
public class _17872_원판돌리기 {
	static int N, M, T;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][M]; // 배수라서 1행부터

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		int sum = 0;
		int total = N * M;
		for (int rot = 0; rot < T; rot++) { // 회전수
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			// 복사배열만들어서 해당 배수판만 회전
			int[][] temp = new int[N + 1][M];
			for (int i = 1; i <= N; i++) {
				if (i % x == 0) { // 배수이면 돌리기
					int index = 0;
					int cnt = 0;
					if (dir == 0) { // 시계방향
						index += k;
						while (cnt < M) {
							if (index >= M)
								index -= M;
							temp[i][index++] = arr[i][cnt];
							cnt++;
						}
					} else {// 반시계
						index = M - k;
						while (cnt < M) {
							if (index >= M)
								index -= M;
							temp[i][index++] = arr[i][cnt];
							cnt++;
						}
					}
				} else {
					for (int j = 0; j < M; j++)
						temp[i][j] = arr[i][j];
				}
			}
			// 회전 다하면 검사 행은 위아래 끝행 뺴고 열은 양옆
			boolean[][] zero = new boolean[N + 1][M]; // 0이 되는거 체크용
			sum = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					sum += temp[i][j];
					// 행 체크
					if (i == 1) {
						if (temp[i][j] != 0 && temp[i][j] == temp[i + 1][j])
							zero[i][j] = zero[i + 1][j] = true;
					} else if (i == N) {
						if (temp[i][j] != 0 && temp[i][j] == temp[i - 1][j])
							zero[i][j] = zero[i - 1][j] = true;
					} else {
						if (temp[i][j] != 0 && temp[i][j] == temp[i - 1][j])
							zero[i][j] = zero[i - 1][j] = true;
						if (temp[i][j] != 0 && temp[i][j] == temp[i + 1][j])
							zero[i + 1][j] = true;
					}
					// 열 체크
					if (j == 0) {
						if (temp[i][j] != 0 && temp[i][j] == temp[i][j + 1])
							zero[i][j] = zero[i][j + 1] = true;
						if (temp[i][j] != 0 && temp[i][j] == temp[i][M - 1])
							zero[i][j] = zero[i][M - 1] = true;
					} else if (j == M - 1)
						continue;
					else {
						if (temp[i][j] != 0 && temp[i][j] == temp[i][j + 1])
							zero[i][j] = zero[i][j + 1] = true;
						if (temp[i][j] != 0 && temp[i][j] == temp[i][j - 1])
							zero[i][j] = zero[i][j - 1] = true;
					}
				}
			}
			// 지울게 있는지 체크
			boolean flag = false;
			loop: for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if (zero[i][j]) {
						flag = true;
						break loop;
					}
				}
			}
			if (flag) { //0으로 만들기
				for (int i = 1; i <= N; i++) {
					for (int j = 0; j < M; j++) {
						if (zero[i][j]) {
							total--;
							temp[i][j] = 0;
						}
					}
				}
			} else { // 수조정: 큰거 -1 작은 수 +1
				if(total == 0) {
					sum = 0;
					break;
				}
				double avg = (double) sum / (double)total; //실수: 형변환을 한쪽에다만 해주면 안됨
				for (int i = 1; i <= N; i++) {
					for (int j = 0; j < M; j++) {
						if (temp[i][j] == 0)
							continue;
						if (avg < temp[i][j])
							temp[i][j]--;
						else if (avg > temp[i][j])
							temp[i][j]++;
					}
				}
			}
			// 원본으로 복사
			sum =0;
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					arr[i][j] = temp[i][j];
					sum += arr[i][j];
				}
			}
		}
		System.out.println(sum);
	}
}
