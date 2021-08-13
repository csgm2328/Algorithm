package Lv1;

// 1. 어떻게 자리수를 자르지?
//
public class _2021_카카오_인턴쉽_1_숫자문자열과_영단어 {
	public static void main(String[] args) {
		String s = "oneseveneight";
		System.out.println(solution(s));
	}

	private static int solution(String s) {
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
				sb.append(s.charAt(i));
			} else {
				temp.append(s.charAt(i));
				// temp가 어떤 숫자가 완성되면 sb에 추가
				char x = toNumber(temp.toString());
				if (x != 'x') {
					sb.append(x);
					temp.setLength(0);
				}

			}
		}
		
		return Integer.valueOf(sb.toString());
	}

	private static char toNumber(String tmp) {
		switch (tmp) {
		case "zero":
			return '0';
		case "one":
			return '1';
		case "two":
			return '2';
		case "three":
			return '3';
		case "four":
			return '4';
		case "five":
			return '5';
		case "six":
			return '6';
		case "seven":
			return '7';
		case "eight":
			return '8';
		case "nine":
			return '9';
		default:
			return 'x';
		}
	}
}
