import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1,1 --> N, M까지
// 벽한번 부수기 가능
// bfs라고?
public class _2206_벽부수고이동 {
	static StringBuilder sb = new StringBuilder();
	static int R, C; // 1 ~ 15
	static int chance = 1;
	static int[] dr = { 1, 0, 0,-1 }; //하 , 우  먼저
	static int[] dc = {0, 1, -1,0 };

	static int Max = -1;
	static int[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		visited = new boolean[R][C];
		
		for(int i =0; i< R; i++) {
			String s[] = br.readLine().split("");
			for(int j =0 ; j< C; j++) {
				arr[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		
	}
}