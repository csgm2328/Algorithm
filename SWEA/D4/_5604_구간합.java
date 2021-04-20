package D4;
import java.io.*;
import java.util.*;

// 구간 합
// 점화식을 최대한 일반항으로 바꾼 다음에 반복되는 값들을 메모이제이션한다
// 메모이제이션을 어떻게 할지? HashMap
// 0~ 9까지는 일반항으로 바로
// 그리고 99,999,9999,99999~ 15자리까지
public class _5604_구간합 {
	static long from, to;
	static HashMap<Long, Long> save = new HashMap<Long, Long>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			from = Long.parseLong(st.nextToken()); //long입력인지 체크
			to = Long.parseLong(st.nextToken());
			//미리 저장해놓는 방식으로 하고 함수 안에서 저장안해도 속도는 똑같게 나온다 테케가 약해서인가?
//			save.put(-1L,0L); //from -1 까지라서
//			save.put(0L, 0L);
//			for(int i =1; i<=9;i++)
//				save.put((long) i, save.get((long) i-1) + i);
//			// 99 ~ 15자리까지
//			for (int i = 2; i <= 15; i++)
//				//An+1 = An * 10^(1++) + An * 10
//				save.put((long) (Math.pow(10, i) - 1),
//						(long) (save.get(9L) * Math.pow(10, i-1)
//								+ save.get((long) Math.pow(10, i - 1) - 1) * 10));
			sb.append("#" + tc + " " + (F(to) - F(from-1)) + "\n");
		}
		System.out.println(sb.toString());
	}

	// 전 저장수에다가 + G(n)
	private static long F(long n) {
		if (save.containsKey(n))
			return save.get(n);
		if(n <= 9)
			return (n*(n+1))/2; //일반항
		long v = NumberOfDigits(n);
		long result = F(n - 1 - n % v) + G(n);
		save.put(n, result);
		return result;
	}

	private static long NumberOfDigits(long n) {
		long cnt = 1;
		while(true) {
			if(cnt * 10 > n) break;
			cnt *= 10;
		}
		return cnt;
	}

	private static long G(long n) {
		long v = NumberOfDigits(n);
		return n / v * (n % v + 1) + F(n % v);
	}

}
