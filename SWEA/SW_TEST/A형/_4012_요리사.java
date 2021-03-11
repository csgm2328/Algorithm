package SW_TEST.A형;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//N을 2/N으로 만드는 조합의 경우에서
//선택된 것들 안에서 2/N P 2 수열의 경우의 수의 합을 구한후
//그 합의 값 A, B의 차가 최소인 것을 찾는다
public class _4012_요리사 {
	static int N;
	static int[][] arr;
	private static int[] visit;
	static int diff = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visit = new int[N];
			
			for(int i = 0; i< N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j= 0; j< N; j++)
					arr[i][j] = Integer.parseInt(st.nextToken()); 
			}
			
			combination(0,0);
			
			sb.append("#" + tc + " " + diff + "\n");
			diff = Integer.MAX_VALUE;
		}// end tc
		System.out.println(sb.toString());
	}
	static List<Integer> A,B;
	private static void combination(int cnt, int start) {
		if(cnt == N/2)
		{
			//A, B가 나눠졌으면 그 상태에서 수열의 합구하기
			A = new ArrayList<Integer>();
			B = new ArrayList<Integer>();
			for(int i = 0; i < N; i++) {
				if(visit[i] == 1) //A 
					A.add(i);
				else 	//B
					B.add(i);
			}
			//다 나눴으면
			visit_temp = new boolean[N/2];
			sum = 0;
			permutation(A, 0,0);
			int sumA = sum;
			
			Arrays.fill(visit_temp, false);
			sum = 0;
			permutation(B, 0,0);
			int sumB = sum;
			//결과
			if(diff > Math.abs(sumA - sumB))
				diff = Math.abs(sumA - sumB);
			return;
		}
		
		for(int i = start; i< N; i++) {
			visit[i] = 1;
			combination(cnt+1, i+1);
			visit[i] = 0;
		}
	}
	static boolean[] visit_temp;
	static int sum;
	//사실은 조합임 조합으로 순열 경우의 수의 합구하는거
	private static void permutation(List<Integer> list, int cnt,int start) {
		//R == 2로 고정
		if(cnt == 2) {
			int x= -1,y =-1;
			for(int i = 0; i< N/2; i++) {
				if(visit_temp[i]) {
					if(x == -1)
						x = list.get(i);
					else
						y = list.get(i);
				}
			}
//			System.out.println(x +"" + y + " " + y + "" + x);
			sum += arr[x][y] + arr[y][x];
			return;
		}
		for(int i =start; i< N/2; i++) {
//			if(visit_temp[i]) continue;
			visit_temp[i] = true;
			permutation(list, cnt+1,i+1);
			visit_temp[i] = false;
		}
	}
}
