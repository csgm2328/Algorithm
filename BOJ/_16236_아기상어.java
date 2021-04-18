import java.io.*;
import java.util.*;

// 아기상어
// 자신보다 작은 물고기만 먹을 수 있고
// 자신의 크기만큼 물고기 수를 채우면 커진다
// 먹이는 pq로 가깝고 위에고 왼쪽인거를 먼저 찾는다

public class _16236_아기상어 {
	static StringBuilder sb = new StringBuilder();
	static int N; // 2 ~ 20
	static int[] dr = { -1, 0, 1, 0 }; // 상,좌를 먼저 본다면 빨라질까?
	static int[] dc = { 0, -1, 0, 1 };

	static int size, eat;
	static int ans;
	static int[][] arr;
	static boolean[][] visited;

	static class target {
		int r, c, time;

		public target(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	static class Shark {
		int r, c;
		target target;

		public Shark(int r, int c, target feed) {
			super();
			this.r = r;
			this.c = c;
			this.target = feed;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		arr = new int[N][N];
		visited = new boolean[N][N];
		Shark babyShark = null;
		size = 2;
		eat = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					babyShark = new Shark(i, j, null);
					arr[i][j] = 0;
					visited[i][j] = true;
				}
			}
		}
		bfs(babyShark);
//		for (int[] x : timecheck)
//			System.out.println(Arrays.toString(x));
		System.out.println(ans);
	}

//	static int[][] timecheck;

	private static void bfs(Shark cur) {
//		timecheck = new int[N][N];
		while (true) {
			cur.target = getFeed(cur);
			if (cur.target == null)
				return;
			cur.r = cur.target.r; //먹이 위치로
			cur.c = cur.target.c;
			eat++;
			if (eat== size) { // 성장여부
				size++;
				eat = 0;
			}
			ans += cur.target.time; // 걸린 시간 누적
			arr[cur.r][cur.c] = 0; // 먹음 처리
//			timecheck[cur.r][cur.c] = ans; // 디;버깅용
		}
	}

	private static boolean isInBoundary(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

	// 먹이를 먹고나면 그자리에서 또 새로운 먹이큐만듬 bfs방식으로
	private static target getFeed(Shark babyShark) {
		PriorityQueue<target> feeds = new PriorityQueue<target>(new Comparator<target>() {

			@Override
			public int compare(target o1, target o2) { // 가까운순, 위쪽 -> 왼쪽
				// 실수포인트 !!!!! 상어위치에서 상대적인게 아니라 절대적인 위치도 아니고
				if (o1.time == o2.time) {
					if (o1.r == o2.r)
						return Integer.compare(o1.c, o2.c);
					return Integer.compare(o1.r, o2.r);
				}
				return Integer.compare(o1.time, o2.time);
			}
		});
		Queue<target> q = new LinkedList<target>();
		boolean[][] visit = new boolean[N][N];
		q.offer(new target(babyShark.r, babyShark.c, 0));
		visit[babyShark.r][babyShark.c] = true;
		int dist = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			target cur = q.poll();
			if(dist < cur.time) //1로 갈수 있는 먹이를 다찾았다면 2로 갈 수 있는 먹이는 무의미
				break;
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				if (isInBoundary(nr, nc) && !visit[nr][nc] && arr[nr][nc] <= size) {
					if (arr[nr][nc] != 0 && arr[nr][nc] < size) {// bfs 돌면서 먹이큐에 들어갈것
						feeds.offer(new target(nr, nc, cur.time + 1));
						dist = cur.time+1;
					}
					// 냅다 pq를 만들면 먹을 수없는 곳에 먹이가 있을 수 있게 된다
					// 먹을 수 있는 곳에 있는 거를 다 조사해서 pq에 넣은다음 그 중에 먹을 거를 고르자
					q.offer(new target(nr, nc, cur.time + 1));
					visit[nr][nc] = true;
				}
			}
		}
		return feeds.poll();
	}
}
