

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//N <= 10 ^ 15
//DP를 어떻게 ????
//메모리 제한 128MB로 늘었으니까 재귀  OK
//14MB 사용
public class _1179_마지막_요세푸스 {
	static long josephus(long n, int k) {
		if (n == 1)
			return 0;
		if (k == 1)
			return n - 1;
		else if (n < k) {
			return ((josephus(n - 1, k) + k) % n);
		}
		// 보통의 경우 연산 줄이기 n/k와 n%k로
		// n이크고 k가 작을 때 많은 연산을 건너 뛸 수 있음
		long cnt = n / k;
		long result = josephus(n - cnt, k) - n % k;
		if (result < 0)
			result += n;
		else
			result += result / (k - 1);

		return result;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		System.out.print(josephus(N, K) + 1);
	}
}
