import java.io.*;
import java.util.*;

// 숨바꼭질 2
// 경우의 수가 몇 갠지도 세기
// 다음 위치로 같은 횟수로 갈 수 있는 경우를 살려야한다
// 그러기 위해선 해당위치로 몇번만에 왔는지 기록해야함

public class _12851_숨바꼭질_2 {
	static int N, K;
	static int[] visited; // 동생위치에 방문한 횟수 저장

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		if (N == K) {
			System.out.print(0 + "\n" + 1);
			System.exit(0);
		} else if (N > K) { // 거꾸로가는경우는 한가지
			System.out.print(N - K + "\n" + 1);
			System.exit(0);
		}
		// bfs
		System.out.println(bfs() + "\n" + visited[K]);
	}

	private static int bfs() {
		visited = new int[100001];
		int[] arrivedTime = new int[100001]; // 해당위치에 도착한 시간을 기록할 배열
		Arrays.fill(arrivedTime, Integer.MAX_VALUE);
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(N);
		visited[N] = 1;
		arrivedTime[N] = 0; // 시작

		while (!q.isEmpty()) {
			int cur = q.poll();
			
			int[] next = new int[3];
			next[0] = cur + 1;
			next[1] = cur - 1;
			next[2] = cur * 2;
			for (int move = 0; move < 3; move++) {
				if (next[move] < 0 || next[move] > 100000 || arrivedTime[cur] + 1 > arrivedTime[K])
					continue;
				if (visited[next[move]] == 0) {
					visited[next[move]] = 1;
					arrivedTime[next[move]] = arrivedTime[cur] + 1; //도착시간 +1
					q.offer(next[move]);
				}
				// 방문은 되어있는데 1+1, 1*2같은 다른 경로라면
				else if (arrivedTime[next[move]] >= arrivedTime[cur] + 1) {
					visited[next[move]]++; // 경우의 수 추가
					q.offer(next[move]);
				}
			}
		} // end while
		return arrivedTime[K];
	}
}
