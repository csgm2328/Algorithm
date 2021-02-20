import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

// bufferedReader 스태틱 선언하면 Scanner보다 더오래걸림 164ms -> 260ms 스캐너 240ms
// Scanner도 입력하나인데도 4ms 차이나니까 지역변수로 선언해주자
// 근데 재귀나 DP나 속도는 똑같다

public class _1463_1로만들기 {
	static Scanner sc = new Scanner(System.in);
	
	static int n; // < 10^6
	static int result = 1000001;

	static void powerset(int n, int cnt) {
		if (n <= 1) {
			// min 검사
			if (result > cnt)
				result = cnt;
		}
		if (cnt > result)
			return; // 이미 최저가 아니라면 그만

		// 3개의 연산
		if (n % 3 == 0)
			powerset(n / 3, cnt + 1);
		if (n % 2 == 0)
			powerset(n / 2, cnt + 1);
		powerset(n - 1, cnt + 1);
	}

	// DP로도 풀어보기
	static int[] dp;

	static void Dynamic(int n) {
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
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		//n = sc.nextInt();
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 n = Integer.parseInt(br.readLine());
		//powerset(n, 0);
		// DP
		dp = new int[n + 1];
		Dynamic(n);
		//System.out.println(result);
		System.out.println(dp[n]);
		br.close();
	}
}
