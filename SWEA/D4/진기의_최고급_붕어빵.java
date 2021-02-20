package D4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 진기의_최고급_붕어빵 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer x = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(x.nextToken());
			int M = Integer.parseInt(x.nextToken());
			int K = Integer.parseInt(x.nextToken());

//			int onetime;
//			if (M > K)
//				onetime = N * (M / K); // 사람수만큼 만드는 데 걸리는 시간 나눗셈 주의!
//			else
//				onetime = N * (K / M);	//이렇게 하면 소수점 손실 때문에 틀린다
//
//			int[] bbangs = new int[onetime + 1];
//			for (int i = 1; i < bbangs.length; i++) {
//				if (i % M == 0) // 빵 만드는데 걸리는 시간마다
//					bbangs[i] = K; // K개 만듬
//			}
			
			
			x = new StringTokenizer(br.readLine());
			int[] people = new int[N];
			for (int i = 0; i < N; i++) {
				people[i] = Integer.parseInt(x.nextToken());
			}
			Arrays.sort(people); // 사람이 오는시간 순서대로아님
			
		
			
			
//			for (int i = 0; i < N; i++) {
//				flag = false; // 초기화 필수
//				if (bbangs.length - 1 < people[i]) { // 모든 빵이 준비되는 시간보다 나중에 온다면 바로 줌
//					flag = true;
//				} 
//				else {
//					for (int j = 1; j <= people[i]; j++) { // 0초에 오면 바로 fail
//						if (bbangs[j] != 0) {// 방문한 시간에 만들어 놓은게 남아있다면
//							bbangs[j]--; // 하나주고
//							flag = true;
//							break; // 나옴
//						}
//					}
//				}
//				// break 안걸렸으면
//				if (!flag)
//					break;
//			}
			
			
			//빵을 배열로 하지말자 배열 사이즈 정하는 데 문제가 생김
			boolean flag = true;
			int bbang = 0;
			int idx = 0;
			// i는 시간의 흐름
			for(int i =0 ; i<= people[people.length-1]; i++) {			// 가장 늦게 오는 사람의 시간까지 
				flag = true;
				if( i != 0 && i % M == 0) { //빵만드는 시간마다 빵추가
					bbang += K;
				}
				if(i == people[idx]) {	//손님 도착하는 시간
					bbang --;
					idx++;
					if(bbang < 0) {
						flag = false;
						break;
					}
				}
				
				//수식으로 더 간단하게
//				bbang = people[i] / M * K - i; // 사람 온 시간 / 빵만든 횟수 * 한번에 만드는 빵 - 지금까지 온사람
//				if(bbang <= 0) {
//					flag = false;
//					break;
//				}
			}
			
			System.out.println("#" + tc + (flag ? " Possible" : " Impossible"));
		}

	}
}
