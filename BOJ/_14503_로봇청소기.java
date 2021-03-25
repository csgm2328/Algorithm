import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

//입력을 잘받자 덕분에 디버깅 +2시간 
//로봇 청소기
//dfs방식에 청소기 정보를 가지고 다니는게 아니라 전역으로 처리한다
//구현문제는 문제조건을 정확히 처리하는게 항상 오래걸린다
//여기서는 후진을 할때마다 4방탐색을 또하는 거였음
public class _14503_로봇청소기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	// 북, 동, 남, 서 매핑이지만 왼쪽부터니까 반대로 -하면서 돌아야함
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int N, M;
	static int[][] arr;
	static boolean[][] visited;

	static class vaccum {
		int r, c, dir, sum;

		public vaccum(int r, int c, int dir, int sum) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.sum = sum;
		}
	}
	static vaccum vaccum;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(input.readLine());
		int sr = Integer.parseInt(st.nextToken()); // 시작지점
		int sc = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken()); //입력을 안받았다 잘한다~

		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		vaccum = new vaccum(sr, sc, dir, 1);
		visited[sr][sc] = true;
//		arr[vaccum.r][vaccum.c] = vaccum.sum;
		dfs();
	}

	private static void dfs() {
		// 후진을 연달아 하면 안되고 일단 방문이 되어있어도 4방탐색해야함
		// 후진 중에는 방문한 곳을 또 방문한다
		// 그러므로 리턴되지않는 dfs임
//		for(int dir = vaccum.dir + 1; dir <= (vaccum.dir + 4)%4; dir++) {
		for (int i = 0; i < 4; i++) { // 4방탐색 
			--vaccum.dir; // 방향먼저 돌리고 왼쪽부터 탐색
			if (vaccum.dir < 0)
				vaccum.dir += 4;
			int nr = vaccum.r + dr[vaccum.dir]; // 넘길 때는 nr쓰면 새 객체를 넘기는게 아니라 안됨
			int nc = vaccum.c + dc[vaccum.dir];
			if (isInBoundary(nr, nc) && !visited[nr][nc] && arr[nr][nc] == 0) {// 청소가능이면
				vaccum.r = nr; //위치 갱신
				vaccum.c = nc;
				visited[vaccum.r][vaccum.c] = true;
				vaccum.sum++;
//				arr[vaccum.r][vaccum.c] = vaccum.sum;
				dfs();
			}
		}
		// !!!!!!!!!!!!실수포인트 -> 리턴을 하면 포문이 돌던중이라 디렉션이 처음방향으로 안되어진다
		// 리턴하면 안됨
		int back = vaccum.dir + 2; //후진 방향, 청소기 방향은 고정
		if (back >= 4)
			back -= 4;
		int nr = vaccum.r + dr[back];
		int nc = vaccum.c + dc[back];
		if (arr[nr][nc] == 1) { //후진하려고 하는 곳이 벽인경우 종료
			System.out.print(vaccum.sum);
//			for(int[] x: arr)
//				System.out.println(Arrays.toString(x));
			System.exit(0); // 종료
		}
		vaccum.r = nr; // 후진위치로 갱신
		vaccum.c = nc;
		dfs(); //리턴을 못하게 해야한다 그냥 종료하도록
	}

	private static boolean isInBoundary(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
