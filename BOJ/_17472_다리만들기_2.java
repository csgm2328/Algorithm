import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.print.attribute.standard.Sides;

// 다리 놓기
// BFS로 면적번호를 써 놓은 후 다시 BFS로 면적이 닿으면 그 섬까지의 가중치로
// 인접리스트를 만든다
// 그리고 다익스트라 아니고 프림
public class _17472_다리만들기_2 {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M;
	static int[][] arr;
	static boolean[][] visited; //섬 번호를 2부터 쓴다면 필요 없음
	static int[][] adjIsland;

	static int[] dr = { 0, -1, 1, 0 };
	static int[] dc = { 1, 0, 0, -1 };

	static class xy {
		int r, c;

		public xy(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		// global init
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 1 ~ 10
		M = Integer.parseInt(st.nextToken()); // 3 ~ 10
		arr = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		//섬 번호 표시
		int island_No = 1;
		Queue<xy> q = new LinkedList<xy>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					q.offer(new xy(i, j));
					visited[i][j] = true;
					arr[i][j] = island_No;
					bfs(q, island_No);
					island_No++;
				}
			}
		}
		// 섬번호 써놨으면 자신 아닌거 만나면 가중치 정보 저장
		adjIsland = new int[island_No][island_No]; // 섬 개수만큼
		for (int[] x : adjIsland)
			Arrays.fill(x, Integer.MAX_VALUE);
		for (int no = 1; no < island_No; no++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == no) { // 섬 시작점 셋팅
						q.offer(new xy(i, j));
//						visited[i][j] = true; //쭉뻗어볼꺼라 visit안씀
					}
				}
			}
			searchPath(q, no); // 경로 찾아서 가중치 배열에 저장
		}
//		 가중치 배열 준비되었으면 프림
		int[] savedShortestPath = new int[island_No];
		boolean[] adjVisited = new boolean[island_No];
		List<Integer> S = new ArrayList<>();
		// 시작 정점 셋팅
		Arrays.fill(savedShortestPath, Integer.MAX_VALUE);
		savedShortestPath[1] = 0;
		// 1. 모든 정점이 신장트리에 포함 될 때 까지
		int sum = 0;
		while (S.size() < island_No - 1) {
			// 2. 미포함 정점 중 신장트리에 포함되는 가장 최소비용의 정점 선택
			int selectedVertex = extractMin(adjVisited, savedShortestPath);
			// 연결끊기면 종료
			if (savedShortestPath[selectedVertex] == Integer.MAX_VALUE) {
				sum = -1;
				break;
			}
			// 3. 선택된 정점의 연결된 정점들에 가중치 갱신하고
			for (int i = 1; i < island_No; i++) {
				if (adjVisited[i] || adjIsland[selectedVertex][i] == Integer.MAX_VALUE) // 연결되있고 아직 미포함인지
					continue;
				// 더 짧게 포함할 수 있는 경로 있으면 갱신
				else if (savedShortestPath[i] > adjIsland[selectedVertex][i])
					savedShortestPath[i] = adjIsland[selectedVertex][i];
			}
			// 4. 선택된거 신장트리에 포함시킴
			adjVisited[selectedVertex] = true;
			S.add(selectedVertex);
			sum += savedShortestPath[selectedVertex];
		}
		System.out.print(sum);
	}

	private static int extractMin(boolean[] visited, int[] savedShortestPath) {
		int min = Integer.MAX_VALUE;
		int minVertex = 0;
		for (int i = 1; i < visited.length; i++) {
			// 아직 S에 포함안되었으면서(미방문) 가중치가 가장 적은거 선택
			if (!visited[i] && savedShortestPath[i] < min) {
				min = savedShortestPath[i];
				minVertex = i;
			}
		}
		return minVertex;
	}

	private static void searchPath(Queue<xy> q, int no) {
		while (!q.isEmpty()) {
			xy cur = q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				if (isInBoundary(nr, nc) && arr[nr][nc] == 0) { // 다음이 바다이면
					stretch(nr, nc, dir, no); // 그방향으로 직진
				}
			}
		} // end while
		q.clear();
	}

	private static void stretch(int nr, int nc, int dir, int from) {
		int cnt = 1; // 현재 카운트 1
		nr += dr[dir];
		nc += dc[dir];
		// 끝나거나 다른 섬나올때까지 직진
		while (isInBoundary(nr, nc)) {
			if (arr[nr][nc] != 0 ) { // 섬을 만나면
				if (cnt == 1 || arr[nr][nc] == from) //반례 : 가다가 또 나의 섬을 만나면
					break; // 다리길이 1이면 더이상 진행 못하도록
				int to = arr[nr][nc];
				if (adjIsland[from][to] > cnt) { // 지금까지의 cnt를 from to의 가중치로 갱신
					adjIsland[from][to] = cnt;
				}
				break; //섬을 만나면 반드시 나가야함 --> 실수 포인트
			}
			nr += dr[dir];
			nc += dc[dir];
			cnt++;
		}
	}

	private static void bfs(Queue<xy> q, int No) {
		// bfs로 먼저 1의 면적들에다가 섬의 번호를 써놓는다
		while (!q.isEmpty()) {
			xy cur = q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				if (isInBoundary(nr, nc) && !visited[nr][nc] && arr[nr][nc] == 1) {
					q.offer(new xy(nr, nc));
					visited[nr][nc] = true;
					arr[nr][nc] = No;
				}
			}
		} // end while
		q.clear(); // 전역 사용
	}

	private static boolean isInBoundary(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
