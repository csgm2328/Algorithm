import java.io.*;
import java.util.*;

public class _19941_햄버거분배 {
	static int N, K;
	static int sum;
	static char[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new char[N];
		arr = br.readLine().toCharArray(); // 160 - 80ms
//		out:for (int i = 0; i < N; i++) {
//			if (arr[i] == 'P') {
//				for (int j = K; j >= 1; j--) {
//					if (0 <= i - j) {
//						if (arr[i - j] == 'H') {
//							arr[i - j] = 'X';
//							sum++;
//							continue out;
//						}
//					}
//				}
//				for (int j = 1; j <= K; j++) {
//					if (i + j < N) {
//						if (arr[i + j] == 'H') {
//							arr[i + j] = 'X';
//							sum++;
//							continue out;
//						}
//					}
//				}
//			}
//		}
		// 최적화
		// 왼쪽부터 차례대로 보기 for문 하나로
		for (int i = 0; i < N; i++) {
			if (arr[i] == 'P') {
				for (int j = i - K; j <= i + K; j++) {
					if (0 <= j && j < N && j != i) {
						if (arr[j] == 'H') {
							arr[j] = '.';
							sum++;
							break;
						}
					}
				}
			}
		}
		System.out.print(sum);
	}
}