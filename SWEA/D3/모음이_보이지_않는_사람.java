package D3;
import java.util.Scanner;

public class 모음이_보이지_않는_사람 {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int T = sc.nextInt();
		for (int tc = 1; tc<=T;tc++) {
			String s = sc.next();
			
			//모음제거 'a','e''i''o''u'
			String result = new String();
			for(int j = 0; j < s.length(); j++) {
				if(s.charAt(j) == 'a' || s.charAt(j) == 'e' || s.charAt(j) == 'i'
						|| s.charAt(j) == 'o' || s.charAt(j) == 'u') {
					continue;
				}
				else result += s.charAt(j);
			}
			System.out.println("#" + tc +" " + result);
		}
	}
}
