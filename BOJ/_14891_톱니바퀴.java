import java.io.*;
import java.util.*;

//3h
//톱니바퀴
//문제좀 정확히 파악하자
//톱니바퀴 하나 돌때 재귀로 턴이 증가하는게 아니라 돌아갈수있는거 체크하고
//시작점에서 먼곳에서 부터 돌리면서 한 턴만 한다.

//실패한 접근
//1. 환형큐는 인덱싱불가
//2. 재귀로 돌아갈수 있는 톱니를 큐에 넣어준 다음에 다시 확인함 --> 재확인하는 오류

public class _14891_톱니바퀴 {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static int num, dir; // 톱니번호 , 방향
//	static Deque<Character> dq1 = new LinkedList<>();
//	static Deque<Character> dq2 = new LinkedList<>();
//	static Deque<Character> dq3 = new LinkedList<>();
//	static Deque<Character> dq4 = new LinkedList<>();
	static char[][] dq = new char[5][8];
	static int[] index = { 0, 2, 2, 2, 6 }, tw = new int[5];
	static int sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// global init
		for (int i = 1; i <= 4; i++)
			dq[i] = br.readLine().toCharArray();
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			int[] cur = { num, dir }; //{톱니번호, 방향}
			int re_dir = cur[1] == 1 ? -1 : 1;

			switch (cur[0]) {
			case 1:
				if (dq[1][index[1]] != dq[2][(index[2] + 4) % 8]) {
					if (dq[2][index[2]] != dq[3][(index[3] + 4) % 8]) {
						if (dq[3][index[3]] != dq[4][index[4]]) {
							rotation(new int[] { 4, re_dir });
						}
						rotation(new int[] { 3, dir });
					}
					rotation(new int[] { 2, re_dir }); // 나오면서 돌려야함
				}
				rotation(new int[] { 1, dir });
				break;
			case 2:
				if (dq[1][index[1]] != dq[2][(index[2] + 4) % 8])
					rotation(new int[] { 1, re_dir });
				if (dq[2][index[2]] != dq[3][(index[3] + 4) % 8]) {
					if (dq[3][index[3]] != dq[4][index[4]])
						rotation(new int[] { 4, dir });
					rotation(new int[] { 3, re_dir });
				}
				rotation(new int[] { 2, dir });
				break;
			case 3:
				if (dq[2][index[2]] != dq[3][(index[3] + 4) % 8]) {
					// 3번 돌때 2번도 돌아야되면 1번도 검사
					if (dq[1][index[1]] != dq[2][(index[2] + 4) % 8]) {
						// 한번더 역방향
						rotation(new int[] { 1, dir });
					}
					rotation(new int[] { 2, re_dir });
				}
				if (dq[3][index[3]] != dq[4][index[4]])
					rotation(new int[] { 4, re_dir });
				rotation(new int[] { 3, dir });
				break;
			case 4:
				if (dq[3][index[3]] != dq[4][index[4]]) {
					if (dq[2][index[2]] != dq[3][(index[3] + 4) % 8]) {
						if (dq[1][index[1]] != dq[2][(index[2] + 4) % 8]) {
							rotation(new int[] { 1, re_dir });
						}
						rotation(new int[] { 2, dir });
					}
					rotation(new int[] { 3, re_dir });
				}
				rotation(new int[] { 4, dir });
				break;

			default:
				break;
			}
		}
		for (int i = 1; i <= 4; i++) {
			sum += (dq[i][tw[i]] - '0') * Math.pow(2, i - 1);
		}
		System.out.print(sum);
	}

	private static void rotation(int[] cur) {
		if (cur[1] == 1) {
			index[cur[0]] = (index[cur[0]] - 1) % 8;
			if (index[cur[0]] < 0)
				index[cur[0]] += 8;
			tw[cur[0]] = (tw[cur[0]] - 1) % 8;
			if (tw[cur[0]] < 0)
				tw[cur[0]] += 8;
		} else {
			index[cur[0]] = (index[cur[0]] + 1) % 8;
			tw[cur[0]] = (tw[cur[0]] + 1) % 8;
		}
	}
}