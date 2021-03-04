import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Z
//R,C가 4일때
//2로 나눌 수 있으면
//나누고 (0,0) 부터 (0,0+4/2), (0+2,0), (2,2)에서 시작한다
//방향은 딱 두가지
//근데 범위체크가 시작점에서 R+2,c+2이다

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static char[][] arr;
	static boolean[][] visit;

	static int[] dr = { 0, 1};
	static int[] dc = { 1, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// global init
		N = Integer.parseInt(br.readLine());
		int R = (int) Math.pow(2, N);
		int C = (int) Math.pow(2, N);
		arr = new char[R][C];

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = st.nextToken().charAt(0);

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
				switch (arr[operIdx]) { // 해당위치의 부등호 체크
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
