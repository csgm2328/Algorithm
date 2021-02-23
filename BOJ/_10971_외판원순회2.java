import java.io.*;
import java.util.*;

public class _10971_외판원순회2 {
	static StringBuilder sb = new StringBuilder();
	static int N, cnt; 
//	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 }; // 8방탐색
//	static int[] dc = { 0, 0, -1, 1, -1, 1, 1, -1 };
	
	static int Min = Integer.MAX_VALUE;
	static int[][] arr;
	static int[] save;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N];
		save = new int[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		TSP(0);
		System.out.print(Min);
	}
	//TSP는 순열
	// 2 3 1 0
	// 0 2 3 1 은 같은 값임
	
	static void TSP(int cnt) {
		if(cnt == N) {
			//거리 비용계산
			int sum =0;
			int current = save[0];
			for(int i =1; i<N; i++) {
				int des = save[i];
				if(arr[current][des] == 0)//갈수 없는 경우가 있다 가중치가 0인경우
					return;
				sum += arr[current][des];
				current = des;
			}
			if(arr[current][save[0]] == 0)
				return;
			sum += arr[current][save[0]];
			if(Min > sum) {
				Min = sum;
			}
			return;
		}
		
		for(int i =0; i< N; i++) {
			if(visited[i])
				continue;
			visited[i] = true;
			save[cnt] = i;
			TSP(cnt+1);
			visited[i] = false;
		}
	}
}
