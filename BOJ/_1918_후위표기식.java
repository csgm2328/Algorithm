
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//6h
public class _1918_후위표기식 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static String[] s;
	static List<Character> ans = new ArrayList<Character>(); // 변환된 후위표기법 담을 곳(변환전이랑 길이다름)

	static int priority(char input) {
		switch (input) {
		case '+':
			return 2;
		case '-':
			return 2;
		case '*':
			return 1;
		case '/':
			return 1;
		default:
			return 3; // 값이 더 작은 '(' 이랑 비교할때 pop안되도록
		}
	}

	static void trans_postfix(String[] s) {
		Stack<Character> op = new Stack<Character>();

		for (int i = 0; i < s.length; i++) {
			char input = s[i].charAt(0);
			if (input == '*' || input == '/' || input == '+' || input == '-') { // 연산자일떄
				if (op.isEmpty()) // 비어있거나 숫자가 2개이상 없으면 일단 넣음
					op.push(input);
				// 실수! 아니면 우선순위 비교 우선순위가 높은게 오면 아무것도 꺼내지말고 PUSH해야함
				else {// 우선순위 낮은거 오면
					while (!op.isEmpty() && priority(op.peek()) <= priority(input)) { 
						ans.add(op.pop()); // 스택에 있는 연산자 다 뺌
						if (op.isEmpty())
							break;
					}
					//실수! input이 우선 순위가 더 높거나 peek()으로 괄호를 만나거나 하면 while 탈출해야함
					op.add(input); // 현재 연산자 저장 
				}
			} else if (input == '(') // 괄호 만나면
				op.add(input);

			else if (input == ')') { // 괄호 닫히면
				while (op.peek() != '(') { // 여는거 올때까지 스택에 남은거 다 꺼내기
					ans.add(op.pop());
				}
				op.pop(); // '(' 제거
			} else // 피연산자는 바로 나옴
				ans.add(input);
		}
		while (!op.isEmpty()) // 괄호없는 인풋은 남아있으니까
			ans.add(op.pop());

		for (int i = 0; i < ans.size(); i++)
			sb.append(ans.get(i));
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		s = br.readLine().split("");
		trans_postfix(s);
	}
}
