package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 햄버거다이어트 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static int N; // 재료의 수
	static int limit; //칼로리의 리미트
	static int[] score;
	static int[] cal;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 1 ~ 20
			limit = Integer.parseInt(st.nextToken());
			score = new int[N];
			cal = new int[N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}
			//재료를 선택하는 모든 경우의 수에 대해서, limit가 안넘는 score합의 최대를 구하자.
			sel = new boolean[N];
			ans = 0;
			hamburger(0, 0, 0); //계산 최적화 196ms
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb.toString());
	}
	static boolean[] sel;
	static int ans = 0;
	
	static void hamburger(int idx, int sumCal, int sumScore) {
		if( sumCal > limit)
			return;
		if( sumScore > ans)
			ans = sumScore;
		if( idx == N) 
			return;
		hamburger(idx + 1, sumCal + cal[idx], sumScore + score[idx]);
		hamburger(idx + 1, sumCal, sumScore);
	}
	
	static void powerset(int idx) {
		if( idx == N ) {
			//이번 케이스에서 선택되어진 재료들의 칼로리와 점수의 총합을 구해보자.
			int sumCal = 0;
			int sumScore = 0;
			for(int i = 0; i < N; i++) {
				if( sel[i] ) {
					sumCal += cal[i];
					sumScore += score[i];
				}
			}
			if( sumCal < limit && sumScore > ans)
				ans = sumScore;
			return;
		}
		sel[idx] = true;
		powerset(idx + 1);
		sel[idx] = false;
		powerset(idx + 1);
	}
}
