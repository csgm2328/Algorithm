package D3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//규영이와 이기는 경우와 지는 경우
public class 규영이와_인영이의_카드게임 {
	static int[] guyoung = new int[9];
	static int[] inyoung = new int[9];
	static int[] save = new int[9];
	static boolean[] check = new boolean[9];

	
	static int WIN, ROSE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 출력 최적화 -18ms

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			 int[] arr = new int[19];	//실수!!! 초기화안해줬네,,
			 for (int i = 0; i < 9; i++) {
				arr[Integer.parseInt(st.nextToken())] = 1;
			}
			int gidx = 0;
			int iidx = 0;
			for (int i = 1; i <= 18; i++) {
				if (arr[i] == 1)
					guyoung[gidx++] = i;
				else
					inyoung[iidx++] = i;
			}
			permutation(0);
			sb.append("#" + tc + " " + WIN + " " + ROSE + "\n");

			// 초기화
			WIN = 0;
			ROSE = 0;
			
			Arrays.fill(check, false);
		}
		System.out.println(sb.toString());
	}

	static void permutation(int idx) {
		if (idx == 9) {
			int guscore= 0; 	//실수!!!!!!!!!!! 변수위치도 모르네
			int inscore = 0;
			for (int i = 0; i < 9; i++) { // 라운드
				// 규영이가 이기는 경우 세고 지는 경우도 세야함
				// 순열 차면 총점 계산해서 결과
				if (save[i] > guyoung[i]) // 인영이가 이기면
					inscore += save[i] + guyoung[i];
				else if (save[i] < guyoung[i])
					guscore += save[i] + guyoung[i];
			}
			if (guscore > inscore) // 라운드 총점이 규영이가 더크면
				WIN++;
			else
				ROSE++;
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (check[i])
				continue;

			check[i] = true;
			save[idx] = inyoung[i];
			permutation(idx+1);
			check[i] = false;
		}
	}
}
