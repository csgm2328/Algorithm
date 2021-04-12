package SW_TEST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 역시나 문제 잘 읽자 사람이 2명 고정이다
// 특강 해설
// 용량 큰 충전기순으로 정렬후 나에게 충전가능한 BC를 2개까지 찾는다
//0번 충전기로 없는 상황을 표시한다고?
//두배열에서  다른 충전영역인지 비교하고 각각 더함
//두 개가 다르다면 큰거는 바로 넣고 두번쨰꺼는 max비교해서 넣는다????????
//ㅁ르겠다

// 0412 라이브 강의
// 2명이니까 움직이고 난다음에 그 둘의 위치에서 모든 충전소의 중복순열을 구한 후
// 가능한 경우중에  MAX를 구하면 끝
public class _5644_무선충전 {
	static int M, A; // 이동 수, 충전소 수
	static int[] moveA, moveB;

	static class Charger {
		int r, c, coverage, power;

		public Charger(int r, int c, int coverage, int power) {
			super();
			this.r = r;
			this.c = c;
			this.coverage = coverage;
			this.power = power;
		}
	}

	static Charger[] chargers;
	static int[] dr = { 0, -1, 0, 1, 0 }; // 상,우,ㅡ하좌
	static int[] dc = { 0, 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			moveA = new int[M+1];
			moveB = new int[M+1];
			for (int i = 1; i <= M; i++)
				moveA[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++)
				moveB[i] = Integer.parseInt(st.nextToken());

			chargers = new Charger[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken()); //거꾸로
				int r = Integer.parseInt(st.nextToken());
				int coverage = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				chargers[i] = new Charger(r, c, coverage, power);
			}

			// a,b 초기위치
			int[] locA = { 1, 1 };
			int[] locB = { 10, 10 };
			// a,b 움직이고
			int sum = 0;
			//처음도 충전가능하려면
			for (int i = 0; i <= M; i++) {
				locA[0] += dr[moveA[i]];
				locA[1] += dc[moveA[i]];
				locB[0] += dr[moveB[i]];
				locB[1] += dc[moveB[i]];

				sum += getEnergy(locA, locB);
			}
			sb.append("#" + tc + " " + sum + "\n");
		}
		System.out.println(sb.toString());
	}

	private static int getEnergy(int[] locA, int[] locB) {
		// 충전소 경우의 수 중에서 현재 위치에서 충전가능한 곳 찾기
		// 현재위치에서 충전가능한 곳으로 만들 수 있는 경우의 수는?
		int max = -1;
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < A; j++) {
				int chargeA = 0, chargeB = 0;
				if (Math.abs(chargers[i].r - locA[0]) + Math.abs(chargers[i].c - locA[1]) <= chargers[i].coverage)
					chargeA = chargers[i].power;
				if (Math.abs(chargers[j].r - locB[0]) + Math.abs(chargers[j].c - locB[1]) <= chargers[j].coverage)
					chargeB = chargers[j].power;
				int sum = 0;
				if (i != j)
					sum = chargeA + chargeB;
				else
					sum = Math.max(chargeA, chargeB);
				if (max < sum)
					max = sum;
			}
		}
		return max;
	}

	private static int Chargable(int[] locA, int i) {
		// 현재위치가 충전가능한지 확인
		return 0;
	}
}
