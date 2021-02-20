package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 기본_4일차_괄호짝짓기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());

			String[] s = br.readLine().split("");
			Stack<Character> st = new Stack<Character>();
			int flag = 1;
			
			for (int i = 0; i < N; i++) {
				if (!st.empty()) {	//비어있는지 체크
					if (s[i].charAt(0) == '}') {	//charAt(i) 이;런거좀 주의하자
						if (st.peek() != '{') 	// 올바른 닫기가 아니면 끝내기
							break;
						st.pop();	//올바른 닫기면 POP
					} else if (s[i].charAt(0) == '>') {
						if (st.peek() != '<') 
							break;
						st.pop();
					} else if (s[i].charAt(0) == ')') {
						if (st.peek() != '(')
							break;
						st.pop();
					} else if (s[i].charAt(0) == ']') {
						if (st.peek() != '[') 
							break;
						st.pop();
					}
					else	//닫기가 아니면
						st.add(s[i].charAt(0));		
				}	//안비어있을 떄는 바로 추가
				else st.add(s[i].charAt(0));
			}
			flag = st.empty() ? 1 : 0;
			sb.append("#" + tc + " " + flag + "\n");
		}
		System.out.println(sb.toString());
	}
}
