package D3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 기본_2일차_ladder1 {
	final static int ladder_size = 100;
	static int[][] ladder = new int[ladder_size][ladder_size];
	static boolean[][] visited = new boolean[ladder_size][ladder_size];
	
	static int[] dr = {0,0,1}; //내려가는게
	static int[] dc = {1,-1,0};
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		for(int tc = 1; tc<= 10; tc++) {
			int tNum =Integer.parseInt(br.readLine());
			for(int i = 0; i < ladder_size; i++) {
				String[] s = br.readLine().split(" ");
				for(int j = 0; j< ladder_size; j++) {
					ladder[i][j] = Integer.parseInt(s[j]);
				}
			}
			boolean star = false; // 2 찾은 여부
			
			for(int j = 0; j < ladder_size; j++) { //사다리타기 시작 위에서부터
				if(star) break;
				
				if(ladder[0][j] == 1) {
					// 초기화 필수
					for(boolean[] b: visited)
						Arrays.fill(b, false);
					
					
					int cu_row = 0;	//현재 행
					int cu_col = j;	//현재 열
					visited[cu_row][cu_col] = true;
					boolean flag = true; //더이상 진행여부 체크
					
					while(flag) {
						for(int i = 0; i< 3; i++) {	//방향 탐색
							int NextR = cu_row + dr[i];
							int NextC = cu_col + dc[i];
							
							if(NextR >= 0 && NextR < ladder_size && NextC >= 0 && NextC < ladder_size) { //범위 내
								if(ladder[NextR][NextC] == 1 && !visited[NextR][NextC]) {	//갈 길 있으면
									//다음칸으로
									visited[NextR][NextC] = true;
									cu_row = NextR;	//현재 행 갱신
									cu_col = NextC;
									flag = true;
									break; //길 찾으면 다른거 볼필요없음
								}
								else if(ladder[NextR][NextC] == 2) {//찾음
									System.out.println("#" + tNum + " " + j);
									star = true; // 아예 종료
									flag = false;
									break;
								}
							}
							flag = false;
						}
					}
				}
			}
		}
	}
}
