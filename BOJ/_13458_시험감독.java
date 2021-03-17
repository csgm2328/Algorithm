import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _13458_시험감독 {
	static int N, B,C;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr =new int[N];
		for(int i = 0; i< N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken()); //총 
		C = Integer.parseInt(st.nextToken()); //부감독
		long sum = 0; //백만 * 백만
		
		for(int i =0; i< N; i++) {
			int n = (int)Math.ceil((double)(arr[i]-B)/C);
//			System.out.println(Math.ceil((double)(arr[i]-B)/C));
			if(n < 0)
				n =0;
			sum += n+1;
		}
		System.out.print(sum);
	}
}
