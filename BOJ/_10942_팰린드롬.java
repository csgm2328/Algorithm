import java.io.*;
import java.util.*;

// 펠린드롬
// 2차원 dp에다가 [s][e] 상태로 펠린드롬여부를 저장해놓는다
public class _10942_팰린드롬 {
	static int N, M;
	static int[] arr;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		arr = new int[N + 1]; // 수열의 길이 1 ~ 2000
		dp = new int[N+1][N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());

		for(int[] x: dp)
			Arrays.fill(x, -1);
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if (isPalindrome(start, end))
				sb.append(1 + "\n");
			else
				sb.append(0 + "\n");

		}
		System.out.println(sb.toString());
	}

	private static boolean isPalindrome(int start, int end) {
		if(dp[start][end] != -1) {
			if(dp[start][end] == 1)
				return true;
			else
				return false;
		}
		if(start == end || start > end) {
			dp[start][end] =1;
			return true;
		}
		if(arr[start] != arr[end]) { //비교하는게 다르면
			dp[start][end] =0;
			return false;
		}
		//비교하는게 같으면  다음 거 비교
		if(isPalindrome(start+1, end-1)) {
			dp[start][end] = 1;
			return true;
		}
		else {
			dp[start][end] = 0;
			return false;
		}
	}
}
