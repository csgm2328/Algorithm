import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

//조커
//가장 긴 수열에 조커들 추가해보고 다시 길이 체크
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int joker = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[i] == 0)
				joker++;
		}
		Arrays.sort(arr);
		
		int max = 0;
		for (int i = joker; i < N; i++) { // 0 다음부터 돌기 시작 
			int pre = arr[i];
			int joker_c = joker;
			int sum =1;
			//간단하게 생각해보자
			//한칸 씩 뒤로 가면서 끝까지 탐색하고 길이를 재볼꺼니까 조커 넣을 수 있는 곳에 다 넣어봄
			for(int j = i+1; j < N; j++) {
				if(arr[j] - pre == 0) //앞 뒤 같은 경우
					continue;
				else if(arr[j] - pre -1 <= joker_c) { //스트레이트일때랑 조커 있는경우
					int diff = arr[j] - pre - 1;
					sum += diff+1; //스트레이트 일때는 1증가 조커일때는 2증가
					joker_c -= diff;
					pre = arr[j];
				}
				else break;
			}
			max = Math.max(max, sum+joker_c);
			
			//내가 생각한 너무 복잡한 방법 90% 맞음
//			out:for (int j = i + 1; j < N; j++) { 
//				int cur = arr[j];
//				if (pre + 1 == cur) {
//					sum++;
//				} else { // 조커도 없고 스트레이트 아닐때
//					if (joker_c > 0) {
//						sum++;
//						joker_c--;
//						pre = pre+1;
//						j--; //j 증가안하도록
//						continue out;
//					}
//					else if (max < sum)
//						max = sum;
//					sum = 1;
//				}
//				pre = cur;
//			}
//			if(joker_c > 0) {
//				sum += joker_c;
//			}
//			if (max < sum)
//				max = sum;
		}
		
		if(joker == N) max = joker; //반례: 전부다 조커일때  for문 안도니까
		
		System.out.print(max);
	}
}
