import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _11404_플로이드 {
	static final int INF = 9999999;
	static int N,adjMatrix[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		adjMatrix = new int[N+1][N+1];
		for(int[] x: adjMatrix)
			Arrays.fill(x, INF);
		
		for(int i=0; i<M; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			if(adjMatrix[from][to] > w)
				adjMatrix[from][to] = w;
		}
		for(int k=1; k<=N; ++k) { //1번 정점부터 N번정점까지 경유해서 가는 경우를 고려
			for(int i=1; i<=N; ++i) {
				if(i==k) continue; // 출발지와 경유지가 같다면 다음 출발지
				for(int j=1; j<=N; ++j) {
					if(i==j || k==j) continue; // 경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스
					//갈 수없는 경우를 INF로 처리했기때문에 어차피 갱신이 안된다
					if(adjMatrix[i][j] > adjMatrix[i][k]+adjMatrix[k][j]) {
						adjMatrix[i][j] = adjMatrix[i][k]+adjMatrix[k][j];
					}
				}
			}
		}
		for(int i=1; i<=N; ++i) {
			for(int j=1; j<=N; ++j) {
				if(adjMatrix[i][j] == INF)
					sb.append(0 + " ");
				else
					sb.append(adjMatrix[i][j] +" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}
