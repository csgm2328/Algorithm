import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//	N,M <=8 완탐가능 (최대 64칸에서 3칸을 놓는 경우)
// 칸을 놓을 수 있는 경우를 조합의 수로 구하고 그 상태로 
// 바이러스 퍼뜨린 후 남은 0의 개수를 센다
// 최적화
// 카피 배열(2중) + 리스트로 시작(1중 for) + 사이즈 바로 계산 = 412ms
// arr에 직접 표시하고 지우면서 사이즈 계산(2중) + 다 돌면서 시작(2중 for) = 404ms
public class _14502_연구소 {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M;
	static int[][] arr;
	static int wallcnt;
//	static List<xy> virusList = new ArrayList<xy>();
	private static int MAX = -1;

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
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 3 ~ 8
		M = Integer.parseInt(st.nextToken()); // 3 ~ 8
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
//				if (arr[i][j] == 1)
//					wallcnt++;
//				else if (arr[i][j] == 2)
//					virusList.add(new xy(i, j));
			}
		}
		combination(0, 0);
		System.out.print(MAX);
	}

	private static void combination(int idx, int start) {
		if (idx == 3) { // 벽은 무조건 세개 놓기
//			int[][] copy = new int[N][M]; // 바이러스 퍼뜨려볼 카피 배열
//			for (int i = 0; i < N; i++)
//				for (int j = 0; j < M; j++)
//					copy[i][j] = arr[i][j];

			Queue<xy> q = new LinkedList<xy>();
//			for (int i = 0; i < virusList.size(); i++) // 바이러스 위치들을 bfs 시작점으로
//				q.offer(virusList.get(i));
//			int virus_size = spread_virus(q, copy); // 바이러스가 퍼진 영역의 크기

			// 0 면적 계산
//			int size = N * M - (wallcnt + 3) - virus_size;
			for(int i = 0; i< N; i++) {
				for(int j= 0; j< M; j++) {
					if(arr[i][j]== 2) {
						q.offer(new xy(i,j));
						spread_virus(q);
					}
				}
			}
			int size = 0;
			for(int i = 0; i< N; i++) {
				for(int j= 0; j< M; j++) {
					if(arr[i][j] == 0)
						size++;
					else if(arr[i][j]== 3)
						arr[i][j] = 0;
				}
			}
			if (MAX < size)
				MAX = size;
			return;
		}
		for (int i = start; i < N * M; i++) {
//			int x = Math.max(N, M); //행 열 중에 큰 걸로 나눠서
//			int row = i / x; //몫은 행
//			int col = i % x; //나머지는 열
//			!!실수포인트 -> 위에처럼 하면 행이 더 클 때 배열 나감
			int row = N > M ? i % N : i / M;
			int col = N > M ? i / N : i % M;;
			if (arr[row][col] == 0) {
				arr[row][col] = 1; // 벽 놓기
				combination(idx + 1, i + 1);
				arr[row][col] = 0; // 벽 해제
			}
		}
	}

	private static int spread_virus(Queue<xy> q) {
		int size = 0;
		while (!q.isEmpty()) {
			xy cur = q.poll();
			size++;
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				if (isInBoundary(nr, nc) && arr[nr][nc] == 0) {
					q.offer(new xy(nr, nc));
					arr[nr][nc] = 3; // 방문처리를 바이러스 표시로
				}
			}
		}
		return size;
	}

	private static boolean isInBoundary(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
