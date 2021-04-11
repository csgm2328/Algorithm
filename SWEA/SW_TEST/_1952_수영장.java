package SW_TEST;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//dp는 간단
//완전탐색으로도 가능
//
public class _1952_수영장 {
	static int day, m, m_3, year;
	static int[] arr = new int[13]; //수영장 이용계획

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			m_3= Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
					arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int dp[] = new int[13];
			dp[0] = 0;
			//현재월까지 가장 싸게 다닐 수 있는 방법이 저장된다.
			for(int i = 1; i<=12;i++) {
				dp[i] = dp[i-1]+Math.min(day*arr[i], m); //하루치 * 당월 이용일 or 한달이용권
				if(i>=3) { 
					dp[i] = Math.min(dp[i], dp[i-3] + m_3); //3월이후부터 일,월결과에 3개월치도비교
				}
			}
			//연권이랑 비교
			dp[12] = dp[12] < year ? dp[12] : year;
			sb.append("#" + tc + " " + dp[12] + "\n");
		} // end tc
		System.out.println(sb.toString());
	}
}
