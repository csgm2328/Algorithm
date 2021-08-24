import java.io.*;
import java.util.*;

public class _1715_카드정렬하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			pq.add(x);
		}
		long result = 0L;
		if (N == 1) {
			result = 0;
		} else {
			while (pq.size() != 1) {
				int a = pq.poll();
				int b = pq.poll();
				result += a + b;
				pq.add(a + b);
			}
		}
		System.out.println(result);
	}
}
