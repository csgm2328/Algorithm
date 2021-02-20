package D3;
import java.util.Scanner;

//60h
public class 기본_4일차_거듭제곱_재귀 {
	 static Scanner sc = new Scanner(System.in);
	 static int x; //고정해야한다는 생각을 못했다
	 static int e;
	 
	 static int powerset(int y,int e) {
		 //탈출 조건
		if ( e >= 2) {
			return powerset(y*x, e-1); //거듭 제곱
		}
		else {
			return y;
		}
	 }
	 
	 public static void main(String[] args) {
		for(int tc = 1; tc <= 10; tc++) {
			int T = sc.nextInt();
			x = sc.nextInt();
			e =  sc.nextInt();
			System.out.format("#%d %d\n", tc,powerset(x, e)); 
		}
	}
}
