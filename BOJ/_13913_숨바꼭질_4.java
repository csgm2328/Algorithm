import java.io.*;
import java.util.*;

// 숨바꼭질 4
// 동생이 움직이지 않는다
// 경로를 다저장하면 시간초과임
// 부모위치만 저장한다
public class _13913_숨바꼭질_4 {
	static int N, K;
	static boolean[] visited;
	static int[] parents;
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
		parents = new int[100001];
		while (!q.isEmpty()) {
			subin cur = q.poll();
			if (visited[K]) { // 동생찾음을 K가 아니라 visit으로 체크
				System.out.println(cur.cnt+1);
				Stack<Integer> stack = new Stack<>();
				while(N != cur.loc) {
					stack.add(cur.loc);
					cur.loc = parents[cur.loc];
				}
				stack.add(N); //처음꺼 넣고
				StringBuilder sb = new StringBuilder();
				while(!stack.isEmpty())
					sb.append(stack.pop()+" ");
				System.out.println(sb.toString());
				break;
			}
			// 세가지 이동
			if (0 <= cur.loc - 1 && !visited[cur.loc - 1]) {
				q.offer(new subin(cur.loc - 1, cur.cnt + 1));
				visited[cur.loc - 1] = true;
				parents[cur.loc -1] = cur.loc; 
				
			}
			if (cur.loc + 1 <= 100000 && !visited[cur.loc + 1]) {
				q.offer(new subin(cur.loc + 1, cur.cnt + 1));
				visited[cur.loc + 1] = true;
				parents[cur.loc +1] = cur.loc;
				
			}
			if (cur.loc * 2 <= 100000 && !visited[cur.loc * 2]) {
				q.offer(new subin(cur.loc * 2, cur.cnt + 1));
				visited[cur.loc * 2] = true;
				parents[cur.loc * 2] = cur.loc;
			}
		}
	}

}
