import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//줄어드는 숫자(1~1023) == 1038_감소하는 수(0~1022)
//11:50~ 1:50
//321 950
//조합이였다
// 큰수로 정렬하고 10c1(한자리수) , 10c2(두자리수) ~ 10c10(열자리수) 경우 구하고
// 한자리부터 10자리수까지 만들 수 있는 조합의 수의 합은 1023 = 2^N - 1;
// 2^N인 부분집합에서 하나도 안고르는 경우가 빠진 결과
// 근데 조합을 오름차순으로 넣으면 순서가 잘못된다
// 그래서 arr을 내림차순으로 정렬한후 모든경우를 다 미리 배열에 저장해 놓은 다음에
// 그 저장한 곳에서 cnt로 찾는다

public class _1174_줄어드는_숫자 {
	static int N, cnt;
	static String[] store = new String[1024];
	static int[] save;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// global init
		N = Integer.parseInt(br.readLine());

		if (N > 1022)
			System.out.print(-1);
		else {
			// 10Cr
			for (int r = 10; r > 0; r--) {
				save = new int[r];
				combination(r, 0, 9);
			}
			System.out.print(store[N]);
		}
	}

	private static void combination(int r, int idx, int start) {
		if (idx == r) {
			StringBuilder sb = new StringBuilder();
			cnt++;
			for (int i = 0; i < save.length; i++)
				sb.append(save[i]);
			store[1023 - cnt] = sb.toString(); // 내림차순 결과니까 뒤에서부터 저장
			return;
		}

		for (int i = start; i >= 0; i--) { // 내림차순으로
			save[idx] = i;
			combination(r, idx + 1, i - 1);
		}
	}
}
