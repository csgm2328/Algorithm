import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1753_최단경로 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int V,E;
	static class Vertex{
		int No, weight;

		public Vertex(int no, int weight) {
			super();
			this.No = no;
			this.weight = weight;
		}
		
	}
	static List<List<Vertex>> vList = new ArrayList<List<Vertex>>();
//	static List<Vertex>[] list; //이렇게도 가능
	
	public static void main(String[] args) throws IOException {
		// 정점에 대한 간선 입력받음 리스트
		StringTokenizer st = new StringTokenizer(input.readLine());
		// global init
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		for(int i =0 ; i<= V;i++)
			vList.add(new ArrayList<Vertex>());
		int start = Integer.parseInt(input.readLine());
		for(int i = 0; i< E; i++) {
			st = new StringTokenizer(input.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			vList.get(u).add(new Vertex(v, w));
		}
		
		//비용이 작은 정점을 고르는  O(V)를 최소힙을 사용해서 log(V)로
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(new Comparator<Vertex>() {
			public int compare(Vertex o1, Vertex o2) {
				return o1.weight - o2.weight;
			};
		});
		int[] savedShortestPath = new int[V+1]; //dp
		boolean[] visited = new boolean[V+1]; // 이걸로 S-V 체크
		
		// 시작정점 제외 무한대로 초기화
		Arrays.fill(savedShortestPath, Integer.MAX_VALUE);
		savedShortestPath[start] = 0;
		pq.offer(new Vertex(start, 0)); 
		
		List<Integer> S = new ArrayList<Integer>(); //신장트리
		// 1. 모든 정점이 신장트리에 포함 될 때 까지
		while (S.size() < V && !pq.isEmpty()) {  //pq는 갱신되는 게 추가되므로 최대 O(E) 그러므로 최종 O(ElogV)
			// 2. 미포함 정점 중 출발지에서 가장 최소비용의 정점 선택
			Vertex u = pq.poll();
			//꺼냈는데 이미 포함된 정점이라면 패스
			if(visited[u.No])
				continue;
			// 3. 선택된 정점의 연결된 정점들에 가중치 갱신하고
			// 정점은 순서대로 있으므로
			int uSize = vList.get(u.No).size();
			for (int i = 0; i < uSize; i++) {
				//돌아가는 길이 더 최소비용이라면 갱신
				int to = vList.get(u.No).get(i).No;
				int weight = vList.get(u.No).get(i).weight;
				if (!visited[to] && savedShortestPath[to] > weight + savedShortestPath[u.No]) {
					savedShortestPath[to] = weight + savedShortestPath[u.No];
					pq.offer(new Vertex( to, savedShortestPath[to])); //갱신된 정점이랑 가중치
					//원래 저장되어있던 해당 정점의 가중치를 지우고 싶은데 그럴 순 없다
				}
			}
			// 4. 선택된거 신장트리에  포함시킴
			visited[u.No] = true;
			S.add(u.weight); //신장트리에 최단경로 추가
		} 
		
		System.out.println("최단경로: ");
		for(int i = 1; i<= V; i++) {
			if(savedShortestPath[i] == Integer.MAX_VALUE) {
				sb.append("INF\n");
				continue;
			}
			sb.append(savedShortestPath[i]+"\n");
		}
		System.out.print(sb.toString());
	}
	//가장 작은거 pq<vertex, weight>로 유지하고 뺴면 logV 탐색비용
	//이 함수 필요없고 가중치가 갱신될때 큐에 추가해야함
}
