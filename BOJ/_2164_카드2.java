
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//마찬가지로 큐로 풀리는 이것도 큐칙성이 있다.
//1 2 3 4 5 6
//홀수 위치가 계속 버려진다
//그 다음으로 2의 제곱수만 남는다
//

// 출력값으로 찾은 규칙
// 1 2
// 2 4
// 2 4 6 8 
// 2 4 6 8 10 12 14 16
//만약에 15이면 15보다 작고 가장 가까운 2의 승수인 8로 나눈 나머지 7에 곱하기 2 하면 답
//가장 가까운 2의 승수를 어떻게 찾지?

//여기서 더 최적화하면
//가깝지만 작은게 아니라 더 큰 수를 찾고
//그 수에서 N의 두배를 빼면 끝이다
// 9*2 - 16 = 2
// 5*2 - 8 = 2

public class _2164_카드2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//간단한 큐 풀이
		Queue<Integer> q = new LinkedList<Integer>(); // Queue 기능만 제한적 사용을 위해 다형성 선언
		int N = Integer.parseInt(br.readLine());
//		int init = st.countTokens();

		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}
//			 q.size() 계산시간 들지 + 20ms
		while (q.size() > 1) {// 끝 뺴고 그 다음거 앞으로
			q.poll();
			int x = q.poll();
			q.offer(x);
		}
		
		System.out.print(q.poll() + " ");
		
		//규칙 풀이
		if (N == 1 || N == 2) { // 예외
			System.out.println(N);
			System.exit(0);
		}
		// 4의 거듭제곱인지 확인
		else if (N % 4 == 0) {
			int test = N;
			boolean flag = true;
			while (flag) {
				if (test % 4 == 0) {
					test /= 4;
					if (test == 1 || test == 2) //예외 8						
						break;
				} else {
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println(N);
				System.exit(0);
			}
		}
		// 다른 경우들
		int powTwo = 2;
		while (powTwo < N) {
			powTwo *= 2;
		}
		powTwo /= 2;
		// 가장 가까운 2의 승수로 나눈
		System.out.println(N % powTwo * 2);
		
		
		br.close();
	}
}
