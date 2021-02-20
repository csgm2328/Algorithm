import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _2581_소수_에라토스테네스의_체 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//bw 안사용하면 156ms --> 124ms로 단축

		int min = 10001; // 10,000이하까지
		int M, N;

		boolean isSosu[] = new boolean[10001];
		Arrays.fill(isSosu, true); // 초기화 속도 부하 없음
		
		isSosu[0] = false;
		isSosu[1] = false;
		
		for (int i = 2; i <= 10000; i++) {
			// i 가 소수라면 소수의 배수 다 false로;
			// 근데 이걸 소수 체크하는 게 아니라
			// i의 배수는 다 소수 아니라고 바꾸면 됨
			if (isSosu[i])
				for (int j = i + i; j <= 10000; j = j + i)
					isSosu[j] = false;
		}
		// 입력 받아서
		M = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());

		// M ~ N 사이의 소수 계산
		int sum = 0;
		for (int i = M; i <= N; i++) {
			if (isSosu[i]) {
				if (min == 10001)
					min = i;
				sum += i;
			}
		}
		
		
		if (sum == 0)
			System.out.println(-1);
			//	bw.write("" + -1); 
		
		else {
			System.out.println(sum);
			System.out.println(min);
		}
			// bw.write("" + sum + "\n" + min + "개수:" + cnt);
			//bw.write("" + sum + "\n" + min);
		//bw.flush();
		//bw.close();
		br.close();
	}
}
