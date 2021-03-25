package D6;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 플로이드 워셜
public class _1263_사람네트워크2 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static int N, adjMatrix[][];
	static final int INF = 9999999;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine()); // 2차원이 한행으로 들어옴
			N = Integer.parseInt(st.nextToken()); // 노드수 0? ~ 1000
			adjMatrix = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adjMatrix[i][j] = Integer.parseInt(st.nextToken());
					if(adjMatrix[i][j] == 0)
						adjMatrix[i][j] = INF;
				}
			}
			//1. 경유하는 바꿔가면서(사실 전꺼에 더하므로 늘려가는 거) 모든 정점에서 각 정점으로의 최소비용 찾기
			for(int path = 1; path<= N; path++) {
				for(int start = 1; start<=N; start++) {
					if(path == start) continue;
					for(int des = 1; des<= N; des++) {
						//1 --> 2 --> 2나 1 --> 2 --> 1 생략
						if(start == des || path == des) continue;
						if(adjMatrix[start][des] > adjMatrix[start][path] + adjMatrix[path][des])
							adjMatrix[start][des] = adjMatrix[start][path] + adjMatrix[path][des];
					}
				}
			}
			
			int min = Integer.MAX_VALUE;
			for(int i=1; i<=N; ++i) {
				int sum = 0;
				for(int j=1; j<=N; ++j) {
					if(adjMatrix[i][j] == INF) continue;
					sum += adjMatrix[i][j];
				}
				if(min > sum)
					min = sum;
			}

			sb.append("#" + tc + " " + min + "\n");
		} // end TC
		System.out.println(sb.toString());
	}
}
