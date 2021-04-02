import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _11722_가장긴_감소하는_부분수열 {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;


	public static void main(String[] args) throws IOException {
		// global init
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		int[] arr= new int[N];
		for(int i =0 ; i< N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[N]; //최대길이 저장
		int max =0;
		for(int i = 0; i< N; i++) { //i번째 수를 시작으로 만들 수 있는 최대길이를 한바퀴돌아서 저장
			dp[i] =1; //기본 길이 1
			for(int j = 0; j < i; j++) { //앞에가 갱신된 상태이므로 j가 i의 이전 범위여야함
				if(arr[j] > arr[i] && dp[i] == dp[j]) { // 그러면 이전꺼(j) > 다음꺼(i) & 이전 연속길이랑 같으면 
					dp[i] = dp[j] +1; //하나 늘림
				}
			}
			if(max < dp[i])
				max = dp[i];
		}
		System.out.println(max);
	}

}
