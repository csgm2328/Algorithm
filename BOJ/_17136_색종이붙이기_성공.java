import java.io.*;
import java.util.*;

//8:37 ~ 10:40
//색종이를 붙이는 데 0 에다 붙이면 안되고 경계넘으면 안되고
// 각 크기당 5개씩밖에 못쓴다
// 가장 최소한의 색종이를 쓰는 경우는?
public class _17136_색종이붙이기_성공 {
	static int[][] arr;
	static boolean[][] visited;
	static int N;
	static int cnt, MIN = Integer.MAX_VALUE;
	static int[] papers = { 0, 5, 5, 5, 5, 5 }; // 1~ 25크기의 색종이 5개씩

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 10;
		arr = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		paper(0, arr);
		System.out.print(MIN == Integer.MAX_VALUE ? -1 : MIN);
	}

	private static void paper(int cnt, int[][] map) {
		if (cnt >= MIN) { // MIN을 넘어서면 더 볼필요없음
			return;
		}
		boolean flag = true;
		//전체를 탐색하면서 다 0이면  탈출한다
		//1을 만날경우 그 면적의 가로 세로의 작은 길이부터 작아지면서
		//그 면적에 색종이를 붙이는 경우의 수를 해본다
		//재귀구조의  backtrack
		//포인트 1. deepcopy로 경우의수 마다 붙여서 없앤 면적을 처리한다
		//포인트 2. 리턴할때 쓴 종이 복구
		//포인트 3. 붙일 수 있는지 체크랑 붙이는 거랑 따로 
		//포인트 4. 1을 만난 후 다음 1로는 재귀형태로만 넘어가야함
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					continue;
			
				flag = false;	// 1을 만나면 false
				int size = sizecheck(i, j); // 기준점에서 가로세로 보고 작은 사이즈
				for (int k = size; k > 0; k--) {
					int[][] copy = new int[10][10];
					for (int x = 0; x < 10; x++)
						for (int y = 0; y < 10; y++)
							copy[x][y] = map[x][y];
					if (papers[k] > 0 && attachable(copy, i, j, k)) {// 그 사이즈로 붙일 수있으면
						paper(cnt + 1, attach(copy, i, j, k)); // 카피배열로 붙이기
//						flag = true;
						
						papers[k]++; // 쓴 종이 복구
					}
				} // end for size
				// 포인트 4. 일단 1을 만나고 나면 1을 못지웠거나 리턴되서 1이 복구된 상황에서 다른 걸로 넘어가면 안됨
				// 그러니 무조건 리턴
				return;
			}
		} // end for
		if (flag) { // 탈출 : 맵이 다 0이라면
			if (MIN > cnt)
				MIN = cnt;
			return;
		}
	}

	private static int sizecheck(int r, int c) {
		int garo = 0;
		for (int i = r; i < r + 5; i++) {
			if (i < N && arr[i][c] == 1) {
				garo++;
			} else
				break;
		}
		int sero = 0;
		for (int j = c; j < c + 5; j++) {
			if (j < N && arr[r][j] == 1) {
				sero++;
			} else
				break;
		}
		return Math.min(garo, sero);
	}

	private static boolean attachable(int[][] copy, int r, int c, int size) {
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (copy[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	private static int[][] attach(int[][] copy, int r, int c, int size) {
		for (int i = r; i < r + size; i++)
			for (int j = c; j < c + size; j++)
				copy[i][j] = 0;
		papers[size]--;
		return copy;
	}
}
