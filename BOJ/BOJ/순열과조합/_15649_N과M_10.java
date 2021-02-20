package BOJ.순열과조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//조합의 결과에서 중복된 수열 삭제
public class _15649_N과M_10 {
	static int N, M;
	static int[] arr = new int[10001];
	static ArrayList<Integer> input, dupl;
	static int[] save, check;
	static StringBuilder sb = new StringBuilder();

	public static void permutation(int cnt,int start) {
		if (cnt == M) {
			for (int i = 0; i < cnt; ++i)
				sb.append(save[i]).append(' ');
			sb.append('\n');

			return;
		}
		int before = 0; // 전에 껄 가지고 있음 중복 체크
		for (int i = start; i < input.size(); ++i) {
			if (check[i] == 1)
				continue;
			else if (check[i] == 0 && (i == 0 || before != input.get(i))) {
				before = input.get(i);
				check[i] = 1;
				save[cnt] = input.get(i);
				permutation(cnt + 1,i+1);
				check[i] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bw.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new ArrayList<Integer>();
		dupl = new ArrayList<Integer>();
		check = new int[N];
		save = new int[M];

		st = new StringTokenizer(bw.readLine());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken());
			input.add(x);
		}
		// 정렬한 후 표시
		Collections.sort(input);

		permutation(0,0);
		System.out.print(sb.toString());
	}
}
