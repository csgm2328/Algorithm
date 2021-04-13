import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class _17471_개리멘더링 {
	static int N, population[];
	static LinkedList<int[]> adjList = new LinkedList<int[]>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		population = new int[N];
		for (int i = 0; i < N; i++)
			population[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int[] x = new int[cnt];
			for (int j = 0; j < cnt; j++)
				x[j] = Integer.parseInt(st.nextToken());
			adjList.add(x);
		}
		// 부분집합 --> 조합으로 반복줄이기
		for (int r = 1; r <= N / 2; r++) {
			visited = new boolean[N];
			combination(0, r, 0);
		}

		System.out.println(MIN == Integer.MAX_VALUE ? -1 : MIN);
	}

	static boolean[] visited;
	static int MIN = Integer.MAX_VALUE;

	private static void combination(int idx, int r, int start) {
		if (idx == r) {
			int sum1 = 0, sum2 = 0;
			List<Integer> area1 = new ArrayList<Integer>();
			List<Integer> area2 = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					area1.add(i + 1); // 1구역
					sum1 += population[i];
				} else {
					area2.add(i + 1); // 2구역
					sum2 += population[i];
				}
			}
			// 나눈 구역들이 연결가능한지 확인
			if (connectable(area1) && connectable(area2)) {
				if (MIN > Math.abs(sum1 - sum2)) {
					MIN = Math.abs(sum1 - sum2);
//					System.out.print("[1지역]: ");
//					for (int x : area1) {
//						System.out.print(x + " ");
//					}
//					System.out.print("[2지역]: ");
//					for (int x : area2) {
//						System.out.print(x + " ");
//					}
//					System.out.println();
				}
			}
			return;
		}
		for (int i = start; i < N; i++) {
			visited[i] = true;
			combination(idx + 1, r, i + 1);
			visited[i] = false;
		}
	}

	private static boolean connectable(List<Integer> area) {
		// 구역 안에있는것들 연결가능한지 확인
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();
		q.offer(area.get(0)); // 구역 첫번째 도시
		visited[area.get(0)] = true; //실수포인트 !!!!!!!!!!!!!!!!!!!!!!

		int connectArea = 1; //실수포인트!!!!!!!! visit처리했으면 시작이 하나지
		while (!q.isEmpty()) {
			int cur = q.poll();
			int[] x = adjList.get(cur - 1); // 그 도시의 연결정보
			for (int i = 0; i < x.length; i++) {
				if (!visited[x[i]] && area.contains(x[i])) { // 그 도시와 연결되어있는 도시가 부분집합으로도 선택되었다면
					q.offer(x[i]);
					visited[x[i]] = true;
					connectArea++;
				}
			}
		}
		if (connectArea != area.size()) // 나눈 구역이 다 연결되었는지 확인
			return false;
		else
			return true;
	}

	private static boolean isContained(List<Integer> area, int vertex) {
		boolean flag = false;
		for (int i = 0; i < area.size(); i++) {
			if (area.get(i) == vertex) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
