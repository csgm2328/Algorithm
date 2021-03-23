import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _12852_1로만들기_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();  
				
        int n = Integer.parseInt(br.readLine());
		// DP
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 0;
		
		for (int i = 2; i <= n; i++) {
			// 기본적으로 -1 안나눠떨어질떄도 연산되도록
			dp[i] = dp[i - 1] + 1;
			if (i % 2 == 0)
				dp[i] = Math.min(dp[i / 2] + 1, dp[i]);
			if (i % 3 == 0)
				dp[i] = Math.min(dp[i / 3] + 1, dp[i]);
		}
        System.out.println(dp[n]);
        int m = n;
        while(m != 1) {
        	sb.append(m + " ");
        	int result = 1; // -1 선택
        	if (m % 2 == 0 && dp[m / 2] + 1 <= dp[m] ) //같으면 바꿔줘야함
        		result =2; //2 나누기 선택
        	if (m % 3 == 0 && dp[m / 3] + 1 <= dp[m] )
				result = 3;
        	if(result == 1)
        		m-=1;
        	else
        		m/=result;
        }
        sb.append(m);
        System.out.println(sb.toString());
	}
}
