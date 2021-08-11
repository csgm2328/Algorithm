import java.io.*;
import java.util.*;

public class _1260_DFS와BFS {
	static int N, M, start;
	static int[][] adjMatrix;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
	
		adjMatrix = new int[N+1][N+1];
		for(int i =0 ; i< M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjMatrix[from][to] = 1; //양방향
			adjMatrix[to][from] = 1;
		}
		visited = new boolean[N+1];
		dfs(start);
		System.out.println(sb.toString());
		sb = new StringBuilder();
		Arrays.fill(visited, false);
		bfs(start);
		System.out.println(sb.toString());
	}
	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur + " ");
			for(int i =1 ; i<= N; i++) {
				if(visited[i] || adjMatrix[cur][i] == 0)
					continue;
				else {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
				
	}
	private static void dfs(int cur) {
		visited[cur] = true;
		sb.append(cur + " ");
		for(int i =1 ; i<=N; i++) {
			if(visited[i] || adjMatrix[cur][i] == 0)
				continue;
			else
				dfs(i);
		}		
	}
}
