package D3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

//이런 입력 많은 건 꼭 buffered
//pq 두개 돌리는건 속도가 느림

public class 기본_1일차_Flatten{
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
			int dumpNum = sc.nextInt();
			int[] boxes = new int[100];	
			PriorityQueue<Integer> lpq = new PriorityQueue<Integer>(Collections.reverseOrder());
			PriorityQueue<Integer> spq = new PriorityQueue<Integer>();
			int max = -1, maxIndex = -1;
			int min = 101, minIndex = -1;
			
			String[] s = br.readLine().split(" ");
			for(int i = 0; i< boxes.length; i++) {
//				boxes[i] = Integer.parseInt(s[i]);
				
				int height = sc.nextInt();
				lpq.add(height);
				spq.add(height);
//				if (max < boxes[i]) {
//					max = boxes[i];
//					maxIndex = i; //위치기억
//				}
//				else if (min > boxes[i]) {
//					min = boxes[i];
//					minIndex = i;
//				}
			}
//			Arrays.sort(boxes);
			for(int i = 0; i< dumpNum;i++) {
				//큰거 -1
//				boxes[boxes.length-1]--;
//				max--;	//실수 한부분 max값은 안바뀐다
				
//				//작은거 +1
//				boxes[0]++;
//				min++;
//				//다시 max min 갱신
//				for(int j =0 ; j< boxes.length; j++) {
//					if(max < boxes[j]) {
//						max = boxes[j];
//						maxIndex = j;
//					}
//					else if(min > boxes[j]) {
//						min = boxes[j];
//						minIndex = j;
//					}
//				}
//				if(boxes[boxes.length-1] - boxes[0] <=1) break;
//				Arrays.sort(boxes);
				max = lpq.poll();
				min =spq.poll();
				lpq.add(max-1);
				spq.add(min+1);
			}
			System.out.println("#" + tc + " " + (lpq.peek() - spq.peek()));
//			System.out.println("#" + tc + " " + (boxes[boxes.length-1] - boxes[0]));
		}
	}
}
