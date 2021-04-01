import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ의 코니와 브라운
public class _17071_숨바꼭질_5 {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, K;
	static boolean[][] visited = new boolean[500001][2]; // 0,1 홀짝으로저장

	static class posTime {
		int pos;
		int time;

		public posTime(int pos, int time) {
			super();
			this.pos = pos;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		// global init
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수빈 위치 0 ~ 50만
		K = Integer.parseInt(st.nextToken()); // 동생 위치 0 ~ 50만

		// 수빈이 가능한 위치를 홀짝 시간으로 먼저 표시해놓고 동생을 움직인다
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<posTime> q = new LinkedList<posTime>();
		q.offer(new posTime(N, 0));
		visited[N][0] = true;
		int time = 0;
		while (true) {
			K += time;
			if (K > 500000)
				return -1; // 동생이 범위 나가면 못잡음
			if (visited[K][time % 2])
				break; // 동생이 수빈이가 방문한자리에 오면 잡은거
			//BFS를 도는데 큐에들어있는게 같은 시간에 들어온 거라 사이즈별로 포문 돈다
			int len = q.size();
			for (int i = 0; i < len; i++) {
				posTime cur = q.poll();
				// 세 가지 경우
				int nPos = cur.pos + 1;
				if (nPos <= 500000 && !visited[nPos][1 - cur.time]) { // 현재가 짝수면 다음 홀수칸이 미방문인지확인
					q.offer(new posTime(nPos, 1 - cur.time));
					visited[nPos][1 - cur.time] = true;
				}

				nPos = cur.pos - 1;
				if (0 <= nPos && !visited[nPos][1 - cur.time]) {
					q.offer(new posTime(nPos, 1 - cur.time));
					visited[nPos][1 - cur.time] = true;
				}
				nPos = cur.pos * 2;
				if (nPos <= 500000 && !visited[nPos][1 - cur.time]) {
					q.offer(new posTime(nPos, 1 - cur.time));
					visited[nPos][1 - cur.time] = true;
				}
			}
			time++;
		}
		return time;
	}
}
