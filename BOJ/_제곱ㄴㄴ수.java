import java.io.*;
import java.util.*;

//제곱 ㄴㄴ 수
//512mb 니까 
public class _제곱ㄴㄴ수 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static long min, max; //1조 + 100만
	static long sum;
	static boolean[] square_che;
	static int[] save;
	static boolean flag = true;
//	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		min = Integer.parseInt(st.nextToken());
		max = Integer.parseInt(st.nextToken());
		//arr이 1조개면 1조bit인데 256기가???
		//어차피 배열 인덱싱은 int가 한계네
		
		//소수의 제곱수로 나뉘는지 판별하는 체
		square_che[0] = false;
		square_che[1] = false;//2부터 소수
		
		for(int i = 2; i < max; i++) {
			if(square_che[i]) {
				int sqaure = i*i;
				for(int j = sqaure; j < max; i+=sqaure) {
					square_che[j] = false;
				}
		}
		
		
		System.out.print(sb.toString());
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
