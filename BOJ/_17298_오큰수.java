
import java.io.*;
import java.util.*;

//100m
//스택 활용법
//현재는 고정하고 전에 있는것들을 지워가면서 비교할 수 있다

public class _17298_오큰수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Stack<Integer> stack = new Stack<Integer>();
		
		int N = Integer.parseInt(br.readLine()); 
		int[] nge = new int[N];	//지역선언 + 20ms
		int[] arr = new int[N];
//		String[] input = br.readLine().split(" ");
//		int[] arr = Arrays.stream(input).mapToInt(Integer::parseInt).toArray(); // 1200 + 88ms
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken());
			arr[i] = x;
		}
		
		//다음부터 계속 찾아나가는 거는 시간초과
//		int i = 1; // 두번째부터 비교
//		while (q.size() != 1 && i < arr.size()) {
//			// 오른쪽에 있는 현재보다 큰 수중 가장 작은 것.
//			int oken = -1; // 실수!
//			int cur = q.poll();
//			for (int j = i; j < arr.size(); j++) {
//				if (arr.get(j) > cur) {
//					oken = arr.get(j);
//					sb.append(oken + " ");
//					break;
//				}
//			}
//			if(oken == -1)
//				sb.append(oken + " ");
//			i++;
//		}
		
		for(int i=0; i< N; i++) {
			while(!stack.isEmpty()) {
				if(arr[stack.peek()] < arr[i]) {	//현재꺼보다 작은 것들의 위치에 다 현재꺼 저장
					nge[stack.pop()] = arr[i];
				}
				//다음꺼가 작다면
				else {
					stack.push(i);
					break;	//다음 꺼로 넘어가
				}
			}
			//비어있거나 자기보다 작은 것들에 다 저장하고 나면 현재꺼도 저장
			if(stack.isEmpty()) 
				stack.push(i); //순서대로 저장하는게 아니라 스택에서 빠지는 순으로 넣을려고
		}
		//오큰수 없는 것들 처리
		while(!stack.isEmpty()) {
			nge[stack.pop()] = -1;
		}
		
		for(int x: nge) {	//bw 안쓰면 시간초과, iterator 출력 -30ms
			bw.write(x + " ");
		}
		
		bw.flush(); 
	}
}
