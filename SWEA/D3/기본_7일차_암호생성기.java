package D3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//이 큐로 해결되는 문제는 규칙성이 있다.
//계산식으로 반복을 돌지 않아도 되는 접근법을 항상 생각해봐야함

public class 기본_7일차_암호생성기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) { // 문제에 안써있지만 10개
			int testNum = Integer.parseInt(br.readLine());
			int[] arr = new int[8];
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 8; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			boolean close = false;

			while (!close) {
				List<Integer> temp  = new ArrayList<Integer>();

				// 한 싸이클
				for (int i = 1; i <= 5; i++) {// 첫번째 위치 - i 이걸 다섯번
					// index %= arr.length; //index 길이안에서 이어서
					if (arr[i - 1] - i <= 0) { // 0이하면 종료
						temp.add(0);
						close = true;
						break;
					}
					else temp.add(arr[i- 1] -i); // 한번에 위치조정할거임
				}

				// 안바뀐거 앞으로
				int change_index = 0;
				for (int i = temp.size(); i < arr.length; i++) //3개에서 끝났으면 3부터 이동
					arr[change_index++] = arr[i]; // 변경안된거 당기고

				// 바뀐거 뒤로
				int temp_index =0;
				for (int j = arr.length - temp.size(); j < arr.length; j++)
					arr[j] = temp.get(temp_index++); // 변경된 5개 뒤에다 갱신
			}
			
			
			sb.append("#" + testNum + " ");
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
