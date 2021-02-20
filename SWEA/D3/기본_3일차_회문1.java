package D3;
import java.util.Scanner;

public class 기본_3일차_회문1 {
	static Scanner sc = new Scanner(System.in);
	static int N;
	static char[][] arr = new char[8][8];
	
	static boolean check_row(int x, int y) {
		boolean flag = true;
		
		for(int i = 0 ; i < N/2;i++) {
			if(arr[x][y+i] != arr[x][(y+N-1)-i]) {// 다르면
				flag = false; 
				break;
			}
		}
		return flag;
	}
	
	static boolean check_col(int x, int y) {
		boolean flag = true;
		
		for(int i = 0 ; i < N/2;i++) {
			if(arr[x+i][y] != arr[x+N-1-i][y]) {// 다르면
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public static void main(String[] args) {
		for(int tc = 1; tc <=10; tc++) {
			N = sc.nextInt();
			for(int i = 0; i< 8;i++) { 
				String s = sc.next();
				for(int j = 0; j<8;j++)  //-->
					arr[i][j] = s.charAt(j);
			}
			
			int cnt = 0;
			for(int i = 0; i< 8;i++){ 
				for(int j = 0; j<=8-N;j++) { //-->
					//N자리씩 검색
					if(check_row(i,j)) cnt++;
				}
			}
			
			for(int i = 0; i< 8;i++)
				for(int j = 0; j<=8-N;j++)	//밑으로
					//N자리씩 검색
					if(check_col(j,i)) cnt++;
			System.out.println("#" + tc + " " + cnt);
		}
	}
}
