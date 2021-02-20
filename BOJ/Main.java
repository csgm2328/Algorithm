import java.io.*;
import java.util.*;

public class Main {
	static int N, cnt; // 1 ~ 15
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 }; // 8방탐색
	static int[] dc = { 0, 0, -1, 1, -1, 1, 1, -1 };
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// N-Queen
		// 공격범위: 8방 끝까지
		for(int i = 0; i< N; i++) {
			arr = new int[N][N]; //새로운 위치로 시작할 때마다 초기화
			N_Queen(0, i, 1);
		}
		System.out.println(cnt);
	}

	static void N_Queen(int i, int j, int idx) {
		// 퀸 위치는 1
		arr[i][j] = 1;
		// 8방 공격범위는 -1로
		for (int dir = 0; dir < 8; dir++) {
			int cu_row = i; //다시 원래 위치부터
			int cu_col = j;
			while (true) {
				int NextR = cu_row + dr[dir];
				int NextC = cu_col + dc[dir];
				if ((NextR >= 0 && NextR < N && NextC >= 0 && NextC < N && arr[NextR][NextC] == 0)) {
					arr[NextR][NextC] = -1; // 범위 안 다 -1로
					cu_row = NextR; //위치 갱신
					cu_col = NextC;
				}
				else break;	// 다했으면 다른 방향
			}
		}

		if (idx == N) { //퀸 다 찾으면
			cnt++;
			return;
		}
		
		for (int c = 0; c < N; c++) { //가능한 열에 놓기
			if (arr[i+1][c] == 0) {	// 0인 부분만 놓을 수 있는 위치
				N_Queen(i+1, c, idx+1);
			}
		}
	}
}
