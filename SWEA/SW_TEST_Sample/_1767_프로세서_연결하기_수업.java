package SW_TEST_Sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class _1767_프로세서_연결하기_수업 {
	static int T, N;
	static int totalcore;
	static int[][] arr;
	static List<int[]> core_loc;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	private static int Max;
	private static int Min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			//전역변수 초기화
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			core_loc = new ArrayList<int[]>();
			Max = -1;
			Min = Integer.MAX_VALUE;
			totalcore = 0;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 1 && !(i == 0 || i == N - 1 || j == 0 || j == N - 1)) { // 가장 자리배제
						core_loc.add(new int[] { i, j });
						totalcore++;
					}
				}
			}
			// 부분집합 구하기
			powerset(0, 0, 0);
			sb.append("#" + tc + " " + Min + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void powerset(int index, int Oncore, int line_sum) {
		// TODO Auto-generated method stub
		if(index == totalcore) {
			if (Max < Oncore) { //
				Max = Oncore;
				Min = line_sum;
				//System.out.printf("%d, %d\n", Max, Min);
				
			} else if (Max == Oncore)
				Min = Math.min(Min, line_sum);
			return;
		}
		
		//4방 탐색 결과로 부분탐색하기
		int cu_row = core_loc.get(index)[0]; //탐색을 돌지말고 따로 리스트로 관리하면서 인덱스로 접근
		int cu_col = core_loc.get(index)[1];
		for(int dir =0; dir < 4; dir++) {
			if(isConnectable(cu_row,cu_col,dir)) { //연결가능하면 선택
				int line_temp = SetStatus(cu_row, cu_col, dir, 2); //전선표시
				powerset(index+1, Oncore+1, line_sum+line_temp);
				SetStatus(cu_row, cu_col, dir, 0); //전선 지우기
				if(line_temp == 1) break; //1칸만 가서 연결되면 다른거 안봐도 됨
			}
			else { //비선택
				powerset(index+1, Oncore, line_sum);
			}
		}
	}

	private static boolean isConnectable(int cu_row, int cu_col, int dir) {
		cu_row += dr[dir];
		cu_col += dc[dir];
		while (0 <= cu_row && cu_row < N && 0 <= cu_col && cu_col < N) { //끝까지 가보기
			if (arr[cu_row][cu_col] == 0) {
				cu_row += dr[dir];
				cu_col += dc[dir];
			} else
				return false; // 밖으로 나가기 전에
		}
		return true;
	}

	static private int SetStatus(int cu_row, int cu_col, int dir, int status) {
		int line_temp =0;
		cu_row += dr[dir];
		cu_col += dc[dir];
		while (0 <= cu_row && cu_row < N && 0 <= cu_col && cu_col < N) { //끝까지 가보기
			arr[cu_row][cu_col] = status;
			cu_row += dr[dir];
			cu_col += dc[dir];
			line_temp++;
		}
		return line_temp;
	}
}
