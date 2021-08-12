package Lv2;

import java.io.*;
import java.util.*;

// 메뉴에 나온 것들을 코스길이 별로 잘라서 Map에 저장하고
// 가장 많이 주문되어 코스메뉴로 하기 적합한 것을 찾는다

public class _2021_KAKAO_메뉴_리뉴얼 {
	static HashMap<String, Integer> map = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String[] orders = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
//		String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
		String[] orders = {"XYZ", "XWY", "WXA"};
		int[] course = { 2, 3, 4 };
		solution(orders, course);
	}

	public static List<String> solution(String[] orders, int[] course) {
		List<String> answer = new ArrayList<String>();

		for (int i = 0; i < orders.length; i++) {
			visited = new boolean[orders[i].length()];
			for (int j = 0; j < course.length; j++) {
				save = new char[course[j]];
				comb(orders[i], course[j], 0, 0);
			}
		}
		// MAP에 다저장했으면
		// entrySet()은 key, value전부다 필요한 경우에 사용
		for (int i = 0; i < course.length; i++) {
			int max = -1;
			List<String> maxStr = new ArrayList<String>();
			for(String key : map.keySet()) {
				if (course[i] == key.length()) { // 코스길이별로 최대 구하기
					int value = map.get(key);
					if (max < value && value >= 2) {
						maxStr.clear();
						max = value;
						maxStr.add(key);
					} else if (max == value) {
						maxStr.add(key);
					}
				}
			}
			for (String x : maxStr)
				answer.add(x);
		}
		Collections.sort(answer);
		for (String x : answer)
			System.out.println(x);
		return answer;
	}

	static boolean[] visited;
	static char[] save;

	private static void comb(String str, int length, int start, int index) {
		if (index == length) {
			String s = "";
			char[] tmp = new char[save.length];
			for(int i =0; i< tmp.length;i++)	//save를 바꾸면 다음 조합만들때 문제발생
				tmp[i] = save[i];
			Arrays.sort(tmp);
			for (char x : tmp) {
				s += x;
			}
			if (map.containsKey(s)) {
				map.replace(s, map.get(s) + 1);
			} else {
				map.put(s, 1);
			}
			return;
		}
		for (int i = start; i < str.length(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				save[index] = str.charAt(i);
				comb(str, length, i + 1, index + 1);
				visited[i] = false;
			}
		}
	}
}