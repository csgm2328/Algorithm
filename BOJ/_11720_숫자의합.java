
import java.io.*;

public class _11720_숫자의합 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int sum =0;
		char[] line = br.readLine().toCharArray();
		for (char c : line)
			sum += Character.getNumericValue(c);
		System.out.print(sum);
	}
}
