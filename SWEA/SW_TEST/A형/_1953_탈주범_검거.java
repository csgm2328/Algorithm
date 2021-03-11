package SW_TEST.A형;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//BFS를 특정 범위(간선수)까지 진행하는건 단순하지만
//맵에 1~7까지 표시된 경우를
//어떻게 잘 처리하는지가 관건인 문제
//상우하좌(4=0,1,2,3) - dr,dc의 순서와 매핑
//1~7의 모양을 상하좌우 중에 뚫려있는 것들을 가지고 있는 배열로 새롭게 표현한다
//그리고 BFS를 도는데
//모양을 표현한 배열의 크기만큼 돌면서(뚫려있는 곳을 모두 체크)
//옆에 모양의 배열 안에 연결 가능한(shape+2%4) 뚫린 곳이 있는지 체크한다

public class _1953_탈주범_검거 {
	static int N, M;
	static int[][] arr;
	private static boolean[][] visited;
	static int[][] pipes = { {}, { 1, 2, 3, 0 }, { 0, 2 }, { 3, 1 }, { 0, 1 }, { 1, 2 }, { 3, 2 }, { 3, 0 } }; // 파이프
	//(0~3)으로 해야 %4 연산이되지,,, 하
	//못찾겠으면 빨리 디버깅이라도하자

	static class xyc {
		int r, c, cnt;

		public xyc(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static int[] dr = {-1,0,1,0}; //4를 0으로 바꿨으니까 상우하좌
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());

			arr = new int[N][M];
			visited = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++)
					arr[i][j] = Integer.parseInt(st.nextToken());
			}
			Queue<xyc> q = new LinkedList<xyc>();
			q.offer(new xyc(r, c, 1)); // 시작
			visited[r][c] = true;
			int cnt = 1;
			
			while (!q.isEmpty()) {
				xyc cur = q.poll();
				if (cur.cnt == day)
					break;

				int shape = arr[cur.r][cur.c];
				//shape이 가지고 있는 뚫린곳을
				//1이면 상하좌우를 다체크해야된다 length만큼
				for (int i = 0; i < pipes[shape].length; i++) { // 그 모양의 뚫린 곳만큼 탐색
					int nr = cur.r + dr[pipes[shape][i]]; 
					int nc = cur.c + dc[pipes[shape][i]];
					//모양이 1일때 위쪽이면 4여서  dr,dc[3]로 넥스트칸을 보고
					//넥스트 쉐입을 알아낸후
					//쉐입이 가지고 있는 연결크기만큼 돌면서 해당 쉐입이 4와 연결되는 2가 있는지 검사한다
					if(0<= nr && nr < N && 0<= nc && nc < M) {
						if(visited[nr][nc]) continue;
						int next_shape = arr[nr][nc];
						for (int j = 0; j < pipes[next_shape].length; j++) {
							if (pipes[next_shape][j] == (pipes[shape][i]+2) %4) { //다음칸의 파이프 모양이 num+2%4 연결 가능
								visited[nr][nc] = true;
								q.offer(new xyc(nr, nc, cur.cnt+1));
								cnt++;
								break;
							}
						}
					}// end boundary check
				}
			}//end while
			sb.append("#" + tc + " " + cnt + "\n");
		} // end tc
		System.out.println(sb.toString());
	}
}