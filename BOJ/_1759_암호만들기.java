
import java.io.*;
import java.util.*;

//60m 문제좀 잘읽자
public class _1759_암호만들기 {
	static int n, r;
	static char[] arr;
	static boolean[] visited;

	static boolean mo(char x) {
		switch (x) {
		case 'a':
			return true;
		case 'e':
			return true;
		case 'i':
			return true;
		case 'o':
			return true;
		case 'u':
			return true;
		default:
			return false;
		}
	}
	static void combination(int cnt, int start) {
		if (r == cnt) {
			int mosum =0;
			int jasum =0;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < n; i++) {
				if (visited[i]) {
					if(!mo(arr[i])) jasum ++;
					else mosum++;
					sb.append(arr[i]);
				}
			}
			if(jasum >= 2 && mosum >=1)
				System.out.println(sb.toString());
			return;
		}

		for (int i = start; i < n; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			combination(cnt + 1, i+1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new char[n];
		visited = new boolean[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		combination(0, 0);
	}
}
