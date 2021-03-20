package Lv3;
import java.util.Scanner;
//PG level 3
//DFS/BFS

public class 네트워크 {
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int n = sc.nextInt();
		int[][] arr =new int[n][n];
		for(int i = 0; i< 3; i++) {
			for(int j =0; j< 3; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		System.out.println(solution(n,arr));
	}
	static boolean[] visited;
	static int solution(int n, int[][] map) {
		visited = new boolean[n];
		int cnt =0;
		for(int i = 0; i< n; i++) {
			for(int j =0; j< n; j++) {
				if(map[i][j] == 1 && !visited[j]) {
					dfs(n,j,map); //j 정점으로
					cnt++;
				}
			}
		}
		return cnt;
	}
	private static void dfs(int n, int vertex, int[][] map) {
		visited[vertex] = true;
		
		for(int i =0; i< n; i++) {
			if(i != vertex && map[vertex][i] == 1 && !visited[i])
				dfs(n,i,map);
		}
	}
}
