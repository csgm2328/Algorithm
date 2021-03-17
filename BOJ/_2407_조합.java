
import java.io.IOException;
import java.util.Arrays;
//조합을 만드는 세가지 방법
public class _2407_조합 {
	
//	3방법의 시간복잡도는 같다 int한계인 100 6 까지 4초가량 걸림
	
	static int combination0(int n, int r) {
		if (n == r || r == 0)
			return 1;
		else
			return combination0(n - 1, r - 1) + combination0(n - 1, r);
	}
	
	static long cnt = 0;
	static void combination1(int n, int r) {
		// 탈출 조건
		if (n == r || r == 0) { // 그냥 몇가지인지만 세면되니까 안되는 경우나 되는 경우 더 들어가지 않음
			cnt++;
			return;
		}

		// 고르고 안고르는 경우 2가지
		// check[idx] = true;
		combination1(n - 1, r - 1); // 고름
		// check[idx] = false;
		combination1(n - 1, r);
	}

	static int[] arr;
	static boolean[] check; 
	static void combination2(int r, int idx) {
		if (r == 0) {
			for (int i = 0; i < arr.length; i++) {
				if (check[i])
					System.out.print(arr[i] + " ");
			}
			System.out.println();
			cnt++;
			return;

		}
		for (int i = idx; i < arr.length; i++) {
			if (check[i])
				continue;
			check[i] = true;
			combination2(r - 1, i);
			check[i] = false;
		}

	}
	static int n = 4, r = 2; 
	static int[] save; //뽑는 r 만큼 사이즈
	
	//라이브강의 -- 이게 표준인듯
	static void combination3(int cnt, int start) {
		if(r == cnt) {
			System.out.println(Arrays.toString(save));
			return;	
		}
		for(int i = start; i< n; i++) {
			save[cnt] = i;
			combination3(cnt+1, i+1);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int n = Integer.parseInt(st.nextToken());
//		int r = Integer.parseInt(st.nextToken());
//		
//		BigInteger a = BigInteger.ONE;
//		BigInteger b = BigInteger.ONE;
//		
//		for(int i =0 ; i< r; i++) {
//			a = a.multiply(new BigInteger(String.valueOf(n-i)));
//			b = b.multiply(new BigInteger(String.valueOf(i+1)));
//		}
//		System.out.println(a.divide(b));
//		br.close();
		
		save = new int[r];
		combination3(0,0);
				
//		arr =new int[n];
//		check = new boolean[arr.length];
//		for(int i =  0; i< n; i++) {
//			arr[i] = i; 
//		}
		
//		System.out.println(combination0(n, r));
		
//		combination1(n, r);
//		System.out.println(cnt);
//
//		combination2(r, 0);
//		System.out.println(cnt);
		
		
	}

}
