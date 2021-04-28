import java.io.*;
import java.util.*;

import javax.swing.JPopupMenu.Separator;

// ACM CRAFT
// 위상정렬한 후 진입간선이 0인 것들이 동시에 건설된다
// 동시에 건설되는 것중(진입간선이 이번에 0이되는 것들 중에  Max만 더해준다
public class _1005_ACM_Craft {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] cntOfEnterEdge = new int[N + 1];
			int[] buildingTime = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++)
				buildingTime[i] = Integer.parseInt(st.nextToken());

			List<LinkedList<Integer>> adjList = new LinkedList<LinkedList<Integer>>();
			for (int i = 0; i <= N; i++)
				adjList.add(new LinkedList<>());
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList.get(from).add(to);
				cntOfEnterEdge[to]++;
			}
			int end = Integer.parseInt(br.readLine()); // 마지막에 지을 건물 번호

			// 위상정렬
			int[] spendTime = new int[N+1];
//			Arrays.fill(spendTime, Integer.MAX_VALUE);
			Queue<Integer> q = new LinkedList<Integer>();
			for (int i = 1; i <= N; i++) {
				if (cntOfEnterEdge[i] == 0) {
					q.offer(i); //시작 정점
					spendTime[i] = buildingTime[i];
				}
			}
			while(!q.isEmpty()) {
				int cur = q.poll();
				if(cur == end) {
					sb.append(spendTime[cur] + "\n");
					break;
				}
				for(int u : adjList.get(cur)) {
					cntOfEnterEdge[u]--;
					
					if(spendTime[u] < spendTime[cur] + buildingTime[u]) {
						spendTime[u] = spendTime[cur] + buildingTime[u];
					}
					if(cntOfEnterEdge[u] == 0 )
						q.offer(u);
				}
			} //end while
		}
		System.out.println(sb.toString());
	}
}
