import java.io.*;
import java.util.*;

// 소수인 a가 b가 되기 까지 한자리씩 바꿔서 걸리는 최소 회수
public class _17142_연구소_3 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int target = 0;

	static class Node {
		int r;
		int c;
		int time;

		public Node(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		List<int[]> virusList = new ArrayList<int[]>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					virusList.add(new int[] { i, j });
				else if (map[i][j] == 0)
					target++;
			}
		}
		if (target == 0) {
			System.out.println(0);
			System.exit(0);
		}
		// 조합
		check = new boolean[virusList.size()];
		comb(0, 0, virusList);
		if (MIN == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(MIN);
	}

	static boolean[] check;
	static Queue<Node> q;

	private static void comb(int idx, int start, List<int[]> virusList) {
		if (idx == M) {
			q = new LinkedList<Node>();
			visited = new boolean[N][N];
			for (int i = 0; i < check.length; i++) {
				if (check[i]) {
					int r = virusList.get(i)[0];
					int c = virusList.get(i)[1];
					q.offer(new Node(r, c, 0));
					visited[r][c] = true;
				}
			}
			bfs();
			return;
		}
		for (int i = start; i < virusList.size(); i++) {
			if (!check[i]) {
				check[i] = true;
				comb(idx + 1, i + 1, virusList);
				check[i] = false;
			}
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int MIN = Integer.MAX_VALUE;

	private static void bfs() {
		int cnt = 0;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (MIN < cur.time) // 시간초과문제
				break;
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] == 1)
					continue;
				q.offer(new Node(nr, nc, cur.time + 1));
				visited[nr][nc] = true;
				if (map[nr][nc] == 0) {
					cnt++;
					if (target == cnt)
						MIN = Math.min(MIN, cur.time + 1);
				}
			}
		}
	}
}
