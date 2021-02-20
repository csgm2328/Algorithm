package D1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 중간값_찾기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] scores = new int[100 + 1];
		for (int tc = 1; tc <= T; tc++) {
			int score = Integer.parseInt(st.nextToken());
			scores[score]++;
		}

		// 중간값 구하기
		int cnt = 0;
		int i = 0;
		while (cnt < T / 2) { 
			//9일때 4가 되는 순간 끝남 앞에 4개가 있단 뜻이므로 i-1이 아니라 i
			//중복이 들어올 때는 어떻게 해야할까?
			//5를 찾아야하는데 4번쨰에서 6값이 되버리면 i-1을 출력해야함
			cnt += scores[i];
			if(cnt > T/2) break;
			i++;
		}
		sb.append(i);
		System.out.println(sb.toString());
	}
}
