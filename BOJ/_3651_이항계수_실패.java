import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _3651_이항계수_실패 {
	static BufferedReader input;
	static StringBuilder output;
	static StringTokenizer st;
	static long m;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		output = new StringBuilder();
		m = Long.parseLong(input.readLine()); // 2 ~ 100조
		// 근데 k가 작아서 완전탐색이 가능하다는데????
		// 이분탐색 문제
		// 일단 시작할 위치를 정한다
		// 15이면 가까운 2의 제곱승 16(2^4)다음부터 시작한다
		long two = 2;
		int square = 1;
		while (two < m) {
			two *= 2;
			square++;
		}
		// index+1부터 조합시작
		int n = square + 1;
		while (n <= m) {
			combination(n);
			n++;
		}
		System.out.println(cnt);
		System.out.println(output.toString());
	}
	//10억까지 1초걸림
	private static void combination(int n) {
		// m과 지금까지 모아온 값이 같으면 그만
		long x = n; // 5
		if (x == m) { //k == 1일떄
			if(x==2) {
				output.append(n + " " + 1 + "\n"); //중간인 경우는 하나만
				return;
			}
			output.append(n + " " + 1 + "\n");
			output.append(n + " " + (n - 1) + "\n");
			cnt+=2;
			return;
		}
		for (int k = 2; k < n; k++) {
			x = x * (n - k + 1) / k;
			if(x > m)
				return;
			if (x == m) {
				if(k == n/2) {
					output.append(n + " " + k + "\n");
					return;
				}
				output.append(n + " " + k + "\n");
				output.append(n + " " + (n - k) + "\n");
				cnt+=2;
				return;
			}
		}
	}
}
