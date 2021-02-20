package D3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 파도반_수열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		long[] dp = new long[100];
		dp[0] = 1;
		dp[1] = 1;
		dp[2]= 1;
		dp[3] = 2;
		dp[4] = 2;
		
		for(int i = 5; i< 100; i++) {
			dp[i] = dp[i-1] + dp [i-5];
		}
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			sb.append("#" + tc + " " + dp[N-1] + "\n");	
		}
		
		System.out.println(sb.toString());
	}
}
