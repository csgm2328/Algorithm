import java.io.*;
import java.util.*;
// 부분합 투포인터
public class _1806_부분합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수열의 개수 (최대 10만)
		int S = Integer.parseInt(st.nextToken()); // S이상으로 만든 수열 (최대 1억)

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N+1]; //end가 한칸 더 가서 start를 기다릴수 있도록
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int ans = StartAndEnd(N,S,arr);
		System.out.println(ans == Integer.MAX_VALUE? 0: ans);
		
		
//
//		int firstSum = 0, curSum = 0;
//		int firstLen = Integer.MAX_VALUE, curLen = 0;
//		boolean flag = false; // 첫번째 만족하는 수열을 찾았는지
//		for (int i = 0; i < N; i++) {
//			if (arr[i] == S) { // S만나면 바로탈출
//				System.out.println(1);
//				System.exit(0);
//			}
//			if (flag) {
//				if (curLen + 1 < firstLen) { // 만족하는거 찾았고 그 다음 진행하면서 그 길이보다 작은수열만 유지
//					curSum += arr[i];
//					curLen++;
//				} else {
//					curSum += arr[i]; // 새로운거는 더하고
//					curSum -= arr[i - curLen]; // 가장 뒤에거는 빼고
//					// 길이는 유지됨
//				}
//			}
//			else {
//				curSum += arr[i];
//				curLen++;
//			}
//			if (curSum >= S) { // 지금까지 더해오던게 S를 넘으면 최적 길이를 찾아냄
//				int temp = getLen(arr, i, curLen, curSum, S);
//				firstLen = Math.min(firstLen, temp);
//				if (firstLen == 1) {
//					System.out.println(1);
//					System.exit(0);
//				}
//			}
//			
//		}
//		System.out.println(firstLen);
	}

	private static int StartAndEnd(int n, int s, int[] arr) {
		int ans= Integer.MAX_VALUE;
		int start =0, end=0;
		int sum = 0;
		while(start <= n && end <= n) { //end는 도착해도 start가 끝까지 올때까지 기다려줘야해서
			if(sum >= s && ans > end-start)
				ans = end - start;
			if(sum < s)
				sum += arr[end++];
			else
				sum -= arr[start++];
		}
				
		return ans;
	}

	private static int getLen(int[] arr, int loc, int len, int curSum, int S) {
		int minus = 0;
		for (int i = loc - len + 1; i < loc; i++) {
			if (curSum - arr[i] >= S) {
				curSum -= arr[i];
				minus++;
			} else
				break;
		}
		return len - minus;
	}
}
