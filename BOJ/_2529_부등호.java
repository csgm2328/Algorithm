import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//순열중에 부등호 만족하는 것 중에 Max, Min 구하기
//10자리는  int를 넘을 수 있다
public class _2529_부등호 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static char[] oper;
	static char[] save;
	static boolean[] visit = new boolean[10];
	static long Max = -1, Min = Long.MAX_VALUE; //실수!!!!!!!!!!!int로 초기화했다
	static String Max_res = "", Min_res = ""; // 출력용

//	static int[] dr = { -1, 0, 1, 0 };
//	static int[] dc = { 0, 1, 0, -1 };
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// global init
		N = Integer.parseInt(br.readLine());
		oper = new char[N];
		save = new char[N + 1];

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			oper[i] = st.nextToken().charAt(0);

		permutaion(0, 0);
		sb.append(Max_res + "\n" + Min_res);

		// output
		System.out.print(sb.toString()); // 0이 시ㅅ작이니까 0일때만
	}

	private static void permutaion(int saveIdx, int operIdx) {
		if (saveIdx == N + 1) { // 부등호개수보다 한개많을때 탈출
			long num = 0;
			String res = "";
			for (int i = 0; i < N + 1; i++) {
				num += (long) ((save[i] - '0') * Math.pow(10, N - i)); // long cast 실수
				res += save[i];
			}
			if (Max < num) {
				Max = num;
				Max_res = res;
			}
			if (Min > num) {
				Min = num;
				Min_res = res;
			}
			return;
		}

		// 0 ~ 9
		for (int i = 0; i < 10; i++) {
			// 그 숫자 안썼고 부등호가 성립하면 go
			if (visit[i])
				continue;
			if (saveIdx == 0) { // 처음은 그냥 넣고
				save[saveIdx] = Character.forDigit(i, 10);
				visit[i] = true;
				permutaion(saveIdx + 1, operIdx);
				visit[i] = false;
			} else {
				switch (oper[operIdx]) { // 해당위치의 부등호 체크
				case '<':
					if (save[saveIdx - 1] - '0' < i) {
						save[saveIdx] = Character.forDigit(i, 10);
						visit[i] = true;
						permutaion(saveIdx + 1, operIdx + 1);
						visit[i] = false;
					}
					break;
				case '>':
					if (save[saveIdx - 1] - '0' > i) {
						save[saveIdx] = Character.forDigit(i, 10);
						visit[i] = true;
						permutaion(saveIdx + 1, operIdx + 1);
						visit[i] = false;
					}
					break;

				default:
					break;
				}
			}
		}//end for
	}
}
