package BOJ.순열과조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

//Hash알고리즘을 사용하여 검색속도가 아주 빠른 HashMap 인스턴스를 사용하는 HashSet
//하지만 저장순서를 고려하지않으므로
//LinkedHashSet을 사용한다
//before과 비교하면서 호출을 줄였을때보다 200 + 280ms
public class _15649_N과M_9_SET {
	static StringTokenizer st;
	static int N, M;
	static int[] input;
	static boolean[] visited;
	static int[] number;
	static LinkedHashSet<String> set = new LinkedHashSet<String>(); // 중복 체크하기위해 결과값을 먼저 저장할 곳
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new int[N];
		number = new int[M];
		visited = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(input);

		permutation(0,"");
		// set에서 sb로
		for (String s : set) {
			sb.append(s+ "\n");
		}
		System.out.println(sb);
	}

	static void permutation(int cnt,String s) {
		if (cnt == M) {
			set.add(s);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			number[cnt] = input[i];
			visited[i] = true;
			permutation(cnt + 1, s+input[i]+" ");
			visited[i] = false;
		}
	}
}
