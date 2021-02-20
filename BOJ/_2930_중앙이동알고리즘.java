import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//등차 규칙
public class _2930_중앙이동알고리즘 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int iter = Integer.parseInt(br.readLine());// 1 ~ 15
		//int iter = (int) br.read();
		int start = 2;
		int result = 0;
		
		for(int i =1; i<= iter; i++) {
			start += (int) Math.pow(2, i-1);
			result = (int) Math.pow(start, 2);
			
		}
		System.out.println(result);
	}
}
