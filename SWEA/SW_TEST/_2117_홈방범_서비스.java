package SW_TEST;
//운영비용은 서비스 크기에 비례해서 증가

//마름모면적 세는 식 기억해놓자 = 마름모를 꽉채운 사각형으로 조작해서 센 계산
//집들의 배치를 보고 손해를 안보는 서비스 지역을 설정하자
//마름모를 다 그려보지 말고
//i,j를 기준으로 주변 집을 마름모로 포함하려면
//거리를 재본다
//그러고 포함할수 있는 마름모의 크기인 K를 배열로 만들고
//누적합을 만들어서 마름모K 이내의 집수를 기록
//그러고 비용보다 작은것중에 가장 큰거 찾는다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _2117_홈방범_서비스 {
	static int[][] arr;
	static int T, N, M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken()); // 한 집당 지불금
			arr = new int[N][N];
			List<int[]> homes = new ArrayList<int[]>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 1) {
						homes.add(new int[] { i, j });
					}
				}
			}
			// 먼저 전체 집을 서비스할 수 인ㅆ는지 체크
			// 주어진 맵에서 전체 집을 서비스하려면 맵크기 +1의 마름모가 필요함
			int rhombus_size = 0; // 마름모 크기
			if (N % 2 == 0)
				rhombus_size = N + 1;
			else
				rhombus_size = N;

			if (calc_cost(rhombus_size) <= homes.size() * M) { // 모든 집 포함 가능하면
				sb.append("#" + tc + " " + homes.size() + "\n");
				continue;
			}
			rhombus_size--; // 마름모 사이즈 줄여서 이제 맵 돌면서 검사
			while (rhombus_size > 1) {
				int max = -1;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						int cnt = 0; // 포함되는 집의 개수
						// 맵 돌면서 현재위치에서 마름모 크기에 포함되는 집 찾기
						for (int k = 0; k < homes.size(); k++) {
							int x[] = homes.get(k);
							int dis = Math.abs(i - x[0]) + Math.abs(j - x[1]);
							// 왜 여기서 계산이 틀리지? 특정경우에만 틀릴수가있나?
							// (1,6)에서 2 크기인데 2 거리에 있는게 들어와서 2가찍힘
							if (dis < rhombus_size) { // 마름모 중심부에서 집까지 거리가 마름모 크기보다 1 작아야함
								cnt++;
							}
						}
						if (calc_cost(rhombus_size) <= cnt * M) {
							// 서비스가 가능해도 맥스랑 비교해야한다!!! 첫번째 실수
							// 테케가 한개맞는다고?
							if (max < cnt) {
								max = cnt;
							}
						}
					}
				}
				if (max != -1) {
					sb.append("#" + tc + " " + max + "\n");
					break;
				}
				rhombus_size--;
			}
			if(rhombus_size == 1) //반례 디버깅 + 1시간
					sb.append("#" + tc + " " + 1 + "\n"); //반례 마름모크기 1로줄어들면
		} // end tc
		System.out.println(sb.toString());
	}

	static int calc_cost(int size) {
		return size * size + (size - 1) * (size - 1);
	}
}
