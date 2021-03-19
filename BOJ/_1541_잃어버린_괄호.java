import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

import sun.security.util.Length;

public class _1541_잃어버린_괄호 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int N, M;
	static int[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		char[] s = input.readLine().toCharArray();
		StringBuilder num = new StringBuilder();
		char oper = '+'; // 처음 +부터 시작
		int sum = 0;
		for (int i = 0; i < s.length; i++) {
			// 숫자먼저
			// 연산자 -나오면 그후로 - 나올떄까지 묶음
			// 숫자
			if ('0' <= s[i] && s[i] <= '9') {
				if (s[i] == '0' && num.length() == 0)
					continue;
				num.append(s[i]);
			} else { // 연산자나오면
				if (oper == '+') {
					for (int j = 0, index = num.length() - 1; j < num.length(); j++)
						sum += (num.charAt(index--) - '0') * Math.pow(10, j);
				} else { // -일때
					for (int j = 0, index = num.length() - 1; j < num.length(); j++)
						sum -= (num.charAt(index--) - '0') * Math.pow(10, j);
				}
				// 먼저 저장해논거 반영하고 이제 나온거로 연산자 변경
				num = new StringBuilder();
				if (s[i] == '-') // - 나오면 영원히 뺴기
					oper = '-';
			}
		} // end for
		//마지막 수 연산
		if(oper == '-') {
			for (int j = 0, index = num.length() - 1; j < num.length(); j++)
				sum -= (num.charAt(index--) - '0') * Math.pow(10, j);
		}
		else {
			for (int j = 0, index = num.length() - 1; j < num.length(); j++)
				sum += (num.charAt(index--) - '0') * Math.pow(10, j);
		}
		System.out.print(sum);
	}
}
