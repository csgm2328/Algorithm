package D3;
import java.util.Scanner;

public class 미니멀리즘_시계 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc =1; tc<= T; tc++) {
			int angle = sc.nextInt();
			System.out.println("#" + tc + " " +angle/30 +" " + angle%30 *2);
		}	
	}
}
