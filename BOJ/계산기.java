import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 거스름돈을 나누는 액면가가 바뀔때
// 거스름돈 최소개수를  구할때 그리디와 DP방법 중 최적이 되는 경우는?
public class 계산기 {
	static BufferedReader input;
//	static StringBuilder output;
	static StringTokenizer st;
	static int N;
	static int dr[] = { -1, 0, -1 }; // 대각, 왼, 위
	static int dc[] = { -1, -1, 0 };

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
//		output = new StringBuilder();
		// global init
		N = Integer.parseInt(input.readLine());
		st = new StringTokenizer(input.readLine());
		int[] changes = new int[N];

		for(int i = 0; i < N; i ++)
			changes[i] = Integer.parseInt(st.nextToken());
		
		//그리디 방법
		//가능한 최대 액면금
		int C = 1; //1원부터 ~
		int sum = 0;
		//모든 잔돈에 대하여 그리디가 최적인지 체크해야하는데
		//어디서 끝내야할까?
		
		while(true) { 
			while(sum <= C) {
				
			} //end while
		} //end while 모든 잔돈
		
	}
}
