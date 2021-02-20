import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1244_스위치_켜고_끄기 {

	static int switching(int x) {
//		if (x == 0)
//			return 1;
//		else
//			return 0;
		return x = x == 1 ? 0: 1;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int switchNum = Integer.parseInt(br.readLine());
		int[] arr = new int[switchNum+1]; //1부터
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < switchNum+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int studentNum = Integer.parseInt(br.readLine());
		for (int i = 0; i < studentNum; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int loc = Integer.parseInt(st.nextToken()); // -1하면 안됨

			// 남자면
			if (gender == 1) {
				for (int j = loc; j < arr.length; j++) {
					if (j % loc == 0) // 위치의 배수위치이면
						arr[j] = switching(arr[j]);
				}
			} else {
				arr[loc] = switching(arr[loc]);

				for (int k = 1;; k++) {
					if (1 <= loc - k && loc + k < arr.length) { //length하나 늘었으니까
						if (arr[loc - k] == arr[loc + k]) {  //이런거를 if문 하나에써야 실수 안함
							arr[loc-k] = switching(arr[loc-k]);
							arr[loc+k] = switching(arr[loc+k]);
						}
						else break;
					}
					else break; //왜 실수하냐 이런거
				}
			}
		}
		for(int i= 1; i< arr.length; i++) {
			System.out.print(arr[i] + " ");
			if(i % 20 == 0) System.out.println();
		}
	}
}
