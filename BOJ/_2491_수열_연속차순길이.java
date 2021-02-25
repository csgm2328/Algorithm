import java.io.*;
import java.util.*;

//연속 오름차순 혹은 내림차순의 최대 길이
//01:00~
public class _2491_수열_연속차순길이 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		arr[0] = Integer.parseInt(st.nextToken());

		//오름차순 체크
		int plus_max = -1, sum = 1;
		for (int i = 1; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[i - 1] <= arr[i]) {
				sum++;
			} else { //틀린 경우에만 체크하고 나중에 sum == N일때만 갱신한다면 놓친다
				if (plus_max < sum)
					plus_max = sum;
				sum = 1; //1부터 시작
			}
		}
		if (plus_max < sum)
			plus_max = sum;

		//내림차순 체크
		int minus_max = -1;
		sum = 1;
		for (int i = 1; i < N; i++) {
			if (arr[i - 1] >= arr[i]) {
				sum++;
			} else {
				if (minus_max < sum)
					minus_max = sum;
				sum = 1; //1부터 시작
			}
		}
		if (minus_max < sum)
			minus_max = sum;
		int Max = minus_max >= plus_max ? minus_max : plus_max;
		
		System.out.print(Max);
	}
}
