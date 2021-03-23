import java.util.Scanner;

public class _11727_2xN타일링_2 {
	//2x2타일이 하나추가되었을때
	// 2칸에 놓을 수 있는방법이 3가지다
	// 그럼 3칸에 놓을 방법은 세운거랑 2x2 순서 교환 6가지 인데 다세운거 중복이므로 -1 5가지
	// 4칸 일때는 1칸,2칸,1칸 채우는 경우도 있다 11가지
	// 그러므로 1칸  + 2 * 2칸
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int dp[] = new int[N+1];
		if(N == 1) {
			System.out.println(1);
			System.exit(0);
		}
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 3; //1일때 터진다
		for(int i= 3; i<= N; i++) {
			dp[i] = (dp[i-1] + 2 * dp[i-2]) % 10007;
		}
		System.out.println(dp[N]);
	}
}
