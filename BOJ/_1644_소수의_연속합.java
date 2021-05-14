import java.io.*;
import java.util.*;

//4백만까지의 소수의 개수는 283146개
public class _1644_소수의_연속합 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		//che
		boolean[] isPrime = new boolean[N+1];
		List<Integer> primes = new ArrayList<Integer>();
//		int[] primes = new int[283146+1];
		
//		Arrays.fill(isPrime, true); //172 - 20ms
//		isPrime[0] = false; //초기화 안하고 시작할때는 true
//		isPrime[1] = false; //true
		
		for(int i = 2; i<= N;i++) {
			if(!isPrime[i]) {
				for(int j = i+i; j <= N; j+=i)
					isPrime[j] = true;
				primes.add(i);
			}
		}
		//투포인터로 작으면 뒤증가 크면 앞증가
		//포인터 끝날때까지 가면서 N이랑같아지는 구간 수 세기
		primes.add(0); //rear가 size를 벗어나도 비교를 하고 끝나도록
		int cnt=0,front=0,rear=0,sum=0;
		while(front < primes.size() && rear < primes.size()) {
			if(sum == N)
				cnt++;
			if(sum > N)
				sum -= primes.get(front++);
			else
				sum += primes.get(rear++);
		}
		System.out.print(cnt);
	}
}
