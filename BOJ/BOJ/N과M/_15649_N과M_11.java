package BOJ.N과M;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//중복 순열의 결과에서 중복된 수열 삭제
//check할 필요없음
public class _15649_N과M_11 {
	static int N, M;
	static ArrayList<Integer> arr;
	static int[] save;
	static StringBuilder sb = new StringBuilder();

	public static void permutation(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < cnt; ++i)
				sb.append(save[i]).append(' ');
			sb.append('\n');
			return;
		}
		
		int before = 0; // 전에 껄 가지고 있음 중복 수열 체크
		for (int i = 0; i < arr.size(); ++i) {
			if (i == 0 || before != arr.get(i)) {
				before = arr.get(i);
				save[cnt] = arr.get(i);
				permutation(cnt + 1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bw.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new ArrayList<Integer>();
		save = new int[M];

		st = new StringTokenizer(bw.readLine());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken());
			arr.add(x);
		}
		// 정렬한 후 표시
		Collections.sort(arr);

		permutation(0);
		System.out.print(sb.toString());
	}
}
