import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 100m
//에라토스테네스의 체를 내 방식대로 구현
//10000 까지니까 100까지의 소수만 구하면 된다. 

//100까지의 소수는 1~ 10사이의 소수로 나눠떨어지지 않는 수
//10000까지의 소수는 1~100까지의 소수로 나눠떨어지지 않는 수
//1~100까지 사전작업을 하고
//M ~ N 사이를
//100 이하와 초과로 나눠서
//100 이하일때는 저장된 소수를 바로 출력하고 101부터는 저장된 소수로 나눠떨어지는 검사
//만약 N이 100보다 작을 때는 저장된 소수 출력하고 종료


public class _2581_소수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int sum = 0;
		int min = 10001; // 10,000이하까지
		int M, N;
		int[] dp = new int[10000];
		int index = 0;

		M = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		// 1 ~ 100까지 사전작업
		int num = 2;
		while (num <= 100) {
			if (num <= 7) { // 2, 3, 5, 7
				if (num == 2 || num == 3 || num == 5 || num == 7) {
					dp[index++] = num;

				}
			} else {
				if (num % 2 != 0 && num % 3 != 0 && num % 5 != 0 && num % 7 != 0) {
					dp[index++] = num;
				}
			}
			num++;
		}

		// System.out.println("사전작업" + index);

		int cnt = 0;

		while (M <= N) {
			// 구간이 100보다 클때
			if (M > 100) {
				boolean flag = true;
				for (int i = 0; i < index; i++) {
					if (M % dp[i] == 0) {// 소수의 배수이면
						flag = false;
						break;
					}
				}
				if (flag) { // 소수 일떄만
					if (min > M)
						min = M;
					// System.out.println("초과" + M);
					sum += M;
					cnt++;
				}
				M++;
			}

			else { // 100이하면 바로
				for (int i = 0; i < index; i++) {
					if (M <= dp[i] && dp[i] <= N) {
						if (min > dp[i])
							min = dp[i];
						// System.out.println("이하" + dp[i]);
						sum += dp[i];
						cnt++;
					}
				}
				if (N >= 100) // N이 100보다 크면 101부터 시작하도록
					M = 100 + 1;
				else	//100보다 작으면 출력해야함
					M = N + 1;
					//break;
			}
		}

		if (sum == 0)
			bw.write("" + -1);
		else
			// bw.write("" + sum + "\n" + min + "개수:" + cnt);
			bw.write("" + sum + "\n" + min);
		bw.flush();
		bw.close();
		br.close();
	}
}
