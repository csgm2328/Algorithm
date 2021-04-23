import java.io.*;
import java.util.*;

// 입력을 중복해서 처리하는 위상정렬
public class _2623_음악프로그램 {
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
			int cnt = Integer.parseInt(st.nextToken());
			int[] x = new int[cnt];
			for (int j = 0; j < cnt; j++)
				x[j] = Integer.parseInt(st.nextToken()) - 1;
			int from = x[0];
			for (int j = 1; j < cnt; j++) {
				int to = x[j];
				adjList.get(from).add(to);
				cntOfEnterEdge[to]++; // 진입간선수 추가
				from = to;
			}
		}
		Queue<Integer> q = new LinkedList<Integer>();
		// 시작정점 찾기
		for (int i = 0; i < N; i++)
			if (cntOfEnterEdge[i] == 0)
				q.offer(i);

		while (!q.isEmpty()) {
			int cur = q.poll();
			sb.append((cur + 1)).append("\n");
			for (int next : adjList.get(cur)) {
				cntOfEnterEdge[next]--;

				if (cntOfEnterEdge[next] == 0)
					q.add(next);
			}
		}
		boolean flag = true;
		for (int i = 0; i < N; i++) {
			if (cntOfEnterEdge[i] != 0) {
				flag = false;
				break;
			}
		}
		if (flag)
			System.out.println(sb.toString());
		System.out.println(0);
	}
}
