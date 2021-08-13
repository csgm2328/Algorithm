package Lv2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 2크기의 마름모 구역안에 P 있는지 찾는 bfs

public class _2021_카카오_인턴쉽_2_거리두기_확인하기 {
	public static void main(String[] args) {
		int[] x = solution(new String[][] {
			{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
			{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
			{"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"},
			{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
			{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
			}
		);
		System.out.println(Arrays.toString(x));
	}
	static int N;
	static class rc{
		int r,c,cnt;

		public rc(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static int[] solution(String[][] places) {
		int[] answer = new int[places.length];
		N = places[0].length; //5x5
		
		for(int tc = 0; tc < places.length;tc++) { //한 행이 한케이스
			char[][] map = new char[N][N];
			
			
			for(int i =0; i< N;i++){
				for(int j = 0; j < N; j++) { //places[tc][i].length()
					map[i][j] = places[tc][i].charAt(j);
					
				}
			}
			boolean flag = true;
			loop: for(int i =0; i< N;i++){
				for(int j = 0; j < N; j++) { //places[tc][i].length()
					if(map[i][j] == 'P') {
						Queue<rc> q= new LinkedList<rc>();
						boolean[][] visited = new boolean[N][N];
						q.offer(new rc(i,j,0));
						visited[i][j] = true;
						if(bfs(q,map,visited) == 0) {
							answer[tc] = 0;
							flag =false;
							break loop;
						}
					}
				}
			}
			if(flag)
			answer[tc] = 1;
		}
		return answer;
	}
	private static int bfs(Queue<rc> q, char[][] map, boolean[][] visited) {
		while(!q.isEmpty()){
			rc cur =q.poll();
			if(cur.cnt >= 2) //맨하탄거리 넘는 거 나오면 
				break;
			for(int dir =0; dir< 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				if(isInBoundary(nr, nc) && !visited[nr][nc]) {
					if(map[nr][nc] == 'O') {
						q.offer(new rc(nr,nc,cur.cnt+1));
						visited[nr][nc] = true;
					}
					else if(map[nr][nc] == 'P') {
						q.clear();
						return 0;
					}
				}
			}
		}
		return 1;
	}
	private static boolean isInBoundary(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
