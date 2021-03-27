import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// 최소 신장트리를 만드는 최소 가중치는 프림이나 크루스칼
// 음의 값이 있어도 유니온으로 사이클이 생기지 않게 하므로 상관없다
// 크루스칼의 시간 복잡도
// 간선 정렬  + make() + find()
//=O(ElogE) + O(V) + O(E)
// E는 최대 V^2 --> logE = logV^2 = 2logV = logV
// 따라서, O(ElogV)로 정렬시간이 좌우한다

public class _1197_최소_스패닝_트리 {
	static BufferedReader br;
	static StringTokenizer st;
	static int V, E; // 정점, 간선
	static int[][] adjMatrix;
	static int[] parents;

	static class WeightComparator implements Comparator<int[]> {
		@Override
		public int compare(int[] o1, int[] o2) {
			return Integer.compare(o1[2], o2[2]);
		}
	}

	public static void main(String[] args) throws IOException {
		// global init
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine()); // 1922_네트워크 연결은 V,E 다른줄에
		V = Integer.parseInt(st.nextToken()); // 1 ~ 10만
		E = Integer.parseInt(st.nextToken()); // 1 ~ 100만
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
		for (int i = 0; i < E; i++) { // 간선 수 만큼 보면서 신장트리에 포함시키기
			if (union(adjMatrix[i][0], adjMatrix[i][1])) {
				S.add(adjMatrix[i]); // 정점이 아닌 간선을 저장
				sum += adjMatrix[i][2];
				if (S.size() == V - 1) // 신장트리가 정점-1개의 간선을 포함하면 끝
					// 맨 뒤에 제일 큰 가중치는 다른 마을로 보내서 세지 않음: 1647_마을분할계획
					break;
			}
		}
//		S.forEach((temp) -> {
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
