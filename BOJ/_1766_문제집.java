import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//위상정렬 + PQ

public class _1766_문제집 {
	static int N,M;
	static List<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] enterEdge = new int[N+1];
		for(int i =0; i<= N; i++)
			adjList.add(new ArrayList<>());
		for(int i =0 ; i< M; i++) {
			st = new StringTokenizer(br.readLine());
			int before = Integer.parseInt(st.nextToken());
			int after = Integer.parseInt(st.nextToken());
			adjList.get(before).add(after);
			enterEdge[after]++;
		}
		TopologicalSort(enterEdge);
	}
	private static void TopologicalSort(int[] enterEdge) {
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i =1; i<=N;i++) 
			if(enterEdge[i] == 0) 
				pq.add(i);
		while(!pq.isEmpty()) {
			int x = pq.poll();
			sb.append(x).append(" ");
			for(int next : adjList.get(x)) {
				enterEdge[next]--;
				if(enterEdge[next] == 0)
					pq.add(next);
			}
		}
		System.out.println(sb.toString());
	}
}