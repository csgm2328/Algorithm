package Lv2;

import java.util.HashSet;
import java.util.Set;


// 7자리수인데 체를 1백만까지만 해도 되네
// 체로 만드는 것보다 백만까지 26만 //50ms
// nC1 ~ nCn 에서 그때 그때 소수 체크하는게 훨씬 빠르다
public class 소수찾기 { 
	public static void main(String[] args) {
		System.out.println(solution("17"));
	}

	static Set<Integer> primes = new HashSet<>();
	static Set<Integer> already = new HashSet<>();

	static public int solution(String numbers) {
		boolean[] isPrime = new boolean[1000000]; // 7자리까지니까
		for (int i = 2; i < isPrime.length; i++) { // 백만: 52~ 113ms 60MB, 천만:300 ~ 408ms(max), 100MB
			if (!isPrime[i]) {
				for (int j = i + i; j < isPrime.length; j = j + i) {
					isPrime[j] = true;
				}
				primes.add(i);
			}
		}
		save = new boolean[numbers.length()];
		check = new char[numbers.length()];
		for (int r = 1; r <= numbers.length(); r++)
			permutation(0, r, numbers);
		return cnt;
	}

	static boolean[] save;
	static char[] check;
	static int cnt;

	static private void permutation(int index, int r, String numbers) {
		if (index == r) {
			String s = "";
			for (int i = 0; i < r; i++) { // r까지만 뽑아야 뒤에 공백이 안찍힘
				s += check[i]; // 저장한거 순서대로 스트링으로
			}
			System.out.println(s);
			if (s.length() == 0)
				return;
			int origin = Integer.parseInt(s);
//            System.out.println(origin);
			if (!already.contains(origin)) {
				if (checkPrime(origin))
					cnt++;
				already.add(origin);
			}
			StringBuilder sb = new StringBuilder(s);
			int re = Integer.parseInt(sb.reverse().toString());
			if (!already.contains(re)) {
				if (checkPrime(re))
					cnt++;
				already.add(re);
			}
			return;
		}
		for (int i = 0; i < numbers.length(); i++) { // 여기는 주어진 문자의 모든 걸 탐색
			if (save[i])
				continue;
			save[i] = true;
			check[index] = numbers.charAt(i);
			permutation(index + 1, r, numbers);
			save[i] = false;
		}

	}

	static private boolean checkPrime(int x) {
		if (primes.contains(x))
			return true;
		else
			return false;
	}

	static private boolean checkPrime2(int x) { //12 ~ 68ms,60MB
		if (x == 2)
			return true;
		else if (x % 2 == 0 || x == 1)
			return false;
		for (int i = 3; i <= (int) Math.sqrt(x); i += 2) { // 제곱근 까지 홀수로 나눠보기
			if (x % i == 0)
				return false;
		}
		return true;

	}
}
