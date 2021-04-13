package D4;
import java.beans.Visibility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 보급로 
// bfs인거 같지만 복구해야하는데 드는 시간을 가중치로 생각하면
// 최단경로 그래프 문제가 된다
// 정점을 N개로 보는게 아니라 N^2개로보면서 2차원으로 생각한다
// 그러고 각 간선(연결된 정점)이 최대 4개인 4방탐색으로 도착지까지 가는 최단경로를 구한다

// 아이디어
// 1. BFS
// 2. 다익스트라 pq ElogV = (4*N^2)log(N^2)
// 3. 다익스트라 pq안쓰면 (V^4) --> minTime찾는데 N^2 그 동작을 정점개수만큼 반복
public class _1249_보급로 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static int N, arr[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				char[] c = br.readLine().toCharArray();
				for (int j = 0; j < N; j++)
					arr[i][j] = c[j] - '0';
			}
			int repair_time = bfs(); //150ms
//			int repair_time = dijkstra_PQ(); //157ms
			sb.append("#" + tc + " " + repair_time + "\n");
		}
		System.out.println(sb.toString());
	}

	private static int dijkstra_PQ() {
		//pq
		boolean[][] visited = new boolean[N][N];
		int[][] minTime = new int[N][N];
		for( int[] x: minTime)
			Arrays.fill(x, Integer.MAX_VALUE);
		minTime[0][0] = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		pq.offer(new int[] {0,0,0}); //시작점
		
		while(true) {
			int[] cur = pq.poll();
			int r = cur[0];
			int c = cur[1];
			int time = cur[2];
			
			if(visited[r][c]) continue;
			visited[r][c] = true;
			if(r == N-1 && c == N-1) return time;
			
			for(int dir = 0; dir<4; dir++) {
				int nr = cur[0] + dr[dir];
				int nc = cur[1] + dc[dir];
				
				if(isInBoundary(nr, nc) && minTime[nr][nc] > minTime[cur[0]][cur[1]] + arr[nr][nc]) {
					minTime[nr][nc] = minTime[cur[0]][cur[1]] + arr[nr][nc];
					pq.offer(new int[] {nr,nc, minTime[nr][nc]});
				}
			}
		}
	}

	private static int bfs() {
		int[][] visited = new int[N][N];
		for(int[] x: visited)
			Arrays.fill(x, Integer.MAX_VALUE);
		
		Queue<int[]> q= new LinkedList<int[]>();
		q.offer(new int[] { 0,0}); // 시작점
		visited[0][0] = 0;
		int time = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[0] == N-1 && cur[1] == N-1) {
				if(time > visited[cur[0]][cur[1]]) //BFS는 Q 빌때까지 기다려야한다
					time = visited[cur[0]][cur[1]];
			}
			for(int dir = 0; dir<4; dir++) {
				int nr = cur[0] + dr[dir];
				int nc = cur[1] + dc[dir];
				
				if(isInBoundary(nr, nc) && visited[nr][nc] > visited[cur[0]][cur[1]] + arr[nr][nc]) {
					visited[nr][nc] = visited[cur[0]][cur[1]] + arr[nr][nc];
					q.offer(new int[] {nr,nc});
				}
			}
		}
		return time;
	}
	private static boolean isInBoundary(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
