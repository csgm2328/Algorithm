package D3;
import java.io.*;
import java.util.*;

// 조합 수학적 계산법
// 나누기는 mod 연산이 안되니
// 페르마 소정리를 통해 1/a 를 a^(p-2) mod p로 변환한다
public class _5607_조합 {
	static int N, r;
	static final int MOD = 1234567891;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			long[] fact = new long[N + 1]; // N까지의 팩토리얼 나머지값 저장
			fact[0] = 1;
			for (int i = 1; i <= N; i++)
				fact[i] = (fact[i - 1] * i) % MOD;

			long bottom = (fact[r] * fact[N - r]) % MOD;
			long reverse_bottom = pow(bottom, MOD - 2); // 1/a --> a^(p-2) 꼴로 변환
			System.out.println((fact[N] * reverse_bottom) % MOD);
			sb.append("#" + tc + " " + (fact[N] * reverse_bottom) % MOD + "\n");
		}
		System.out.println(sb.toString());
	}
	//O(logN) 거듭제곱구하기
	// 짝수 : a^n/2 * a*n/2
	// 홀수 : a^(n-1)/2 * a^(n-1)/2 * a
	private static long pow(long n, int x) {
		if (x == 0)
			return 1;
		long temp = pow(n, x / 2);
		long result = (temp * temp) % MOD;
		if (x % 2 == 0)
			return result;
		else
			return (result * n) % MOD;
	}

}
