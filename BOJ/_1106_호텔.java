import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class _1106_호텔 {
	static int C,N;
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C= Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][]; // 최대 1000명 추가해야함
		int max = -1;
		for(int i =0; i<N;i++) {
			st =new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int plus = Integer.parseInt(st.nextToken());
			arr[i] = new int[] {cost,plus};
			max = Math.max(plus, max);
		}
		int[] dp = new int[C+max+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] =0;
		for(int i =1; i<= C + max;i++) { //필요한 사람추가까지
//			for(int j = 0;j < N; j++) { //추가 가능한 것중에 가장 큰 걸 저장
//				if(i - arr[j][0]>= 0)
//					dp[i] = Math.max(dp[i], dp[i-arr[j][0]] + arr[j][1]);
//				if(dp[i] >= C) {
//					System.out.print(i);
//					System.exit(0);
//				}
//			}
			// 반대로 사람당 최소비용으로 간다
			// 그러면 100명을 채워야 할때 1원으로 99명으로 추가를 할 수 있다면
			// 100 + 99까지 해봐야 2원임을 알수 있음
			for(int j = 0; j < N;j++) {
				if(i - arr[j][1] >= 0 && dp[i- arr[j][1]] != Integer.MAX_VALUE)
					dp[i] = Math.min(dp[i], dp[i-arr[j][1]] + arr[j][0]);
			}
		}
		int min = Integer.MAX_VALUE;
		for(int i = C; i<=C+max;i++) {
			if(min > dp[i])
				min = dp[i];
		}
		System.out.println(min);
	}
}
