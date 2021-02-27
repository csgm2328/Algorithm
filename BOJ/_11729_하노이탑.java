import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

//하노이
public class _11729_하노이탑 {
	static int cnt = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		hanoi(N, 1, 2, 3);
		System.out.println(cnt);
		System.out.print(sb.toString());

	}

	// 1. n-1개 원판 이동 ( 시작 -> 임시) : 재귀
	// 2. n번째 원판 이동( 시작 -> 목적)
	// 3. n-1개 원판 이동( 임시 -> 목적) : 재귀
	private static void hanoi(int n, int start, int temp, int to) {
		// TODO Auto-generated method stub
		if (n == 1) {
			move(n, start, to);	//1개일때는 바로이동
		} else {
			hanoi(n - 1, start, to, temp); // n-1개를 시작에서 중간으로
			move(n, start, to);				// n번째를 목적으로
			hanoi(n - 1, temp, start, to);	// n-1개를 임시에서 목적으로
		}
	}

	private static void move(int n, int start, int to) { //함수 호출 324 + 56ms
		cnt++;
		sb.append(start + " " + to + "\n");
	}

	// 라이브 강의
	private static void hanoi1(int n, int start, int temp, int to) { //324 - 44ms
		if (n == 0)
			return;

		hanoi(n - 1, start, to, temp); // n-1개를 start 시작에서 중간으로
		cnt++;
		sb.append(start + " " + to + "\n");
		hanoi(n - 1, temp, start, to);
	}
}
