

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
//실버4 N<= 5000
public class _1158_요세푸스_문제 {
	static int N, K;
	static List<Integer> arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		//arr =new ArrayList<Integer>();
		arr =new LinkedList<Integer>(); // +44ms 위치 갱신보다 탐색 오버헤드가 더큰가보네
		
		for (int i = 1; i <= N; i++) {
			arr.add(i);
		}
		int idx = 0;
		
		sb.append("<");
		while(arr.size() != 0) {
			idx = (idx + K-1) % arr.size();
			sb.append(arr.get(idx));
			arr.remove(idx);
			
			if(arr.size() == 0)
				sb.append(">");
			else
				sb.append(", ");
		}
		System.out.println(sb.toString());
	}
}
