import java.io.*;
import java.util.*;

// 그리디 방법은 2h
// 완탐은 10m
public class _10819_차이를최대로 {
	static StringBuilder sb = new StringBuilder();
	static int N;
//	static int[] dr = { -1, 1, 0, 0 };
//	static int[] dc = { 0, 0, -1, 1 };

	static int Max = -1;
	static int[] arr;
	static int[] save;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		visited = new boolean[N];
		save = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
//		permutation(0);
		
		// 절대값 안의 차이가 지속적으로 크게 만든다
		// 정렬 후 중간을 나눠서 반복문
		// N이 홀수 일때는 중간값이 앞 뒤에서 차이가 큰 것과의 계산결과를 선택한다
		// 1  4  8
		// 10 15 20
		// <------ 이 방향으로 계산할거임 
		Arrays.sort(arr);
		int sum = 0;
		int M = N;
		if(N %2 == 1) { //N이 홀수 일때는 한번만 쓰인 첫 시작 중간-1과 마지막으로 끝난 중간+1 중에 차이가 큰걸로 고름
			if(arr[N/2] - arr[N/2-1] > arr[N/2+1] - arr[N/2]) { //
				sum += arr[N/2] - arr[N/2-1];
			}
			else
				sum += arr[N/2+1] - arr[N/2];
			M--;
		}
		int front = N/2-1; //중간부터
		int rear = N-1;	//끝부터
		for(int i = 0; i< M-1; i++) {//N-1번 계산
			if(i %2 == 0)
				sum += arr[rear] - arr[front--];
			else
				sum += arr[rear--] - arr[front];
		}
		System.out.print(sum);
	}

	// 0 -1 + 1-2 + 2-3
	static void permutation(int cnt) {
		if (cnt == N) {
			int sum = 0;
			for (int i = 0; i < N - 1; i++) {
				sum += Math.abs(save[i] - save[i + 1]);
//				if (sum < Max)
//					return;
			}
			if (Max < sum)
				Max = sum;
			return;

		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			save[cnt] = arr[i];
			permutation(cnt + 1);
			visited[i] = false;
		}
	}
}
