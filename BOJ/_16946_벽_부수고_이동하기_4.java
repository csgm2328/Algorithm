import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 처음에 한 생각은 최악의경우 백만의 공간을 백만번 탐색한다(벽 있는 걸 뺴면 반절정도겠지만)
// 1. bfs탐색으로 0의 면적을 세고 그 면적의 번호를 부여한다
// 2. 그 후에 전부다 탐색하면서 벽일때는 사방탐색으로 연결 된 면적의 크기를 더한다
// 3. 이 때 면적이 중복연결되지않도록 방문체크를 해준다.

// 그 외에도 시간초과 포인트
// for문 내에서 visited 1차원 배열이라도 재할당하면 안됨 --> HashSet으로 대체
// list대신 hashMap, hashSet 사용

// 최적화 포인트
// 1. grouping 배열만들지 않고 visit에 매핑 -20ms(어차피 초기화과정은 동일해서 미미함)
// 2. 입력하면서 0인 리스트만드는 것보다 배열로 받고 전체 탐색하는게 2000 - 900ms

public class _16946_벽_부수고_이동하기_4 {
	static BufferedReader input;
	static StringBuilder output;
	static StringTokenizer st;
	static int N, M;
	static int[] dr = { 0, -1, 1, 0 };
	static int[] dc = { 1, 0, 0, -1 };
	static char[][] save, arr;
	static int[][] visited;

	static class xy {
		int r, c;

		public xy(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class area {
		int No, size;

		public area(int no, int size) {
			super();
			this.No = no;
			this.size = size;
		}
	}

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("TC.txt"));
		// long stt = System.currentTimeMillis();
		input = new BufferedReader(new InputStreamReader(System.in));
		output = new StringBuilder();
		// global init
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken()); // 1 ~ 1000
		M = Integer.parseInt(st.nextToken()); // 1 ~ 1000
		arr = new char[N][M];
		visited = new int[N][M];
		HashMap<Integer, Integer> areaInfo = new HashMap<>(); //면적번호, 면적크기
//		grouping = new char[N][M];
//		List<xy> zeroList = new ArrayList<xy>();

		for (int i = 0; i < N; i++) {
			arr[i] = input.readLine().toCharArray(); //-900ms
//			String[] s = input.readLine().split("");
//			for (int j = 0; j < M; j++) {
//				arr[i][j] = s[j].charAt(0);
//				if (arr[i][j] == '0')
//					zeroList.add(new xy(i, j));
//			}
		}
		for (int[] x : visited)
			Arrays.fill(x, -1);

		int areaNo = 0;
//		for (int i = 0; i < zeroList.size(); i++) {
		for (int i = 0; i < N; i++) { // i: 1 ~ 1000
			for (int j = 0; j < M; j++) { // j: 1 ~ 1000
				if (arr[i][j] == '1' || visited[i][j] != -1)
					continue;
//				int r = zeroList.get(i).r;
//				int c = zeroList.get(i).c;
//				if (visited[i][j] == -1) { // 면적 표시 안되어있으면
				areaNo++; // 1부터시작
				int areaSize = bfs(i, j, areaNo);
				// 0으로 초기화된 새로운 맵에다가 면적 표시
				// 업데이트를 하지말고 그냥 돌면서 면적번호를 써놓자
				// 면적 번호와 면적사이즈 면적방문여부
//				sizes.add(new areas(areaNo, areaSize));
				areaInfo.put(areaNo, areaSize);
			}
		}

		// 벽에서 면적을 저장해논 4방탐색했을때 [nr][nc]에 면적번호 적혀 있고
		for (int i = 0; i < N; i++) { // i: 1 ~ 1000
			for (int j = 0; j < M; j++) { // j: 1 ~ 1000
//				boolean[] areaVisited = new boolean[areaNo + 1]; // 그면적이 방문되었는지 보려고 면적개수만큼 만든다
				// !!!문제 포인트 -> 1차원배열조차 재할당 오버헤드 때문에 시간초과가 난다
				HashSet<Integer> areaNoList = new HashSet<>();
				if (arr[i][j] == '0') {
					output.append(0);
					continue;
				}
				int sum = 1; // 자기자신부터 시작
				for (int dir = 0; dir < 4; dir++) { // 4방탐색으로 연결된 면적크기 한번에 얻어오기
					int nr = i + dr[dir];
					int nc = j + dc[dir];
					if (isInBoundary(nr, nc)) {
						int area_No = visited[nr][nc];
						if (area_No != -1 && !areaNoList.contains(area_No)) {
							sum += areaInfo.get(area_No);
							areaNoList.add(area_No);
						}

//						if (area_No != 0 && !areaVisited[area_No]) {
//							// 연결된 면적이 두번 더해질수도 있으므로 면적 방문체크하는걸 만든다
//							sum += areaInfo.get(area_No);
//							areaVisited[area_No] = true; // 그 면적 방문처리하고 다음 꺼할땐 초기화해야함
//						}
					}
				} // end 4방탐색
				output.append(sum % 10);
			}
			output.append("\n");
			// output.append("종료:"+(System.currentTimeMillis() - stt)/1000.0);
		}
		System.out.print(output.toString());
	}

	private static int bfs(int r, int c, int No) {
		// bfs로 먼저 0의 면적들에다가 면적의 크기를 써놓는다
		// 그래서 벽이 4방탐색한번에 경로를 알아낼 수 있도록
		Queue<xy> q = new LinkedList<xy>();
		q.offer(new xy(r, c));
		visited[r][c] = No;
//		grouping[r][c] = (char) (No + '0');
		int cnt = 1; // 경로 수 0일때 들어왔으니까 1부터시작

		while (!q.isEmpty()) {
			xy cur = q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				if (isInBoundary(nr, nc) && visited[nr][nc] == -1 && arr[nr][nc] == '0') {
					q.offer(new xy(nr, nc));
					visited[nr][nc] = No;
//					grouping[nr][nc] = (char) (No + '0');
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static boolean isInBoundary(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
