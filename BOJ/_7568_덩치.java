import java.io.*;
import java.util.*;

public class _7568_덩치 {
	static StringBuilder sb = new StringBuilder();
	static int N, cnt; // 1 ~ 15
//	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 }; // 8방탐색
//	static int[] dc = { 0, 0, -1, 1, -1, 1, 1, -1 };
	static int[][] arr;
//	static int Min = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		// check 자신보다 덩치 큰게 몇개인지
		int[] ranks = new int[N];
		int rank = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == j) continue;
				if (arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1])
					rank++;
			}
			ranks[i] = rank;
			rank =1;
		}
		for(int x: ranks)
			sb.append(x+" ");
		System.out.print(sb.toString());
	}

}
