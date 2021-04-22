import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 키 순서 (라이브)
// 정점을 차례대로 탐색해서 순서가 확실한지 봐야하므로 정점 중심
// 유향이고 양방향으로 주어질 순 없으므로  간선수 n(n-1)/2
// 500 * 500 * int =1MB 밖에 안되므로 인접리스트로 해봤자 이득없음

// 1. 각 학생마다 자신보다 큰 학생 ,작은 학생 모두 탐색
// 2. 탐색결과의 합이 n-1이며 확실한 위치를 알 수 있는 경우

public class _2458_키순서 {
	static int N, M;
	static int[][] adjMatrix;
	static int[] gtCnt, smCnt; // 각정점마다 큰 학생, 작은학생

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjMatrix = new int[N + 1][N + 1];
		gtCnt = new int[N + 1];
		smCnt = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjMatrix[from][to] = 1;
		}
		int ans = 0;
		for (int i = 1; i <= N; i++)
			DFS(i, i, new boolean[N + 1]); // 정점 다 돌아보야 cnt 두개가 완성됨
		for (int i = 1; i <= N; i++) {
			if (gtCnt[i] + smCnt[i] == N - 1)
				++ans;
		}
		System.out.println(ans);
	}

	// 최적화
	// 큰거를 세면서 작은거에다가 방문할때마다 자기보다 작은게 몇번방문했는지 표시
	// 이렇게 하면 어디서부터 시작할지가 상관없음
	private static void DFS(int cur, int start, boolean[] visited) {
		visited[cur] = true;
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && adjMatrix[cur][i] == 1) {
				DFS(i, start, visited);
				gtCnt[start]++; // 시작한 거는 자기보다 큰거를 세고
				smCnt[i]++; // 정점을 만날 떄마다 그 정점은 자기보다 작은 거를 찾은 거니까
			}
		}
	}
}
