package SW_TEST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1251_하나로_pq {
	static int N, visitIsland;
	static long[][] pos;
	static boolean[] visited;
	static PriorityQueue<Dis> que;

	static double ans, E;

	static class Dis implements Comparable<Dis> {
		double len;
		int idx;

		public Dis(double len, int idx) {
			super();
			this.len = len;
			this.idx = idx;
		}

		@Override
		public int compareTo(Dis o) {
			if (this.len < o.len)
				return -1;
			else if (this.len > o.len)
				return 1;
			return 0;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			// pos 배열 2, N
			pos = new long[N][2];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					pos[j][i] = Integer.parseInt(st.nextToken());
				}
			}

			E = Double.parseDouble(br.readLine());
			ans = 0;
			visitIsland = 1;
			visited = new boolean[N];
			que = new PriorityQueue<>();

			// 인덱스 0번 좌표 넣어주기
			que.offer(new Dis(0, 0));

			while (visitIsland <= N) {
				Dis dis = findShortestIsland();
				visited[dis.idx] = true;

				calcAnotherIslandLength(dis);

				// 해저터널 길이 계산
				ans += (dis.len * dis.len);
				visitIsland++;
			}

			sb.append("#" + tc + " " + Math.round(ans * E) + '\n');
		}
		System.out.print(sb.toString());
	}

	private static void calcAnotherIslandLength(Dis dis) {
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				que.offer(new Dis(calcLength(dis.idx, i), i));
			}
		}
	}

	private static double calcLength(int firIdx, int secIdx) {
		double len;

		len = Math.sqrt((pos[firIdx][0] - pos[secIdx][0]) * (pos[firIdx][0] - pos[secIdx][0])
				+ (pos[firIdx][1] - pos[secIdx][1]) * (pos[firIdx][1] - pos[secIdx][1]));

		return len;
	}

	private static Dis findShortestIsland() {
		Dis dis;
		do {
			dis = que.poll();
		} while (visited[dis.idx]);
		return dis;
	}
}