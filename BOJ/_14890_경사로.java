import java.io.*;
import java.util.*;

// 12:55 ~ 2:50
// 경사로
public class _14890_경사로 {
	static int N, L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		// needs로 구분하지 말고 높이 비교로 구분하자
		int sum = 0;
		// 가로
		for (int i = 0; i < N; i++) {
			int cur = map[i][0];
			int len = 1;
			boolean needs = false;
			for (int j = 1; j < N; j++) {
				if (cur == map[i][j]) { // 다음칸이랑 같음
					len++;
					if (needs && len >= L) { // 경사로 놓을수 있는지 검사
						needs = false;
						len -= L;
					}
				} else if (cur == map[i][j] - 1) { // 다음이 한칸높을 땐 needs상관없이 검사
					if (!needs) {
						if (len >= L) {
							needs = false;
							len = 1;
						} else { // 경사로 놓기 실패
							needs = true;
							break;
						}
					} else {
						if (len >= 2 * L) { //
							needs = false;
							len = 1;
						} else {
							needs = true;
							len =0;
							break;
						}
					}

				} else if (cur == map[i][j] + 1) { // 다음이 한칸낮음
					if (needs) {
						if (len < L)
							break;
					}
					needs = true; // 필요한 상태로변경
					len = 1;
				} else { // 두칸이상 차이
					needs = true;
					len = 0;
					break;
				}
				cur = map[i][j];
			}
			if (!needs)
				sum++;
			else if (needs && len >= L)
				sum++;
		}
		for (int j = 0; j < N; j++) {
			int cur = map[0][j];
			int len = 1;
			boolean needs = false;
			for (int i = 1; i < N; i++) {
				if (cur == map[i][j]) { // 다음칸이랑 같음
					len++;
					if (needs && len >= L) { // 경사로 놓을수 있는지 검사
						needs = false;
						len -= L;
					}
				} else if (cur == map[i][j] - 1) { // 다음이 한칸높을 땐 needs상관없이 검사
					if (!needs) {
						if (len >= L) {
							needs = false;
							len = 1;
						} else { // 경사로 놓기 실패
							needs = true;
							break;
						}
					} else {
						if (len >= 2 * L) { //
							needs = false;
							len = 1;
						} else {
							needs = true;
							len =0;
							break;
						}
					}
				} else if (cur == map[i][j] + 1) { // 다음이 한칸낮음
					if (needs) {
						if (len < L)
							break;
					}
					needs = true; // 필요한 상태로변경
					len = 1;
				} else { // 두칸이상 차이
					needs = true;
					len = 0;
					break;
				}
				cur = map[i][j];
			}
			if (!needs)
				sum++;
			else if (needs && len >= L)
				sum++;
		}
		System.out.println(sum);
	}
}
