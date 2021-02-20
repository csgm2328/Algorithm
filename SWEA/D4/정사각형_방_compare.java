package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// bfs는 N^4 복잡도라 10^3인 N을 풀면 10^12승이라 1조 연산이라 시간초과임
// 큐 안써서 푼 다른 코드랑 로직은 똑같고 큐에 넣었다 뺐다를 헀냐 안헀냐의 차이인데
public class 정사각형_방_compare {
	static int[] dr = { -1, 1, 0, 0 }; // 내려가는게
	static int[] dc = { 0, 0, -1, 1 };
	
	static class xyc {
		int x, y;
		Pair ans;

		public xyc(int x, int y, int cnt, int room) {
			this.x = x;
			this.y = y;
			this.ans.Count = cnt;
			this.ans.Start = room;
		}
	}
	static class Pair implements Comparable<Pair>{
		int Count;
		int Start;
		
		@Override
		public int compareTo(Pair o) {
			return 0;
		}
		
		public Pair(int cnt, int room) {
			super();
			Count = cnt;
			Start = room;
		}
		
		public int compareTo(Pair o1, Pair o2) {
			if(o1.Count != o2.Count )
				return Integer.compare(o2.Count, o1.Count); //카운트는 제일 큰거
			else
				return Integer.compare(o1.Start, o2.Start);	//룸은 가장 작은거
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Queue<xyc> q = new LinkedList<xyc>();
			Pair ans = new Pair(0,Integer.MAX_VALUE);
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 방마다 큐에 넣으면서 시작
					boolean[][] visited = new boolean[N][N]; // 매 시작점마다 초기화해야니까
					visited[i][j] = true;
					q.offer(new xyc(i, j, 1, arr[i][j]));
					// bfs
					while (!q.isEmpty()) {
						int cu_row = q.peek().x;
						int cu_col = q.peek().y;
						int cu_cnt = q.peek().ans.Count;
								
						//갱신
						if(ans.compareTo(q.peek().ans) > 0) //카운트 더 큰거 오면 양수리턴, 룸은 더 작은거 오면 양수리턴
							ans = q.peek().ans;
						q.poll();

						for (int dir = 0; dir < 4; dir++) {
							int NextR = cu_row + dr[dir];
							int NextC = cu_col + dc[dir];

							int cnt = 0;
							if (NextR >= 0 && NextR < N && NextC >= 0 && NextC < N) {// 범위 안이면
								if (!visited[NextR][NextC] && arr[NextR][NextC] == arr[cu_row][cu_col] + 1) {// 미방문이고
																												// 다음이 1
																												// 더 크면
									visited[NextR][NextC] = true;
									q.offer(new xyc(NextR, NextC, cu_cnt + 1, arr[i][j]));
									break; // 어차피 1큰거는 4방향중에 하나다
									//큐를 안쓰고 위치갱신만하고 dir = 0으로 만들어서
									//이동한 위치에서 사방탐색 반복한다 갈 곳은 한곳뿐이라 가능하네
								}
							}
						}
					}
				}
			}
			sb.append("#" + tc + " " + ans.Count + " " + ans.Start + "\n");
		}
		System.out.print(sb.toString());
	}
}
