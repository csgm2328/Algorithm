package D3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 민석이의_과제_체크하기 {
	static int pNum, cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pNum = Integer.parseInt(st.nextToken());
			cnt = Integer.parseInt(st.nextToken());
			int[] arr = new int[pNum+1];
			boolean[] check = new boolean[pNum+1];
		
			st = new StringTokenizer(br.readLine());
			for(int i= 0; i< cnt; i++) {
				int studentNum = Integer.parseInt(st.nextToken());
				check[studentNum] = true;
			}
			sb.append("#" + tc + " ");
			for(int i =  1; i<=pNum; i++) {
				if(!check[i]) {
					sb.append(i + " ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
