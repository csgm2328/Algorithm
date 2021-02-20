package D2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최빈수_구하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			br.readLine();
			int[] scores = new int[100+1];
			int students = 1000;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i =0; i< students;i++) {
				int score = Integer.parseInt(st.nextToken());
				scores[score]++;
			}
			
			//실수
			// 값이랑 인덱스 구분해서 저장해야지
			int max = -1;
			int maxIndex = -1;
			for(int i = 0; i< scores.length;i++) {
				if(max <= scores[i]) {	//문제를 제대로 안봤네 하 =을 붙여
					max= scores[i];
					maxIndex = i;
				}
			}
			
			sb.append("#" + tc + " " +  maxIndex + "\n");
		}
	System.out.println(sb.toString());
	}
}
