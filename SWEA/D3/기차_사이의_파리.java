package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 기차_사이의_파리 {
	static float D,A,B,F;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Float.parseFloat(st.nextToken());
			A = Float.parseFloat(st.nextToken());
			B = Float.parseFloat(st.nextToken());
			F = Float.parseFloat(st.nextToken());
			float sum = D/(A+B) * F;
			sb.append("#" + tc + " "+ sum + "\n"); // -230ms
		}
		System.out.println(sb.toString());
	}
}
