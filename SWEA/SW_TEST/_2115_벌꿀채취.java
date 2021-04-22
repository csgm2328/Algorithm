package SW_TEST;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2차원 안에 2개의 꿀통 배치(조합) + 배치안에서 최대 합(부분집합)
public class _2115_벌꿀채취 {
	static int N, M, C;
	static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					arr[i][j] = Integer.parseInt(st.nextToken());
			}

			// 두명의 일꾼이 배치한 꿀통에서 최대로 얻을수 있는 수익
			check = new int[N][N]; // a: 1, b: 2
			combination(0); // 꿀통 배치
			
			sb.append("#" + tc + " " + ans + "\n");
			ans = -1; //실수포인트 !! 전역변수초기화
		}
		System.out.println(sb.toString());
	}

	static int ans = 0;
	static int[][] check;

	private static void combination(int idx) {
		if (idx == 2) {
			// a,b 부분집합 체크
			int[] a = new int[M];
			int[] b = new int[M];
			int acnt = 0;
			int bcnt = 0;
			for (int i = 0; i < N; i++) {
				if (acnt == M && bcnt == M)
					break;
				for (int j = 0; j < N; j++) { 
					if (acnt == M && bcnt == M)
						break;
					if (check[i][j] == 1)
						a[acnt++] = arr[i][j];
					if (check[i][j] == 2)
						b[bcnt++] = arr[i][j];
				}
			}
			save = new boolean[M];
			subset(0, 0, 0,a);
			//powerset(0,0,a);
			int s = MAX;
			MAX = -1;
			save = new boolean[M];
			subset(0, 0, 0, b);
			s += MAX;
			MAX =-1; //실수포인트 !! 전역변수초기화
			ans = Math.max(ans, s);
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) { //N-M까지 해도됨
				if (check[i][j] == 0 && j + M - 1 < N && check[i][j+M-1] == 0) {
					for (int k = 0; k < M; k++) {
						if (idx == 0)
							check[i][j + k] = 1;
						else
							check[i][j + k] = 2;
					}

					combination(idx + 1);
					for (int k = 0; k < M; k++)
 						check[i][j + k] = 0;
				}
			}
		}
	}

	static boolean[] save;
	static int MAX = -1;

	private static void powerset(int idx, int sum, int[] batch) {
		if (sum > C)
			return;
		if (idx == M) {
			// max 검사
			int sum_max = 0;
			int cnt = 0;
			boolean flag= true;
//			for (int i = 0; i < M; i++) { //???? 아니 분명히 꿀통안에서 연속해서 고르라했는데
//				if (save[i]) {
//					cnt++;
//					if(cnt > 1 && !save[i-1]) {
//						flag = false;
//						break;
//					}
//				}
//			}
//			if(!flag) return;
				
			for (int i = 0; i < M; i++) {
				if (save[i]) {
					sum_max += batch[i] * batch[i];
				}
			}
			MAX = Math.max(MAX, sum_max);
			return;
		}
		save[idx] = true;
		powerset(idx + 1, sum + batch[idx], batch);
		save[idx] = false;
		powerset(idx + 1, sum, batch); //실수포인트 !! 뺴는게 아니라 더하지만 말아야지
	}
	private static void subset(int idx, int weight, int profit,int[] batch ) { // 177 - 25ms
		if(weight > C)
			return;
		if(idx ==M) {
			MAX = Math.max(MAX, profit);
			return;
		}
		subset(idx+1, weight+batch[idx], profit + batch[idx] * batch[idx], batch);
		subset(idx+1, weight, profit, batch);
	}
}
