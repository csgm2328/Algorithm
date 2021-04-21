package D4;

import java.io.*;
import java.util.*;

// 방향을 전환해서 도착하는 규칙을 수식으로
// 홀수일때만 차이에서 -1
public class _8382_방향전환 {
	static int sr, sc,fr,fc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sr = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			fr = Integer.parseInt(st.nextToken());
			fc = Integer.parseInt(st.nextToken());
			
			int dis_r = Math.abs(sr - fr);
			int dis_c = Math.abs(sc - fc);
			int min = Math.min(dis_r, dis_c);
			int max = Math.max(dis_r, dis_c);
			int diff= max -min;
			
			sb.append("#" + tc + " " + (min * 2 + diff * 2 - (diff%2)) + "\n");
		}
		System.out.println(sb.toString());
	}
}
