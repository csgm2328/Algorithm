package D2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//60m
public class 달팽이숫자 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); //출력 최적화 -18ms
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			int[][] arr = new int[N][N];
			int[] dr = { 0, 1, 0, -1 };
			int[] dc = { 1, 0, -1, 0 };

			int cnt = 1;
			int NextR = 0, NextC = 0;
			int dir = 0;
			arr[NextR][NextC] = cnt++;
			
			while (cnt <= N * N) { // 다 입력할떄까지 반복
				NextR += dr[dir];
				NextC += dc[dir];
				if (NextR >= 0 && NextR < N && NextC >= 0 && NextC < N && arr[NextR][NextC] == 0) {
					arr[NextR][NextC] = cnt++;
				} 
				else {
					//되돌리고
					NextR -= dr[dir];
					NextC -= dc[dir];
					dir = (dir + 1) % 4;
				}
			}
			sb.append("#" + tc + "\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(arr[i][j] + " ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}
}
