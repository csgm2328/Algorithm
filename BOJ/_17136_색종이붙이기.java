import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
//

public class _17136_색종이붙이기 {
	static int N = 10;
	static int[][] arr = new int[N][N];
	static int[] paper = {0, 5, 5, 5, 5, 5};
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		backtracking(0); 
	
		System.out.print(min == Integer.MAX_VALUE ? -1 : min); 
		
		//dfs 구조로 색종이를 붙일 수 있는 경우의 수 다 찾고 min을 찾아낸다
		
	}
	static void backtracking(int cnt) {
		
		//시작점 찾기
		int startR = -1,startC = -1;
		out:for(int i =0; i< N; i++) {
			for(int j =0 ; j< N; j++) {
				if(arr[i][j] == 1) {
					startR = i;
					startC = j;
					break out;
				}
			}
		}
		//탈출 더이상 1이 없을떄 
		if(startR == -1) {
			min = Math.min(min, cnt); //0도 처리됨 하지만 -1일때는 여기 도달 못함
			return;
		}
			
		//붙일 색종이의 크기 찾기
		int max_size = 5;
		while(max_size > 1) {
			boolean attachble = true;
			out:for(int i =startR; i< startR+max_size; i++) {
				for(int j =startC ; j< startC+max_size; j++) {
					if(i >= N || j >= N || arr[i][j] == 0) {
						attachble = false;
						break out;
					}
				}
			}
			if(attachble) break; //그 면적이 그 사이즈로 꽉 차있으면 나옴
			max_size--;
		}
		
		//재귀 구조
		for(int size = 1; size<=max_size; size++) { //붙일 수 있는 최대크기보다 작은 것들도 붙여본다
			if(paper[size] == 0)
				continue;
			for(int i = startR; i< startR+size; i++) 
				for(int j = startC; j < startC+size; j++)
					arr[i][j] = 0;
			paper[size]--;
			
			backtracking(cnt+1);
			//다른 경우도 해보기 위해 되돌린다
			for(int i = startR; i< startR+size; i++) 
				for(int j = startC; j < startC+size; j++)
					arr[i][j] = 1;
			paper[size]++;
		}
	}
}
		
