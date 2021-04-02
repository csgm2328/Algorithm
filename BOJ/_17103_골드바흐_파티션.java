import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _17103_골드바흐_파티션 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int T, N;

	public static void main(String[] args) throws IOException {
		// global init
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		// 체
		boolean isprime[] = new boolean[1000001]; // 1 백만
		Arrays.fill(isprime, true);
		isprime[0] = false;
		isprime[1] = false;
		for (int i = 2; i < isprime.length; i++) {
			if (isprime[i]) {
				for (int j = i + i; j < isprime.length; j += i) {
					isprime[j] = false;
				}
			}
		}
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			// 서로 다른 골드바흐 파티션의 개수
			// N = 소수 + 소수
			int cnt = 0; // 경우의 수
			for (int i = 2; i <= N / 2; i++) {
				if (isprime[i] && isprime[N - i]) {
					cnt++;
				}
			}
			sb.append(cnt + "\n");
		}
		System.out.println(sb.toString());
	}
}
