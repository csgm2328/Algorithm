import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// cubeEditor
// 부분문자열을 중복을 제거하는 거에서
// 중복을 체크할 때 몇개가 중복되는지 체크하니까 그게 가장 큰 수를 리턴하면 끝
public class _1701_CubeEditor {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static int MAX = -1;
	public static void main(String[] args) throws IOException {
		String s = input.readLine();
		String[] str = new String[s.length()];
		for(int i =0 ; i< s.length(); i++) {
			str[i] =  s.substring(i);
		}
		Arrays.sort(str); //각 부분문자열을 자리순대로 비교하려고 정렬
		
		for(int i = 1; i< str.length; i++)
			Common_sub(str[i-1],str[i]); //정렬한 부분 문자열을 자리를 비교하면서 공통길이를 찾는다
		System.out.println(MAX);
	}

	private static void Common_sub(String s1, String s2) {
		int common_cnt =0 ;
		for(int i = 0; i < s1.length(); i++) {
			if(i > s2.length()-1)
				break;
			if(s1.charAt(i) == s2.charAt(i)) 
				common_cnt++; //같은거의 길이 센다
			else //공통문자열이 한번끊기면 그만
				break;
		}
		if(MAX < common_cnt) //가장 긴 길이
			MAX = common_cnt;
	}
}
