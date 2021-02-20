import java.util.Scanner;

public class _2839_설탕_배달 {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int num = sc.nextInt();
		//3a + 5b = 18
		//min(a+b) = ?
		int min = 9999; //5000까지
		for(int i = 0 ; i<= num/3; i++) {
			for(int j =0; j<= num/5; j++) {
				if( num == 3*i + 5*j)
					if( min > i+j) {
						min  = i+j;
					}
			}
		}
		if(min != 1001) System.out.println(min);
		else System.out.println(-1);
		
		
		//더 효율적이게 5를 최대한 사용하는 것부터 검사
		int result = -1;
		for(int i =num/5; i>=0; i--) {
			int j = num -i*5;
			if(j %3 == 0 ) {
				result = j/3+i;
				break;
			}
		}
		System.out.println(result);
	}
}
