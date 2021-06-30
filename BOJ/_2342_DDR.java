import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// DDR
// 그리디 생각이 바로나지만
// 다음 지점을 오른발로 가냐 왼발로 가냐에 따라
// dp 최적의 결과를 낼 수 있다

public class _2342_DDR {
	static int N, M;
	static List<Integer> list = new ArrayList<Integer>();
	static int[][][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		while(true) {
			int x = Integer.parseInt(st.nextToken());
			if(x == 0)
				break;
			list.add(x);
		}
		dp = new int[list.size()][5][5]; // [게임길이][0~4로 이루어진 DDR에서 오른발 왼발이 위치]
		
		System.out.println(DDR(0,0,0));
	}
	private static int DDR(int round, int right, int left) {
		if(round == list.size())
			return 0;
		if(dp[round][right][left] != 0)
			return dp[round][right][left];
		//최종라운드부터 채워지기 시작해서 첫라운드까지 오게된다
		int leftFoot = DDR(round+1, list.get(round),left) + cost(right, list.get(round));
		int rightFoot = DDR(round+1, right, list.get(round)) + cost(left, list.get(round));
		
		dp[round][right][left] = Math.min(rightFoot, leftFoot); 
		//지금라운드에 들어가는 값은 다음라운드로 가는 값중 작은것
		return dp[round][right][left];
	}
	private static int cost(int from, int to) {
		if(from == 0)
			return 2;
		else if(Math.abs(from - to) == 2)
			return 4;
		else if(from == to)
			return 1;
		else
			return 3;
				
	}
}