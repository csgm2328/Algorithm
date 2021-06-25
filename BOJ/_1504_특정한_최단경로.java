import java.io.*;
import java.util.*;

// 4:10 ~ 5:25
// 특정한 최단경로

public class _1504_특정한_최단경로 {
	static int V, E;
	static int[][] adjMatrix;
	static final int INF = 123456789; //경로가 더해지면 임계값 넘을까봐

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adjMatrix = new int[V + 1][V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjMatrix[from][to] = w;
			adjMatrix[to][from] = w;
		}
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken()); // 경유지 1 (출발지 가능)
		int v2 = Integer.parseInt(st.nextToken()); // 경우지 2 (도착지 가능)

		// 두가지 경로의 다익스트라 비교
		// 출발 -> 경유지 1 -> 경유지 2 -> 도착
		// 출발 -> 경유지 2 -> 경유지 1 -> 도착
		int path1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, V);
		int path2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, V);
		if (path1 > path2 && path2 < INF)
			System.out.println(path2);
		else if (path1 < path2 && path1 < INF)
			System.out.println(path1);
		else
			System.out.println(-1);
	}

	private static int dijkstra(int start, int des) {
		int[] savedShortestPath = new int[V + 1];
		boolean[] visited = new boolean[V + 1];

		Arrays.fill(savedShortestPath, INF);
		savedShortestPath[start] = 0;

		while (!visited[des]) { // 경유지 1 방문할때까지
			int selected = extractMin(savedShortestPath, visited);
			if (selected == -1) //더이상 갈 곳이 없다면 
				break;
			visited[selected] = true;

			for (int i = 1; i <= V; i++) {
				if (visited[i] || adjMatrix[selected][i] == 0)
					continue;
				else if (savedShortestPath[i] > savedShortestPath[selected] + adjMatrix[selected][i]) {
					savedShortestPath[i] = savedShortestPath[selected] + adjMatrix[selected][i];
				}
			}
		}
		return savedShortestPath[des];
	}

	private static int extractMin(int[] savedShortestPath, boolean[] visited) {
		int min = INF;
		int selectVertex = -1;
		for (int i = 1; i <= V; i++) {
			if (min > savedShortestPath[i] && !visited[i]) {
				min = savedShortestPath[i];
				selectVertex = i;
			}
		}
		return selectVertex;
	}
}
