import java.io.*;
import java.util.*;

// 수 묶기
// 절댓값 정렬해서 양수끼리 곱해서 더하면 최대
// 반례: 음수끼리 곱, 마지막에 0 나오면 남아있는 음수 0처리
// 짝이 되야 곱해지므로 정렬된 거 하나씩 보면서 양수,음수 임시 저장,초기화 반복
public class _1744_수묶기 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(Math.abs(o2), Math.abs(o1));
			}
		});

		for (int i = 0; i < N; i++)
			pq.add(Integer.parseInt(br.readLine()));

		int sum = 0;
		int plus = 0;
		int minus = 0;

		int x = pq.poll();
		if (x > 0)
			plus = x;
		else
			minus = x;

		while (!pq.isEmpty()) {
			int temp = pq.poll();
			if (temp == 1)
				sum += 1; // 반례 1은 무조건 더해야 이득
			else if (temp > 0) {
				if (plus != 0) {
					sum += (temp * plus);
					plus = 0;
				} else
					plus = temp;
			} else if (temp < 0) {
				if (minus != 0) {
					sum += (temp * minus);
					minus = 0;
				} else
					minus = temp;
			} else { // 마지막에 0이 있다면
				if (minus != 0)
					minus = 0;
			}
		}
		sum += plus + minus;
		System.out.println(sum);
	}
}
