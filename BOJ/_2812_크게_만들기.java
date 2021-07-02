import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 크게 만들기
//앞에서부터 주어진 개수만큼 지워서 가장 큰수를 만든다
//스택으로 다음에 큰수가 들어오면 가능한 만큼 앞의 작은수들을 다 지운다

public class _2812_크게_만들기 {
	static int N, K; // 1 ~ 50만 자리

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		String input[] = br.readLine().split("");
		Stack<String> stack = new Stack<>();
		stack.push(input[0]);

		for (int i = 1, cnt = 0; i < N; i++) {
			while (!stack.isEmpty() && stack.peek().compareTo(input[i]) < 0 && cnt < K) { // 새로 들어오려는게 더 크면
				if (cnt == K)
					break;
				stack.pop();
				cnt++;
			}
			stack.push(input[i]);
		}

		StringBuilder sb = new StringBuilder();
		// 반례처리 : 6 4 199244 같은 경우 9944가 남게되는데 99가 답이다
		// 따라서 stack.size()만큼이 아니라 stack에 있는 것중 N - K만큼만 출력해야함
		// 정상으로 지워지는 경우는 당연히 N-K가 스택사이즈고 이 경우에는 앞에 있는게 커서 안지워졌으므로 앞에만
		// 출력하면 됨
		for (int i = 0; i < N - K; i++)
			sb.append(stack.get(i));
		System.out.print(sb.toString());
	}
}