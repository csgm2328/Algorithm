import java.io.*;
import java.util.*;

// 펠린드롬 만들기
// 똑같은 거 찾아서 list에 사이에 껴넣는다
// 길이가 홀수일때는 똑같은 게 없는 것의 카운트가 하나일 때 마지막에 가운데에 껴넣는다
// 문제를 제대로 안봤다. 사전 순으로 앞서는 거를 출력해야하므로
public class _1213_펠린드롬_만들기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
//		String[] str = br.readLine().split("");
		String str = br.readLine();
		makePelindrome(str);
		
		
		
		
		
		
		
//		List<String> origin = new ArrayList<String>();
//		for(int i =0; i< str.length;i++)
//			origin.add(str[i]);
//		List<String> list = new ArrayList<String>();
//		int index =0;
//		int oneCnt =0;
//		
//		for(int i =0; i< origin.size();i++) {
//			boolean flag = false;
//			for(int j = i+1; j< origin.size();j++) {
//				if(origin.get(i).equals(origin.get(j))) {
//					list.add(index, origin.get(i));
//					list.add(index, origin.get(j));
//					origin.remove(i);
//					origin.remove(j-1);
//					index++;
//					i--;
//					flag = true;
//					break;
//				}
//			}
//			if(!flag)oneCnt++;
//			if(oneCnt > 1 || (oneCnt > 0 && str.length % 2 == 0)) {
//				System.out.println("I'm Sorry Hansoo");
//				System.exit(0);
//			}
//		}
//		if(origin.size() != 0)
//			list.add(index, origin.get(0));
//		for(String s: list)
//			System.out.print(s);
	}

	private static void makePelindrome(String str) {
		int[] cnt = new int[26];
		for(int i = 0; i< str.length();i++)
			cnt[str.charAt(i) - 'A']++;
		//개수를 센다음에 짝수인데 홀수 카운트가 있으면 끝
		//홀수일때는 1개까지 봐줌
		
		int oneCnt = 0;
		int oneIdx = 0;
		for(int i =0; i < cnt.length;i++) {
			if(cnt[i] % 2 == 1) {
				oneCnt++;
				oneIdx = i;
			}
		}
		if(oneCnt > 1 || (oneCnt > 0 && str.length() % 2 == 0)) {
			System.out.println("I'm Sorry Hansoo");
			System.exit(0);
		}
		
		//이제 펠린드롬 만드는데 앞 반절 만들고 뒤집어서 뒤 만들거임
		for(int i =0; i< cnt.length;i++) {
			for(int j =0 ;j < cnt[i]/2;j++) { //2면 1개찍고 4면 2개찍고
				System.out.printf("%c", i + 'A');
			}
		}
		//홀수일땐 하나짜리 가운데 찍고
		if(str.length() % 2 == 1)
			System.out.printf("%c",oneIdx+ 'A');
		
		//이제 뒤집어서 찍음
		for(int i= cnt.length-1; i >=0;i--) {
			for(int j =0; j< cnt[i]/2;j++) {
				System.out.printf("%c",i +'A');
			}
		}
	}
}
