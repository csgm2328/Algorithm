package SW_TEST;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//입력값을 인접배열 그래프형태로 저장하고
//프림인데 double 자료형과
//소수점 반올림해주는 Round()는 출력할때 해줘야 값 누락이 안됨

public class _1251_하나로 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(input.readLine()); // 1 ~ 1000
			// 1행: x, 2행 : y
			int[] posX = new int[N];
			int[] posY = new int[N];
			StringTokenizer st = new StringTokenizer(input.readLine());
			for (int i = 0; i < N; i++)
				posX[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(input.readLine());
			for (int i = 0; i < N; i++)
				posY[i] = Integer.parseInt(st.nextToken());
			double E = Double.parseDouble(input.readLine());
			double[][] adjArr = new double[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					// 각 정점으로의 가중치 저장
					double dis = Math.sqrt(Math.pow((posX[i] - posX[j]), 2) + Math.pow((posY[i] - posY[j]), 2));
//					adjArr[i][j] = Math.round(E*dis*dis);
					adjArr[i][j] = dis;
				}
			}
			double[] savedShortestPath = new double[N]; // dp
			boolean[] visited = new boolean[N]; // 이걸로 S-V 체크

			Arrays.fill(savedShortestPath, Double.MAX_VALUE);
			savedShortestPath[0] = 0;
			List<Integer> S = new ArrayList<Integer>(); // 신장트리
			double sum = 0;
			// 다익스트라 아니고 프림
			while (S.size() < N) {
				// 2. 미포함 정점 중 출발지에서 가장 최소비용의 정점 선택
				int selectedVertex = extractMin(visited, savedShortestPath);
				// 3. 선택된 정점의 연결된 정점들에 가중치 갱신하고
				for (int i = 0; i < N; i++) {
					if (visited[i] || adjArr[selectedVertex][i] == 0) // 연결되있고 아직 미포함인지
						continue;
					// 프림이라 그냥 신장트리에 추가하는 젤 짧은거로 갱신
					if (savedShortestPath[i] > adjArr[selectedVertex][i])
						savedShortestPath[i] = adjArr[selectedVertex][i];
				}
				// 4. 선택된거 신장트리에 포함시킴
				visited[selectedVertex] = true;
				sum += savedShortestPath[selectedVertex] * savedShortestPath[selectedVertex];
//				sum += savedShortestPath[selectedVertex];
				S.add(selectedVertex);
			}
			sb.append("#" + tc + " " + Math.round(E * sum) + "\n");
		} // end TC
		System.out.println(sb.toString());
	}

	// 이 함수 필요없고 가중치가 갱신될때 큐에 추가해야함
	private static int extractMin(boolean[] visited, double[] savedShortestPath) {
		double min = Double.MAX_VALUE;
		int minVertex = 0;
		for (int i = 0; i < visited.length; i++) {
			// 아직 S에 포함안되었으면서(미방문) 가중치가 가장 적은거 선택
			if (!visited[i] && savedShortestPath[i] < min) {
				min = savedShortestPath[i];
				minVertex = i;
			}
		}
		return minVertex;
	}
}
