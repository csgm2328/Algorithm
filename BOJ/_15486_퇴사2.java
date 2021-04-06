import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _15486_퇴사2 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][]; //최대 150만
		for(int i =1; i<= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int period = Integer.parseInt(st.nextToken());
			int pay = Integer.parseInt(st.nextToken());
			arr[i] = new int[] {period,pay};
		}
		int[] dp = new int[N+2]; //한칸 더
		if(arr[N][0] == 1) //뒤에서보는 처음값 세팅
			dp[N] = arr[N][1];
		else 
			dp[N] = 0;
		
		for(int i = N-1; i>0; i--) { 
			if(i + arr[i][0] <= N+1) { //상담기간이 퇴사기간 안넘어가면
				//dp를 거꾸로 보면서 지금 까지 최대 보수를 저장하므로
				//4일날 이틀걸리는 상담이라면 6일의 최대보수에 4일상담비를 더한값과
				//4일 상담을 포기하고 5일의 최대보수와 비교한다
				dp[i] = Math.max(dp[i + arr[i][0]] + arr[i][1] , dp[i+1]); 
			}
			else {
				//4일날 상담을 못한다면 그 전에 저장한 5일날의 값을 가져옴
				dp[i] = dp[i+1];
			}
		}
		System.out.println(dp[1]);
	}
}
