import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 빗물
// 빗물이 고이려면 양쪽에 벽이 있어야 하고
// 크고 작고 크고를 만족해야함
// 이 방법말고 그냥 칸 탐색하면서 반복적으로 확인하는 거로

public class _14719_빗물 {
	static int N, M; // 1 ~ 500
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < M; j++) {// 맵 셋팅
			int walls = Integer.parseInt(st.nextToken());
			for (int i = 0; i < walls; i++) {
				map[N - i - 1][j] = 1;
			}
		}
		// 행 탐색하면서 기둥이 연결되면 그곳 물 찬상태로
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					int x = Possible(i, j + 1); // 그 다음열부터 물찰 수 있는지 검사
					if (x != -1) {
						cnt += x; //최적화: 건너뛰기
						j += x;
					} else
						break;
				}
			}
		}
		System.out.println(cnt);
	}
	//해당지점의 다음열부터 또다른 기둥이 있어서 해당 행에 물이 채워질 수 있는지 검사
	private static int Possible(int i, int j) {
		int idx = j;
		while (j < M) {
			if (map[i][j] == 1)
				return j - idx; //물 채운 칸 수 리턴
			j++;
		}
		return -1; 
	}
}