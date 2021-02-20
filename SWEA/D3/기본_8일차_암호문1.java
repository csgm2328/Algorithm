package D3;
import java.util.ArrayList;
import java.util.Scanner;

//삽입 삭제 추가엔 Arraylist
public class 기본_8일차_암호문1 {
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Integer> origin;
	
	static void insert() {
		int x = sc.nextInt();
		int y = sc.nextInt();
		for(int i = 0; i< y; i++) {
			origin.add(x+i,sc.nextInt());
		}
	}
	public static void main(String[] args) {
		for(int tc = 1; tc<= 10;tc++) {
			int origin_cnt = sc.nextInt();
			origin = new ArrayList<Integer>();
			
			for(int i =0; i< origin_cnt ; i++)
				origin.add(sc.nextInt());
			
			int cmd_cnt = sc.nextInt();
			for(int i =0; i< cmd_cnt; i++) {
				char cmd= sc.next().charAt(0); //분기문 이랑 함수 콜 100 + 78ms 너무심한거아님?
				switch(cmd) {
				case 'I': // x부터 y개  s 추가
					insert(); break;
				}
			}
			System.out.format("#%d",tc);
			for(int i =0; i< 10;i++) {
				System.out.print(" " + origin.get(i));
			}
			System.out.println();
		}
	}
}
