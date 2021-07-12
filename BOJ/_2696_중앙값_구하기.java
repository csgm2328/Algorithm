import java.io.*;
import java.util.*;

// 홀수 번째마다 중앙값 구하기
// 우선순위 큐는 제일 작은걸 앞으로 낼뿐 요소들이 전부 정렬되는게 아니다
// 우선순위 큐를 이용하면 탐색을 해야하고
// 탐색도 poll()이용해야므로 다시 넣고 뺴는 과정을 매번해야함
// 정렬을 하면 정렬에 오버헤드가 있다

// sol: 자동으로 중간값을 맞춰주기 위한 최대힙과 최소힙을 사용하기

public class _2696_중앙값_구하기 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
			PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder()); // 출력 힙
			sb.append((N / 2 + 1) + "\n");
			int index = 0;
			for (int k = 0; k <= (N - 1) / 10; k++) { // 입력도 10개씩 잘라서 들어옴
				String[] str = br.readLine().split(" ");
				for (int i = 0; i < str.length; i++) {
					// 1. 자동으로 중간값을 맞춰주기 위한 최대힙과 최소힙을 사용하기
					if (minHeap.size() == maxHeap.size())
						maxHeap.offer(Integer.parseInt(str[i]));
					else
						minHeap.offer(Integer.parseInt(str[i]));
					// 최대힙에서 출력하기 위해
					if ((minHeap.size() + maxHeap.size()) % 2 == 1) {
						// 개수를 맞춰서 한쪽은 큰 순서 한쪽은 작은 순서로 하고
						// 작은순으로 되어있는 첫번째가 큰 순서로 되어있는 첫번째보다 작다면 OK
						if (!minHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
							int a = minHeap.poll();
							int b = maxHeap.poll();
							minHeap.offer(b);
							maxHeap.offer(a);
						}
						if (index % 10 == 0 && index != 0)
							sb.append("\n");
						sb.append(maxHeap.peek() + " ");
						index++;
					}
					
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
