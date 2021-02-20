
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시작과 끝 s, e로 표시해주는 포문 지웠는데 실행시간 변동없음,, 왜???
// 행 길이만큼 시작
// 근데 최대한 짧은 거리를 가야 최대 개수를 만들 수 있음
// 이걸 처음부터 가장 효율적인 길을 찾는 보장이 없는데

// 1. 전진 3방향으로만 가면 최적 파이프만 생성
// 2. 파이프 생성하면 백트랙킹 멈추고 새로운 시작점으로
// 3. 파이프를 생성하지 못한 visited도 유지해야 효율적이다

public class _3109_빵집 {
	static char[][] arr;
	static int[] dr = { -1, 0, 1 }; // 앞으로 전진만
//	static int[] dc = { 1, 1, 1 }; //어차피 다 1이니까 필요없음
	static int R, C, cnt;
	static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][];

		for (int i = 0; i < R; i++)
			arr[i] = br.readLine().toCharArray();

		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) 
			DFS(i, 0);
//		if (Max < cnt) //앞으로만 전진하는 최적의 파이프만 생성하므로 필요없음
//			Max = cnt;
		System.out.print(cnt);
	}
	
	//boolean 타입으로 flag없이 자동으로 끝을 만나면 아예 빠져나오도록 할 거임
	//1. 방문 처리
	//2. 탈출 조건
	//3. 재귀 조건
	static boolean DFS(int cu_row, int cu_col) { 
		visited[cu_row][cu_col] = true;
		if (cu_col == C-1) {// 끝에 도착하면
			cnt++;
			return true;// 파이프 만들었다면 뒤돌아가지말고 새로운 시작점으로
		}
		// 동작
		for (int dir = 0; dir < 3; dir++) {
			int NextR = cu_row + dr[dir];
			int NextC = cu_col + 1;
			if (NextR >= 0 && NextR < R && NextC >= 0 && NextC < C) {// 범위 안이면
				if (arr[NextR][NextC] == '.' && !visited[NextR][NextC]) {// 미방문이고
					if(DFS(NextR, NextC)) //리턴될때 파이프 만들었다면 
						return true; //쭉 나가짐
					//못만들었으면 다른 길 찾으러
				}
			}
		}//end for
		return false; //갈 곳이 없다면
	}
}
