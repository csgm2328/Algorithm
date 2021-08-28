import java.io.*;
import java.util.*;

// 소수인 a가 b가 되기 까지 한자리씩 바꿔서 걸리는 최소 회수
public class _1963_소수경로 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// che
		boolean[] isPrime = new boolean[10000];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		for (int i = 2; i < 10000; i++) {
			if (isPrime[i]) {
				for (int j = i + i; j < 10000; j += i)
					isPrime[j] = false;
			}
		}
		// 한자리만 바뀌는게 bfs 원칙과 같음
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 입력은 네자리 소수
			int b = Integer.parseInt(st.nextToken()); // 소수 1061개
			bfs(a, b, isPrime);
		}
	}

	private static void bfs(int a, int b, boolean[] isPrime) {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean visited[] = new boolean[10000];
		visited[a] = true;
		q.offer(new int[] { a, 0 });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[0] == b) {
				System.out.println(cur[1]);
				return;
			}

//			for(int i =1000; i< 10000; i++) {
//				if(i != cur[0] && isPrime[i] && !visited[i]) {
//					if(Switchable(cur, i)) { //한자리만 다르다면 q에 넣음
//						q.offer(new int[] {i, cur[1] +1 });
//						visited[i] = true;
//					}
//				}
//			}
			// 최적화: 소수배열을 돌면서 바꿀수 있는걸 넣는것보다
			// cur에서 한자리씩 바꾼거를 넣을 수 있는지 확인하면 40번만 확인하면 된다
			int one = cur[0] / 1000;
			int two = cur[0] / 100 % 10;
			int three = cur[0] / 10 % 10;
			int four = cur[0] % 10;

			for (int i = 1; i <= 9; i++) {
				int x = i * 1000 + two * 100 + three * 10 + four;
				enqueue(q, visited, isPrime, x, cur);
			}
			for (int i = 0; i <= 9; i++) {
				int x = one * 1000 + i * 100 + three * 10 + four;
				enqueue(q, visited, isPrime, x, cur);
			}
			for (int i = 0; i <= 9; i++) {
				int x = one * 1000 + two * 100 + i * 10 + four;
				enqueue(q, visited, isPrime, x, cur);
			}
			for (int i = 0; i <= 9; i++) {
				int x = one * 1000 + two * 100 + three * 10 + i;
				enqueue(q, visited, isPrime, x, cur);
			}
		}
		System.out.println("Impossible");
	}

	private static void enqueue(Queue<int[]> q, boolean[] visited, boolean[] isPrime, int x, int[] cur) {
		if (isPrime[x] && !visited[x]) {
			q.offer(new int[] { x, cur[1] + 1 });
			visited[x] = true;
		}
	}

//	private static boolean Switchable(int[] cur, int i) {
//		int cnt = 0;
//		String a = String.valueOf(cur[0]);
//		String b = String.valueOf(i);
//
//		for (int j = 0; j < 4; j++) {
//			if (a.charAt(j) != b.charAt(j)) {
//				cnt++;
//				if (cnt > 1)
//					return false;
//			}
//		}
//		return true;
//	}
}
