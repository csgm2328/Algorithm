import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//거스름돈
//dp를 0 ~ 목표 돈까지 늘려가면서
//그리디 방법과 비교한다
public class _13137_Exchange_Problem {
	static BufferedReader br;
	static StringTokenizer st;
	static int P,coins[];
//	static int N, coins[] = {2,5}; // 14916_거스름돈

	public static void main(String[] args) throws IOException {
		// global init
		br = new BufferedReader(new InputStreamReader(System.in));
//		N = Integer.parseInt(br.readLine()); // 14916_거스름돈
//		System.out.println(NormalExchange(N)); // 14916_거스름돈

		P = Integer.parseInt(br.readLine());
		coins = new int[P];
		st = new StringTokenizer(br.readLine());
		int N = 0; //목표 지점 
		for(int i =0; i< P; i++) {
			coins[i] = Integer.parseInt(st.nextToken());
			N += coins[i]; // 액면가 다 더한금액까지 해본다
		}
		
		// 실수 포인트!!
		// DP 큰 숫자기준으로 만들어서 coins안에 포함되는 것보다 돈이 작을 때 생각을 못했다
		int[] dp = new int[N + 1];
		for (int i = coins[0]; i <= N; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (i == coins[j]) { //현재 돈이 액면가랑 같으면 바로 1
					dp[i] = 1;
				}
				else if (i >= coins[j] && dp[i - coins[j]] != 0) {
					if (dp[i] == 0) { //dp[4]일때 일단 dp[2] +1 넣고 다음부터 최소비교
						dp[i] = dp[i - coins[j]] + 1;
						continue;
					}
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				}
			}
			if(dp[i] != GreedyExchange(i)) { //해당 돈을 그리디방법으로 구했을때
//				System.out.println(i + " DP로 구한 개수: "  + dp[i]);
//				System.out.println(i + " 그리디방법 개수: " + GreedyExchange(i));
				System.out.print("No");
				System.exit(0);
			}
		}
//		if(dp[N] == 0)
//			System.out.println(-1);
//		else
//			System.out.println(dp[N]);
		System.out.print("Yes");
	}

	private static int GreedyExchange(int money) {
		int cnt = 0;
		for (int i = coins.length - 1; i >= 0; i--) {
			if (money >= coins[i]) {
				cnt += money / coins[i];
				money %= coins[i];
				if (money == 0)
					break;
			}
		}
		return cnt;
	}

	private static int NormalExchange(int money) {
		int fivecnt = 0, twocnt = 0;
		while (money >= 2) {
			if (money % 5 == 0) {
				fivecnt += (money / 5);
				money = 0;
				break;
			}
			if (money - 5 > 0 && (money - 5) % 2 == 0) { // 3일때 -2 % 2 == 0이 됨
				money -= 5;
				fivecnt++;
			}
			money -= 2;
			twocnt++;
		}
		if (money == 1)
			return -1;
		else
			return fivecnt + twocnt;
	}
}
