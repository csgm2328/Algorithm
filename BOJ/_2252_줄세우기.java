import java.io.*;
import java.util.*;

public class _2252_줄세우기 {
	static int N, M;
	static List<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생수
		M = Integer.parseInt(st.nextToken()); // 비교횟수
		int[] cntOfEnterEdge = new int[N];

		for (int i = 0; i < N; i++)
			adjList.add(new ArrayList<>());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			adjList.get(from).add(to);
			cntOfEnterEdge[to]++; // 진입간선수 추가
		}
		Queue<Integer> q = new LinkedList<Integer>();
		// 시작정점 찾기
		for (int i = 0; i < N; i++)
			if (cntOfEnterEdge[i] == 0)
				q.offer(i);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append((cur+1)).append(" ");
			for (int next : adjList.get(cur)) {
				cntOfEnterEdge[next]--;

				if (cntOfEnterEdge[next] == 0)
					q.add(next);
			}
		}
		System.out.println(sb.toString());
	}
}
