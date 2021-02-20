import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//최적화: sum도 파라미터로 근데 +4ms???
//bw, sb 추가하면 + 20ms

public class _2309_일곱난쟁이 {
	static int N = 9, r,sum; // 9C7
	static int[] arr = new int[N];
	static boolean[] check = new boolean[N];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	static void powerset(int idx, int r,int sum) throws IOException {
		// 일곱 난쟁이 찾기
		if (r == 7 ) {
			int[] ans = new int[7];
			int ansidx = 0;
			
			for (int i = 0; i < N ; i++) {
				if (check[i]) {
					ans[ansidx++] = arr[i];
				}
			}
			if (sum == 100) {
				for (int i = 0; i < 7; i++)
					sb.append(ans[i] + "\n");
				bw.write(sb.toString());
				bw.flush();
				br.close();
				System.exit(0);
			}
			return;
		}

		for (int i = idx; i < N; i++) {
			if (check[i])
				continue;

			check[i] = true;
			sum += arr[i];
			
			powerset(i, r + 1,sum);
			check[i] = false;
			sum -= arr[i];
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		//정렬도 먼저
		Arrays.sort(arr);
		powerset(0,0,0);
	}
}
