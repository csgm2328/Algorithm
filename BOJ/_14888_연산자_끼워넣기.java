import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//연산자 바꿔껴서 최소 최대 찾는 순열
//2^(n-1)
public class _14888_연산자_끼워넣기 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] arr;
	static boolean[] visited;
	static char[] fixed = { '+', '-', '*', '/' };
	static char[] opers, save;
	static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

//	static int[] dr = { -1, 0, 1, 0 };
//	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// global init
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		visited = new boolean[N-1];
		opers = new char[N - 1];
		save = new char[N - 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int index = 0;
		for (int i = 0; i < 4; i++) {
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				opers[index++] = fixed[i];
			}
		}

		// 순열에서 중복 제거
		combination(0);
		sb.append(max).append('\n').append(min);
		System.out.print(sb.toString()); 
//		System.out.println(s);
	}
	//104ms
	private static void combination(int cnt) {
		if (cnt == N - 1) {
			int result = arr[0];
			System.out.println(Arrays.toString(save));
			for (int i = 0; i < N - 1; i++) {
				switch (save[i]) {
				case '+':
					result += arr[i + 1];
					break;
				case '-':
					result -= arr[i + 1];
					break;
				case '*':
					result *= arr[i + 1];
					break;
				case '/':
					result /= arr[i + 1];
					break;
				default:
					break;
				}
			}
			if (min > result)
				min = result;
			if (max < result)
				max = result;
			return;
		}
		char before = '0'; //지역적으로 해당 재귀단계에서 이전꺼와 겹치는지 체크
		//정렬된 상태에서 가능하다
		//전에 선택한것과 똑같은 걸 선택하면
		//그 다음 단계부터도 똑같다는 거기 때문에 생략됨
		for (int i = 0; i < N - 1; i++) {
			if (!visited[i] && before != opers[i]) {
				save[cnt] = opers[i];
				visited[i] = true;
				before = opers[i];
				combination(cnt + 1);
				visited[i] = false;
			}
		}
	}
	
	//72ms
	static void solve(int sum, int index, int plus, int minus, int multiple, int divide) {

		if (index == N) {
			if (sum > max) {
				max = sum;
			}

			if (sum < min) {
				min = sum;
			}

			return;
		}

		if (plus > 0) {
			solve(sum + arr[index], index + 1, plus - 1, minus, multiple, divide);
		}

		if (minus > 0) {
			solve(sum - arr[index], index + 1, plus, minus - 1, multiple, divide);
		}

		if (multiple > 0) {
			solve(sum * arr[index], index + 1, plus, minus, multiple - 1, divide);
		}

		if (divide > 0) {
			if (sum < 0) {
				solve(-((-sum) / arr[index]), index + 1, plus, minus, multiple, divide - 1);
			} else {
				solve(sum / arr[index], index + 1, plus, minus, multiple, divide - 1);
			}
		}
	}
}
