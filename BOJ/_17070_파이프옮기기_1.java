import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 모양마다 진행방향이 달라지면서 목표에 도착하기
// 가능한 방법의 개수는?

public class _17070_파이프옮기기_1 {
	static int N;
	static int[][] arr;
	static long cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 모양 가로 0 세로 1 대각선 2
		dfs(0, 1, 0);
		System.out.print(cnt);
	}
	//최적화버전 288 - 100ms
	//1. 밑으로 옆으로만 내려가니까 check가 필요없다
	//2. 가로세로 대각선을 간단하게 처리하려면 가로가 아닐때 세로가 아닐때로 if문 작성
	static void dfs(int cu_row, int cu_col, int shape) {
		//도착
		if(cu_row == N-1 && cu_col == N-1) {
			cnt++;
			return;
		}
		
		if(shape != 1 && cu_col+1 < N && arr[cu_row][cu_col+1] == 0)
			dfs(cu_row, cu_col + 1, 0);
		if(shape != 0 && cu_row+1 < N && arr[cu_row+1][cu_col] == 0)
			dfs(cu_row + 1,cu_col, 1);
		//세군데 의 합이 0이면 대각선 가능
		if(cu_row +1 < N & cu_col +1 < N &&
				arr[cu_row+1][cu_col]+arr[cu_row][cu_col+1]+arr[cu_row+1][cu_col+1] == 0)
			dfs(cu_row+1, cu_col+1, 2);
	}
}
