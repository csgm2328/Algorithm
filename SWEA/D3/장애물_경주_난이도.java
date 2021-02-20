package D3;
import java.util.Scanner;

public class 장애물_경주_난이도 {
	static Scanner sc = new Scanner(System.in);
	static int MAX_SIZE = 100; // 블록최대개수
	static int[] arr = new int[MAX_SIZE];

	public static void main(String[] args) {
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int num = sc.nextInt();
			
			for (int j = 0; j < num; j++) {
				arr[j] = sc.nextInt();
			}
			
			int[] diff = { 0, 0 };
			 // 난이도 계산
			for (int k = 0; k < num - 1; k++) { // 움직임
				if (arr[k] < arr[k + 1]) { // 다음칸이 높다면
					int newdiff = arr[k + 1] - arr[k];
					if (diff[0] < newdiff)
						diff[0] = newdiff;
				} else if (arr[k] > arr[k + 1]) {
					int newdiff = arr[k] - arr[k + 1];
					if (diff[1] < newdiff)
						diff[1] = newdiff;
				}
			}
			System.out.println("#" + tc + " " + diff[0] + " " + diff[1]);
		}
	}
}
