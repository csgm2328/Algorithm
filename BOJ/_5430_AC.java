import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// AC
// 덱을 뒤집는 과정에서 시간초과가 날까? 뒤집은거 안뒤집은거 두개 유지
// 배열의 개수는 10만개 

public class _5430_AC {
	static String p;
	static int N;
	static Deque<String> dq;
//	static Deque<Integer> re_dq; //뒤를 지우면 되는데 필요없지
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc< T; tc++) {
			String p = br.readLine();
			N = Integer.parseInt(br.readLine());
			// 여기서 substring을 쓰자
			String input = br.readLine();
			input = input.substring(1, input.length()-1);
//			StringTokenizer st = new StringTokenizer(input,",");
			String[] s = input.split(",");
			
			dq = new ArrayDeque<>();
			reverse = false;
			for(int i =0; i< s.length; i++) {
				if(N==0) break; //[] 이게 들어감
				dq.offer(s[i]);
//				if(s.length == 1) { //배열에 하나만 있을때는 양쪽을 지워야함
//					dq.offer(Integer.parseInt(s[i].substring(1,s[i].length()-1)));
//					break;
//				}
//				if(i==0)
//					dq.offer(Integer.parseInt(s[i].substring(1))); //[ 뒤에 있는거 넣음
//				else if(i == s.length-1)
//					dq.offer(Integer.parseInt(s[i].substring(0,s[i].length()-1)));
//				else
//					dq.offer(Integer.parseInt(s[i]));
			}
//			for(int i =0; i< s.length; i++) { //거꾸로 넣기
//				if(N==0) break;
//				if(s.length == 1) {
//					re_dq.offer(Integer.parseInt(s[s.length-i-1].substring(1,s[i].length()-1)));
//					break;
//				}
//				if(i == 0)
//					re_dq.offer(Integer.parseInt(s[s.length-i-1].substring(0,s[i].length()-1)));
//				else if(i == s.length-1)
//					re_dq.offer(Integer.parseInt(s[s.length-i-1].substring(1)));
//				else
//					re_dq.offer(Integer.parseInt(s[s.length-i-1])); //마지막부터 들어가므로 
//			}
			//함수동작
			
			if(func(p).equals("error"))
				sb.append("error").append("\n");
			else {
				if(!reverse) {
					sb.append("[");
					while(!dq.isEmpty()) {
						sb.append(dq.poll());
						if(dq.isEmpty())
							continue;
						sb.append(",");
					}
					sb.append("]\n");
				}
				else {
					sb.append("[");
					while(!dq.isEmpty()) {
						sb.append(dq.pollLast());
						if(dq.isEmpty())
							continue;
						sb.append(",");
					}
					sb.append("]\n");
				}
			}
		}
		System.out.println(sb.toString());
	}
	static boolean reverse;
	private static String func(String p) {
		for(int i =0 ; i< p.length();i++) {
			if(p.charAt(i) == 'R')  //뒤집기
				reverse = !reverse;
			else if(p.charAt(i) == 'D') { //앞에꺼 삭제
				if(!reverse) { //정상일때
					if(!dq.isEmpty()) {
						dq.removeFirst();
//						re_dq.removeLast(); //반대로 빼주기
					}
					else
						return "error";
				}
				else { //거꾸로일때
					if(!dq.isEmpty()) {
//						re_dq.removeFirst(); //반대로 빼주기
						dq.removeLast();
					}
					else
						return "error";
				}
			}
		}
		return "ok";
	}

}