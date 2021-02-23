import java.io.*;
import java.util.*;

public class _10819_차이를최대로 {
	static StringBuilder sb = new StringBuilder();
	static int N;
//	static int[] dr = { -1, 1, 0, 0 };
//	static int[] dc = { 0, 0, -1, 1 };

	static int Max = -1;
	static int[] arr;
	static int[] save;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		visited = new boolean[N];
		save = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		permutation(0);
		System.out.print(Max);
	}

	// 0 -1 + 1-2 + 2-3
	static void permutation(int cnt) {
		if (cnt == N) {
			int sum = 0;
			for (int i = 0; i < N - 1; i++) {
				sum += Math.abs(save[i] - save[i + 1]);
//				if (sum < Max)
//					return;
			}
			if (Max < sum)
				Max = sum;
			return;

		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			save[cnt] = arr[i];
			permutation(cnt + 1);
			visited[i] = false;
		}
	}
}
