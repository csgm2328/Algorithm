import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//부분 집합 구하기
//2^N이라 20까지가능
//주어지는 값은 절댓값  10만이하
//맞춰야되는 합은 절댓값 100만이하
//수열이라 정렬된 상태
public class _1182_부분수열의합 {
	static int N,S;
	static int[] arr;
	static int cnt,lcnt;
	static int[] save;
//	static int[] dr = { -1, 0, 1, 0 };
//	static int[] dc = { 0, 1, 0, -1 };
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// global init
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];
		save = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

		powerset(0,0);
		System.out.print(S == 0 ? cnt-1: cnt); //0이 시ㅅ작이니까 0일때만
	}
	private static void powerset(int idx, int sum) {
		// TODO Auto-generated method stub
		if(idx == N) {
			System.out.println("가지고 다니는 합" + "<" + sum + ">");
			if(sum == S) 
				cnt++;
//			int lsum =0;
//			for(int i = 0; i< N; i ++) {
//				if(save[i] == 1) {
//					System.out.print(arr[i]+" ");
//					lsum += arr[i];
//				}
//			}
//			if(lsum == S) lcnt ++;
//			System.out.println("합: " + lsum + "경우의수: " + lcnt);
			return;
		}
		
//		save[cnt] = 1;
		powerset(idx+1, sum+arr[idx]);
//		save[cnt] = 0;
		powerset(idx+1, sum);
		
	}
}
