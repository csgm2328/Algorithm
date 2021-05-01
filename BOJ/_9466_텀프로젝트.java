import java.io.*;
import java.util.*;

// 텀 프로젝트
// 사이클에 포함되지 않는 정점의 수

// dfs의 구조를 유지하면서
// 모든 정점은 사이클을 만나야 종료되므로
// visited 외에 사이클을 만나서 종료했는지를 체크하는 배열을 하나더 만든다
// 이렇게 따로 배열을 만들면 자신-자신의 사이클인지는 따로 체크할 필요가 없다
// 그리고 뎁스를 세면서 들어가서 바로 그만큼을 더해버리면 dfs 경로가 전부다 사이클이라는 뜻이라 안된다
// 
public class _9466_텀프로젝트 {
	static int cycled;
	static int[] arr;
	static boolean[] visited, meeted;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			arr = new int[N + 1];
			visited = new boolean[N + 1];
			meeted = new boolean[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++)
				arr[i] = Integer.parseInt(st.nextToken());

			for (int i = 1; i <= N; i++)
				dfs(i);
			sb.append(N - cycled + "\n");
			cycled = 0;
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int cur) {
		if (visited[cur])
			return;
		visited[cur] = true;
		
		int next = arr[cur];
		if(visited[next]) { //다음꺼가 방문이 되어있다면
			if(!meeted[next]) { //싸이클을 아직 안만났다면
				//싸이클을 이루는 정점의 개수를 센다
				cycled++; //들어오면 일단 1세고
				for(int i = next; i != cur; i=arr[i]) //다음이 현재로 돌아올때 걸리는
					cycled++;
			}
		}
		else
			dfs(next);
		
		meeted[cur] = true;
	}
}
