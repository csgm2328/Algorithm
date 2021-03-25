package D3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
//최장 증가 수열 LIS
//N^2 알고리즘
public class _3307_최장증가부분수열 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static int N;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //1~ 1000 수열길이
			int[] arr = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int[][] LIS = new int[N+1][2]; //맨뒤자리, 길이
			
			int max = 0;
			for(int i = 1; i<= N; i++) {
				for(int j = 0; j < i; j++) {
					int last = LIS[j][0];
					int length = LIS[j][1];
					if(last < arr[i]) { //자신의 전 LIS보다 자신이 크면 +1
						if(length+1 > LIS[i][1]) { //갱신하면서 다음꺼 계쏙 보니까 갱신해야할 때만 체크
							LIS[i][0] = arr[i];
							LIS[i][1]=  length +1;
							if(max < LIS[i][1])
								max = LIS[i][1];
						}
					}
				}
			}
//			System.out.println(Arrays.toString(LIS));
			sb.append("#" + tc + " " + max + "\n");
		} // end TC
		System.out.println(sb.toString());
	}
}
