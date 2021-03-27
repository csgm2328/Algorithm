import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.print.attribute.standard.Sides;

// 최소 신장트리를 만드는 최소 가중치는 프림이나 크루스칼
// 음의 값을 허용하므로 벨만포드

public class _1197_최소_스패닝_트리 {
	static BufferedReader br;
	static StringTokenizer st;
	static int V, E; // 정점, 간선
	static int[][] adjMatrix;
	static boolean[][] visited;
	static int[] parents;

	static int[] dr = { 0, -1, 1, 0 };
	static int[] dc = { 1, 0, 0, -1 };

	static class xy {
		int r, c;

		public xy(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class WeightComparator implements Comparator<int[]> {
		@Override
		public int compare(int[] o1, int[] o2) {
			return Integer.compare(o1[2], o2[2]);
		}
	}

	public static void main(String[] args) throws IOException {
		// global init
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 1 ~ 1만
		E = Integer.parseInt(st.nextToken()); // 1 ~ 10만
		adjMatrix = new int[E][]; // 간선수 만큼 입력받음
		parents = new int[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken()); // |10만|, 음수가능
			adjMatrix[i] = new int[] { from, to, w };
		}

		// 크루스칼
		// 1. 각 정점들 만들기
		make();
		// 2. 간선을 오름차순으로 정렬한 후
		Arrays.sort(adjMatrix, new WeightComparator());
		List<int[]> S = new ArrayList<>(); // 빈 신장트리
		int sum = 0; // 가중치 합
		// 3. union을 통해 신장트리를 키워간다
		while (S.size() < V - 1) { // 신장트리의 간선수가 정점의 개수보다 하나작을때까지
			for (int i = 0; i < E; i++) {
				if (union(adjMatrix[i][0], adjMatrix[i][1])) {
					S.add(adjMatrix[i]); // 정점이 아닌 간선을 저장
					sum += adjMatrix[i][2];
				}
			}
		}
//		S.forEach((temp) ->{
//			System.out.println(Arrays.toString(temp));
//		});
		System.out.print(sum);
	}

	private static boolean union(int i, int j) {
		int a = find(i);
		int b = find(j);
		if (a == b)
			return false;
		parents[b] = a;
		return true;
	}

	private static int find(int i) {
		// path comprehension
		if (parents[i] == i)
			return i;
		else
			return parents[i] = find(parents[i]);
	}

	private static void make() {
		// 각 정점의 부모를 자신으로
		for (int i = 1; i <= V; i++)
			parents[i] = i;
	}

}
