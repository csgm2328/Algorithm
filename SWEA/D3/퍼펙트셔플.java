package D3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퍼펙트셔플 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= N; tc++) {
			int num = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			String[] front = new String[num / 2+1];
			String[] back = new String[num / 2]; //홀수 일때 하나 더들어감
			// Stack<String> stack = new Stack<String>();

			int idx = 0;
			int half = (num % 2 == 0 ) ? num/ 2 : num/2+1;
			int j = 0;
			
			for (; j < half; j++) {
				front[idx++] = st.nextToken();
			}
			idx =0;
			for (; j < num; j++) {
				back[idx++] = st.nextToken();
			}
			sb.append("#" + tc + " ");

			int frontidx = 0;
			int backidx = 0;
			for (int k = 0; k < num; k++) {
				if (k % 2 == 0)
					sb.append(front[frontidx++] + " ");
				else
					sb.append(back[backidx++] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
