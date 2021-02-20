package D3;
import java.util.Scanner;

public class 준환이의_운동관리 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int L, U, X;
		int T = sc.nextInt();
		
		for(int tc = 1; tc<= T;tc++) {
			L = sc.nextInt();
			U = sc.nextInt();
			X = sc.nextInt();
			
			int answer;
			if(X < L) { //최소이하라면
				answer = L - X; 
			}
			else if( X == L || X < U) {
				answer = 0;
			}
			else {
				answer = -1;
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
	
}
