import java.io.*;
import java.util.*;

//줄  세우기
//10:34 ~ 10:43
public class _2605_줄세우기 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static long min, max; //1조 + 100만
	static long sum;
	static List<Integer> list = new ArrayList<Integer>();;
	static int[] save;
	static boolean flag = true;
//	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		for(int i = 1; i<= N; i++) {
			int loc =Integer.parseInt(st.nextToken());
			list.add(list.size()-loc, i);
		}
		for(int x: list) {
			sb.append(x + " ");
		}
		System.out.print(sb.toString());
	}
}
