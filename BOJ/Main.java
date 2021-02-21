import java.io.*;
import java.util.*;

public class Main {
	static int N, cnt; // 1 ~ 15
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 }; // 8방탐색
	static int[] dc = { 0, 0, -1, 1, -1, 1, 1, -1 };
	static int[] paper = { 5, 5, 5, 5, 5, 5 };
	static int[][] arr;
	static boolean[][] visited;
	static int Min = 0;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 10;
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				if (arr[i][j] == 1) {
//					int garo = 1;
//					int sero = 1;
//					int k = j+1;
//					for (; k < N; k++) {
//						if (arr[i][k] == 1)
//							garo++;
//						else
//							break;
//					}
//					k--;
//					int z = i+1;
//					for (; z < z + garo; z++) {
//						if (arr[z][k] == 1)
//							sero++;
//						else break;
//					}
//					// 가로세로 길이 재고
//					//cnt 세기
//					
//					// 더 작은 길이에 맞춰서 색종이 붙이기
////					int square_size = Integer.min(garo, sero);
//					int cnt =0;
//					for (int x = i; x< x+garo; x++) {
//						for (int y = j; y < j + sero; y++) {
//							if(arr[x][y] != 1) 
//								break;
//							cnt++;
//						}
//					}
//					//cnt에 따른 크기 선정
//					int square_size = 1;
//					while(square_size* square_size <= cnt) {
//						square_size++;
//					}
//					
//					//그리고 정사각형 찾기
//					//가로가 길떄는 가로를 움직이고
//					// attach
//					boolean attach_flag = true;
//					while (true) {
//						if (!attach_flag) { // 붙이기 실패하면 더작은 색종이로
//							attach_flag = true;
////							square_size--;
//							if (garo > sero)
//								
//						}
//						for (int x = i + square_size - 1; x >= i; x--) {
//							for (int y = j; y < j + square_size; y++) {
//								if (arr[x][y] != 1) {
//									attach_flag = false;
//									break;
//								}
//							}
//							if(!attach_flag)
//								break;
//						}
//						if (attach_flag) // 붙일수 있으면 나오고
//							break;
//					}
//					// 붙이기 처리
//					for (int x = i + square_size - 1; x >= i; x--)
//						for (int y = j; y < j + square_size; y++)
//							arr[x][y] = 0;
//
//					paper[square_size]--;
//					if (paper[square_size] < 0) { // 주어진 거 초과로 사용해야 되면 종료
//						System.out.print(-1);
//						System.exit(1);
//					}
//					Min++;

//					if(garo >= 2 && sero >=2) {
//						//그 범위가 꽉차있는지 검사
//						int cnt =0;
//						for(int x = i; x < i+garo;x++) {
//							for(int y = j; x < j+garo;y++) {
//								if(arr[x][y] == 1)
//									cnt++;
//							}
//						}
//						if(cnt == garo*sero)
//							flag = true;
//					}
//					if(flag) {
//						paper[garo]--; //사용한 색종이
//						Min++; //색종이 하나추가
//					}
//					else {// 1일때는
//						paper[garo]--; //사용한 색종이
//						
//					}
//				}
//					dfs(i,j,1,1);
//				}
//			}
//		}
		System.out.println(Min);
	}

	static void dfs(int i, int j, int garo, int sero) {
		visited[i][j] = true;

		int NextC = j + 1; // 오른쪽으로 쭉가면서 가로길이 체크
		if (!visited[i][NextC] && arr[i][NextC] == 1)
			dfs(i, NextC, garo, sero + 1);

		int NextR = i + 1;
		if (!visited[NextR][j] && arr[NextR][j] == 1)
			dfs(NextR, j, garo + 1, sero);
	}

	static void N_Queen(int i, int j, int idx) {
		// 퀸 위치는 1
		arr[i][j] = 1;
		// 8방 공격범위는 -1로
		for (int dir = 0; dir < 8; dir++) {
			int cu_row = i; // 다시 원래 위치부터
			int cu_col = j;
			while (true) {
				int NextR = cu_row + dr[dir];
				int NextC = cu_col + dc[dir];
				if ((NextR >= 0 && NextR < N && NextC >= 0 && NextC < N && arr[NextR][NextC] == 0)) {
					arr[NextR][NextC] = -1; // 범위 안 다 -1로
					cu_row = NextR; // 위치 갱신
					cu_col = NextC;
				} else
					break; // 다했으면 다른 방향
			}
		}

		if (idx == N) { // 퀸 다 찾으면
			cnt++;
			return;
		}

		for (int c = 0; c < N; c++) { // 가능한 열에 놓기
			if (arr[i + 1][c] == 0) { // 0인 부분만 놓을 수 있는 위치
				N_Queen(i + 1, c, idx + 1);
			}
		}
	}
}
