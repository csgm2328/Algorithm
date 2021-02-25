import java.io.*;
import java.util.*;

//연속된 k일 이동합의 최대값
//12:30~
public class _2559_수열_이동합 {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		int moving_sum = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (i < K)
				moving_sum += arr[i]; // 처음 이동합 먼저 구하기
		}
		int max = moving_sum;
		for (int i = K; i < N; i++) {	//이동문제 포인트 !!!! N * K 복잡도를 N-K로 줄이기 
			moving_sum = moving_sum - arr[i - K] + arr[i]; //합 - 처음거  + 새로운거
			if (max < moving_sum)
				max = moving_sum;
		}

		System.out.print(max);
	}
}
