import java.io.*;
import java.util.*;

//문자열 폭발: 지우고 합치고를 자연스럽게 되도록 스택으로
//반례: A / BA boom문자가 더 긴 경우
public class __9935_문자열폭발 {
	static Stack<Character> stack = new Stack<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine(); // 1 ~ 1백만
		String boom = br.readLine(); // 1 ~ 36
		int idx =0;
		for (int i = 0; i < str.length(); i++) {
			stack.add(str.charAt(i));
			if(str.charAt(i) == boom.charAt(idx)) {
				idx++;
				if(idx == boom.length()) {
					Explosion(boom);
					idx = 0;
					continue;
				}
			}
			else
				idx =0;
			if(boom.charAt(boom.length()-1) == str.charAt(i))
				CheckBoom(boom);
		}
		if(stack.isEmpty())
			System.out.println("FRULA");
		else {
			StringBuilder sb = new StringBuilder();
			Iterator<Character> it = stack.iterator();
			while(it.hasNext())
				sb.append(it.next());
			System.out.println(sb.toString());
		}
	}

	private static void CheckBoom(String boom) {
		if(boom.length() > stack.size())
			return;
				
		Stack<Character> temp = new Stack<>();
		int idx = boom.length()-1; 
		while(!stack.isEmpty()) {
			if(stack.peek() == boom.charAt(idx)) {
				temp.push(stack.pop());
				if(idx == 0)
					return;
				idx--;
			}
			else {
				while(!temp.isEmpty())
					stack.push(temp.pop());
				return;
			}
		}
	}

	private static void Explosion(String boom) {
		for(int i =0; i< boom.length();i++)
			stack.pop();
	}
}
