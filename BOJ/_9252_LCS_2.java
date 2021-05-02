import java.io.*;
import java.util.*;

// LCS 2
// dp로 작은해를 이용할 방식이 생각나지 않으면
// 문제의 이론적인 해법을 생각하자.
// dp[X길이][Y길이] = 공통문자열길이
// Xm == Yn, Xm-1,Yn-1보다 하나더 길어진다
// 다를 때는 m-1,n or m,n-1 중에 큰거를 선택한다
// 이번에는 공통문자열도 저장한다

public class _9252_LCS_2 {
	static String str1, str2;

	static class LCS {
		String commons;
		int length;

		public LCS(String commons, int length) {
			super();
			this.commons = commons;
			this.length = length;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		str1 = br.readLine();
		str2 = br.readLine();
		int len1 = str1.length();
		int len2 = str2.length();

		LCS[][] dp = new LCS[len1 + 1][len2 + 1];
		for (int i = 0; i <= len1; i++)
			dp[i][0] = new LCS("", 0);
		for (int i = 0; i <= len2; i++)
			dp[0][i] = new LCS("", 0);

		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					char c = str1.charAt(i - 1);
					dp[i][j] = new LCS(dp[i - 1][j - 1].commons + c, dp[i - 1][j - 1].length + 1);
				} else { // 비교문자가 다르면 전에꺼들 중에 큰거로 고름
					if (dp[i - 1][j].length > dp[i][j - 1].length)
						dp[i][j] = new LCS(dp[i - 1][j].commons, dp[i - 1][j].length);
					else
						dp[i][j] = new LCS(dp[i][j - 1].commons, dp[i][j - 1].length);
				}
			}
		}
		if (dp[len1][len2].length == 0)
			System.out.println(0);
		else {
			System.out.println(dp[len1][len2].length);
			System.out.println(dp[len1][len2].commons);
		}

	}

}
