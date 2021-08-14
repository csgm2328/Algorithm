package Lv2;
import java.util.*;

public class _2020_카카오_인턴쉽_수식최대화 {
	public static void main(String[] args) {
		System.out.println(solution("100-200*300-500+20"));
	}
	static List<Integer> num = new ArrayList<Integer>();
	static List<Character> op = new ArrayList<Character>();
	static boolean visited[];
	static char[] save;
	static long max;
	
	private static long solution(String expression) {
		// 절댓값이 가장큰 중위표현식 우선순위 결정하기
		// 6가지 순열을 반복한다.
		// 스택을 사용해서 현재 우선연산만 해서 결과를 저장하고
		// 다시 스택에서 빼서 다음 연산식을 만들고z
		// 이 과정을 *,+,- 세번하면 한가지 순열은 끝

		char[] order = { '*', '+', '-' };
		String temp = "";
		for (int i = 0; i < expression.length(); i++) {
			if ('0' <= expression.charAt(i) && expression.charAt(i) <= '9') {
				temp += expression.charAt(i);
			} else {
				num.add(Integer.parseInt(temp));
				temp = "";
				op.add(expression.charAt(i));
			}
		}
		num.add(Integer.parseInt(temp));
		visited = new boolean[3];
		save = new char[3];
		max = Integer.MIN_VALUE;
		perm(order, 0, expression);
		long result = max;
		return result;
	}

	

	private static void perm(char[] order, int cnt, String expression) {
		if (cnt == order.length) {
			// 중위 표기식 계산
			max = Math.max(max, calc(expression));
			return;
		}
		for (int i = 0; i < order.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				save[cnt] = order[i];
				perm(order, cnt + 1, expression);
				visited[i] = false;
			}
		}

	}

	private static long calc(String expression) {
		List<Long> calc_num = new ArrayList<Long>();
		List<Character> calc_op = new ArrayList<Character>();
		for (long x : num)
			calc_num.add(x);
		for (char x : op)
			calc_op.add(x);

		for (int i = 0; i < save.length; i++) {
			char curOp = save[i];
			for (int j = 0; j < calc_op.size(); j++) {
				if (calc_op.get(j) == curOp) {
					long x = solve(calc_num.get(j), curOp, calc_num.get(j + 1));
					calc_num.set(j, x);
					calc_num.remove(j+1);
					calc_op.remove(j);
					j--;
				}
			}
		}
		return Math.abs(calc_num.get(0));
	}

	private static long solve(Long a, char oper, Long b) {
		switch (oper) {
		case '*':
			return a * b;
		case '+':
			return a + b;
		case '-':
			return a - b;
		default:
			return 0;
		}
	}
}
