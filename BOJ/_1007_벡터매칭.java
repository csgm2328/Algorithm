import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//점들 개수/2 개의 벡터를 만들어서
//그 벡터들을 +-해서 최솟값을 만든다
//4개의 점일때 3C1로 중복 없이 +-를 할 수도 있지만
//복잡해서
//4C2로 구한후 가상의 삼각형을 두고
//삼각형 x축 += 선택 x축값, y축+= 선택  y축값
//선택안된거는 빼서
//마지막으로 삼각형의 빗변만 구하고 min이랑 비교하면 된다

public class _1007_벡터매칭 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int N,posX[],posY[];
	static double MIN;
	static double[][] adjMatrix;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		// global init
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine()); // 1 ~ 20 좌표개수
			posX = new int[N];
			posY = new int[N];
			visited = new boolean[N];
			MIN = Double.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				posX[i] = Integer.parseInt(st.nextToken());
				posY[i] = Integer.parseInt(st.nextToken());
			}
			combination(0,0);
			sb.append(MIN + "\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void combination(int idx,int start) {
		if(idx == N/2) {
			double sumX=0,sumY=0;
			for(int i = 0; i< N;i++) {
				if(visited[i]) {
					sumX += posX[i];
					sumY += posY[i];
				}
				else {
					sumX -= posX[i];
					sumY -= posY[i];
				}
			}
			double dis = Math.sqrt(sumX*sumX + sumY*sumY);
			if(MIN > dis)
				MIN = dis;
			return;
		}
		//N/2만큼 점을 골라서 +,-의 min을 구한다
		for(int i = start; i< N; i++) {
			visited[i] = true;
			combination(idx+1, i+1);
			visited[i] = false;
		}
	}

}
