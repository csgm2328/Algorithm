import java.io.*;
import java.util.*;

// 트리의 지름
// 루트 - 리프, 리프- 리프 중 dfs 탐색을 통해 최대가중치 연결을 고른다
// 루트는 1로 고정
// 최적화
// 1. 처음에 루트에서 리프를 dfs를 돌면 가장 루트에서부터 가장 긴 거리를 가진 리프를 알수있게 된다
// 그러니 이 리프 노드에서 dfs를 한번 더하게 되면 답임 -1800ms
// 2. 연결간선이 하나라서 visited 처리 대신 pre 파라미터를 통해 이전으로만 안가게 하면 됨 -10ms

public class _1967_트리의지름 {
	static int N;
	static List<ArrayList<Node>> nodes = new ArrayList<ArrayList<Node>>();
//	static int[] parent;
	static int max = -1;
	static int leaf;
	
	static class Node {
		int val;
		int weight;

		public Node(int val, int weight) {
			this.val = val;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
//		parent = new int[N + 1];
		for (int i = 0; i <= N; i++)
			nodes.add(new ArrayList<Node>());
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			nodes.get(from).add(new Node(to, weight));
			nodes.get(to).add(new Node(from, weight));
//			parent[to] = from;
		}
//		List<Integer> leaves = new ArrayList<Integer>();
		// 리프노드 목록 만들기(해당 노드를 부모노드로 하는 노드가 있는지 여부)
//		int[] idx = new int[N + 1];
//		for (int i = 1; i <= N; i++)
//			idx[parent[i]]++;
//		for (int i = 1; i <= N; i++) { 
//			if (idx[i] == 0)
//				leaves.add(i);
//		}
//		visited = new boolean[N + 1];
		
//		for (int x : leaves) {
//			Arrays.fill(visited, false);
//			dfs(x, 0);
//		}
		dfs(1, 0, 0);
//		Arrays.fill(visited, false);
		dfs(leaf,0,0);
		System.out.println(max);
	}
	//최종 최적화: leaf 찾고 visit대신 이전만 체크
	private static void dfs(int cur, int pre, int weight) {
		max = Math.max(max, weight);
		if(max == weight)
			leaf = cur;
		for(Node next: nodes.get(cur)) {
			if(next.val != pre)
				dfs(next.val, cur, weight+next.weight);
		}
	}

//	static boolean[] visited;
	
//	private static void dfs(int cur, int weight) {
//		visited[cur] = true;
//		for (Node n : nodes.get(cur)) {// 자식 있음
//			if (!visited[n.val])
//				dfs(n.val, weight + n.weight);
//		}
//		if (parent[cur] != 0 && !visited[parent[cur]]) // 부모있음
//			for (Node n : nodes.get(cur)) {
//				if (n.val == parent[cur]) {
//					dfs(parent[cur], n.weight + weight);
//					break;
//				}
//			}
////			dfs(parent[cur], nodes[cur][parent[cur]] + weight);
//		else {// 둘다 없다면
//			max = Math.max(max, weight);
//			if(max == weight)
//				leaf = cur; //최적화 : 한번의 dfs로 최적리프노드를 알아내 한번만 수행가능 200ms
//		}
//	}
}
