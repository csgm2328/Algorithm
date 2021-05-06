import java.io.*;
import java.util.*;

//숨바꼭질 3
//순간이동시 시간 안걸림
public class _13549_숨바꼭질_3 {
	static int N, K;
	static boolean[] visited;
	static int MAX = -1;

	static class subin {
		int loc, cnt;

		public subin(int loc, int cnt) {
			super();
			this.cnt = cnt;
			this.loc = loc;
		}

	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		Queue<subin> q = new LinkedList<subin>();
		q.offer(new subin(N, 0));
		visited = new boolean[100001];
		while (!q.isEmpty()) {
			subin cur = q.poll();
			if (cur.loc == K) { // 이번에는 visit되더라도 시간이 지났는지 안지났는지 모르므로 위치가 K일때 끝내야함
				System.out.println(cur.cnt);
				break;
			}
			// 세가지 이동중에 순간이동이 시간이 안걸리므로 최단시간을 구하려면 젤 빨리해야함
			if (cur.loc * 2 <= 100000 && !visited[cur.loc * 2]) {
				q.offer(new subin(cur.loc * 2, cur.cnt));
				visited[cur.loc * 2] = true;
			}
			if (0 <= cur.loc - 1 && !visited[cur.loc - 1]) {
				q.offer(new subin(cur.loc - 1, cur.cnt + 1));
				visited[cur.loc - 1] = true;
				
			}
			if (cur.loc + 1 <= 100000 && !visited[cur.loc + 1]) {
				q.offer(new subin(cur.loc + 1, cur.cnt + 1));
				visited[cur.loc + 1] = true;
			}
			
		}
	}

}
