import java.io.*;
import java.util.*;

// 그리디 방법은 2h
// 완탐은 10m
//main udpate1
public class _19941_햄버거분배 {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
//	static int[] dr = { -1, 1, 0, 0 };
//	static int[] dc = { 0, 0, -1, 1 };

	static int sum;
	static char[] arr;
	static int[] save;
	static boolean flag = true;
//	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new char[N];
		String s[] = br.readLine().split("");
		for (int i = 0; i < N; i++) {
			arr[i] = s[i].charAt(0);
		}
		out:for (int i = 0; i < N; i++) {
			if (arr[i] == 'P') {
				for (int j = K; j >= 1; j--) {
					if (0 <= i - j) {
						if (arr[i - j] == 'H') {
							arr[i - j] = 'X';
							sum++;
							continue out;
						}
					}
				}
				for (int j = 1; j <= K; j++) {
					if (i + j < N) {
						if (arr[i + j] == 'H') {
							arr[i + j] = 'X';
							sum++;
							continue out;
						}
					}
				}
			}
		}
		System.out.print(sum);

	}

	// 중복 순열
	static void permutation(int pre, int cnt, int dupl, int score) {
		if (dupl >= 3) {
			return;
		}
		if (cnt > 5 && score < cnt - 5)
			return;

		if (cnt == N) {
			if (score >= 5)
				sum++;
			return;
		}

		for (int i = 1; i <= 5; i++) {
			if (pre == i) { // 전꺼랑 똑같은거 고르면
				if (arr[cnt] == i)// 정답이랑 찍은거랑 맞으면
					permutation(i, cnt + 1, dupl + 1, score + 1);
				else
					permutation(i, cnt + 1, dupl + 1, score);
			} else {
				if (arr[cnt] == i)
					permutation(i, cnt + 1, 1, score + 1);
				else
					permutation(i, cnt + 1, 1, score);
			}

		}
	}

	static void perm(int cnt) {
		if (cnt == N) {
			int score = 0;
			int dupl = 1;
			int cur = save[0];
			if (cur == arr[0])
				score++;

			for (int i = 1; i < N; i++) {
				if (cur == save[i]) {
					dupl++;
					if (dupl >= 3)
						return;
				} else {
					cur = save[i];
					dupl = 1;
				}
				if (arr[i] == save[i])
					score++;
			}
			if (score >= 5)
				sum++;
			return;
		}
		for (int i = 1; i <= 5; i++) {
			save[cnt] = i;
			perm(cnt + 1);
		}
	}
}
