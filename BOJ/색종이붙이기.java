import java.util.Scanner;

public class 색종이붙이기 {
	static int[][] map = new int[10][10];
	static int[] paper = {0, 5, 5, 5, 5, 5};
	static int ans = 987654321;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++)
				map[i][j] = sc.nextInt();
		}
		backtrack(0);
		System.out.println(ans == 987654321 ? -1 : ans);
	}
	static void backtrack(int cnt) {
		//처음으로 1인곳을 찾자.
		int sR = -1, sC = -1;
		out:for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(map[i][j] == 1) {
					sR = i; sC = j;
					break out;
				}
			}
		}
		//만약 sR과 sC가 여전히 -1로 남아있다면, 더이상 1이 없는것. 그러니까 색종이붙이기가 끝
		if( sR == -1 && sC == -1) {
			ans = Math.min(ans, cnt);
			return;
		}
		//내가 찾은 1로 시작하는 공간이 최대 얼만큼 크기의 색종이가 붙어질 수 있는ㄴ지 검사
		int max = 5;
		while(max > 0) {
			boolean isok = true;
			//max크기로 색종이를 붙일 수 있는지?
			out:for(int i = 0; i < max; i++) {
				for(int j = 0; j < max; j++) {
					if( sR + i >= 10 || sC + j >= 10 || map[sR+i][sC+j] == 0) {
						isok = false;
						break out;
					}
				}
			}
			//요기왔는데, isok가 여전히 true라는건 30라인을 들어가보지 못했다는 것.
			if(isok)
				break;
			//아니라면 max하나 줄이고 다시 검사하러 가자.
			max--;
		}
		//재귀 구조를 통해 cnt가 가장 작은것의 조합을 뽑아낼거임
		for(int i = 1; i <= max; i++) {
			if( paper[i] == 0 )
				continue;
			for(int r = sR; r < sR+i; r++) {
				for(int c = sC; c < sC+i; c++)
					map[r][c] = 0;
			}
			paper[i]--;
			backtrack(cnt+1);
			for(int r = sR; r < sR+i; r++) {
				for(int c = sC; c < sC+i; c++)
					map[r][c] = 1;
			}
			paper[i]++;
		}
	}
}
