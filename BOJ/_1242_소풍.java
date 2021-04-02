import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//소풍 == 요세푸스 해당 자리가 지워지는 순서
//기본적인 탐색은 O(NK) 시간초과

public class _1242_소풍 {
	static BufferedReader br;
	static StringTokenizer st;
	static int N,K,M;

	public static void main(String[] args) throws IOException {
		// global init
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사람 수 <= 5백만
		K = Integer.parseInt(st.nextToken()); // 몇번째 제거
		M = Integer.parseInt(st.nextToken()); //동호가 처음에 앉은 곳
		 
		//상대 위치 방법
		int cnt = 0;
		int remove = K; //지울 위치
		if(K%N != 0) //K가 N보다 클 경우
			remove = K%N;
		else //K가 0이랑 같을 경우
			remove = N;
		while(true) {
			cnt++;
			if(remove== M)
				break;
			if(M > remove)
				M -= remove; //시작위치에서 동호의 상대적인 위치
			else {
				M -= remove;
				M += N;
			}
			N--;
			if(K%N !=0 )
				remove = K%N;
			else 
				remove = N;
		}
		
		//절대 위치 방법
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
		System.out.println(cnt);
	}
}
