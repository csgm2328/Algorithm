import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _9095_1_2_3더하기 {
	static BufferedReader input;
	static StringBuilder output;
	static StringTokenizer st;
	static int N;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		output = new StringBuilder();
		// global init
		N = Integer.parseInt(input.readLine());
		for( int i =0 ; i< N;  i++) {
			int num = Integer.parseInt(input.readLine());
			int zero = 1;
			int one = 1;
			int two  =2;
			if( num == 1)
				output.append(1 + "\n");
			else {
				for(int j =3 ; j <= num; j++) {
					int x = zero + one + two;
					zero = one;
					one  = two;
					two = x;
				}
				output.append(two + "\n");
			}
		}
		System.out.println(output.toString());
	}
}
