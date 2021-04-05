import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//소풍 == 요세푸스 해당 자리가 지워지는 순서
//기본적인 탐색은 O(NK) 시간초과

public class _1242_소풍 {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, K, M;

	public static void main(String[] args) throws IOException {
		// global init
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사람 수 <= 5백만
		K = Integer.parseInt(st.nextToken()); // 몇번째 제거
		M = Integer.parseInt(st.nextToken()); // 동호가 처음에 앉은 곳

		// 최적화
		// 실제로 큐로 동작되는 걸 생각해야함 (k가 아닌 remove로 연산)
		System.out.println(josephus(N, K, M));
		
		// 절대 위치 방법 - 실패
//		int start = 1;
//		while(N != 1) {
//			System.out.print("시작" + start);
//			int remove = start + (K-1); //지울 위치 셋팅
//			if( remove > N)
//				remove %= N;
//			System.out.print("  지움" + remove);
//			System.out.print("  동호위치" + M);
//			if(remove == M) //동호가  변경된 시작 위치(지울 위치)에 있으면
//				break;
//			if(remove < M) //동호보다 전에있는 걸 지우면 
//				M--;
//			start = remove;
//			N--;
//			if(start > N)
//				start %= N;//지우고 나서 시작위치조정
//			cnt++;
//			System.out.println();
//		}
//		cnt++; //break로 나오든 하나남아서 나오든 ++
		
	}

	private static int josephus(int n, int k, int m) {
		int remove = 0;
		int cnt = 0;
		for (int i = 1; i <= n; i++) { // n개 요소 다 나가면 끝 & cnt 용도
			int qsize = n - i + 1; // 남은 요소들의 수
			remove = k % qsize; // 삭제할 위치는 항상 사이즈에서 k번째 꺼다
								// m의 위치가 달라지므로 고정해도 된다
			if (remove == 0)
				remove = qsize;
			if (remove == m) { // 삭제위치 == 목표 위치
				cnt = i; 
				break;
			}
			m -= remove; //타겟위치변경
			if (m < 0)
				m += qsize;
		}
		return cnt;
	}

	private static int josephus3(int n, int k, int m) {
		int index = 0;
		// 11025_요세푸스 3: 맨 마지막으로 나가는 요소 찾기
		// 요소 수만큼 돌면서 위치를 K만큼 돌림
		// 0이 기준이라 따로 if문 필요없음
		// 하지만, 중간 요소수를 안맞춰줘서 맨 마지막 것만 맞음
		System.out.println("지우는 순서: ");
		for (int i = 1; i <= n; i++) { // 마지막 1개 ~ N개
			index = (index + K) % i;
			System.out.print(index + " ");
		}
		return index + 1; // 0부터 시작했으니까
	}
}
