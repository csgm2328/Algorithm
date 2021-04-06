import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 가방이 K개 있는 냅색
// 근데 가방 최대무게가 1억이다
// 열: 가방의 0~최대무게까지
// 행: 고려할 물건 (최대 30만개(가방개수도))
// 보석은 가방에 하나만 넣을 수 있고 보석도 종류당 하나다
// 그럼 일단 열이 필요가 없다
// 그리디문제임
// 가치 순으로 정렬한 다음
// 뺴면선 가장 무게에 딱 맞는 가방에 집어넣음
public class _1202_보석도둑 {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, K;

	static class jewelry {
		int weight;
		int value;

		public jewelry(int weight, int value) {
			super();
			this.weight = weight;
			this.value = value;
		}
	}

	static class newComparator implements Comparator<jewelry> {

		@Override
		public int compare(jewelry o1, jewelry o2) {
			if (o1.weight == o2.weight)
				return o2.value - o1.value;
			return o1.weight - o2.weight;
		}
	}

	public static void main(String[] args) throws IOException {
		// global init
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 보석개수: 1~ 30만
		K = Integer.parseInt(st.nextToken()); // 가방개수: 1~ 30만

		jewelry[] jewelries = new jewelry[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			jewelries[i] = new jewelry(weight, value);
		}
		int[] knapsacks = new int[K];
		for (int i = 0; i < K; i++) {
			int limit = Integer.parseInt(br.readLine()); // 가방무게: 1~ 1억
			knapsacks[i] = limit;
		}
		Arrays.sort(knapsacks); // 가방 오름차순
		Arrays.sort(jewelries, new newComparator());
		// 다른 아이디어
		// 가방을 보면서 무게 내림차순으로 정렬된
		// 가방 무게보다 작은 보석들을 다 pq에 넣고 거기서는 가치로 오름차순 정렬한다

		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // 보석 가치 내림차순
		long sum = 0;
		for (int i = 0, j = 0; i < K; i++) {
			while (j < N && knapsacks[i] >= jewelries[j].weight) // 넣을 수있는 것들은 다 넣어놓고 가치순으로 정렬
				pq.offer(jewelries[j++].value); // 또 넣어지진 않음
			if (!pq.isEmpty()) // 가방개수만큼 더하게 됨
				sum += pq.poll();
		}

		// 가치 큰거 꺼내서 가장 딱 맞는 거에 넣음
		// 근데 무게도 정렬된 상태는 아니라서 다음꺼가 더작은 무게라면 딱맞는게 쓸데없이 버려졌을 수도 있다
		// 복원 큐도 준비한단
		// 에서 시간초과 예상됨
//		Queue<Integer> restoreQ = new LinkedList<>();
//		long sum = 0;
//		while (!jewerly.isEmpty()) {
//			int[] x = jewerly.poll();
//			
//			while (!knapsacks.isEmpty()) {
//				int limit = knapsacks.poll();
//				if (limit >= x[0]) { // 보석무게가 가방무게 이하라면
//					sum += x[1];
//					while (!restoreQ.isEmpty()) { // 안쓴 가방 복원
//						knapsacks.offer(restoreQ.poll());
//					}
//					break;
//				} else
//					restoreQ.offer(limit); // 안쓴 가방 복원 준비
//			}
//			if(knapsacks.size() ==0)
//				break;
//		}

		System.out.println(sum);
	}
}
