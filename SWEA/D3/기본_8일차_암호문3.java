package D3;
import java.util.ArrayList;
import java.util.Scanner;

//삽입 삭제 추가엔 Arraylist
public class 기본_8일차_암호문3 {
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Integer> origin;
	
	static void insert() {
		int x = sc.nextInt();
		int y = sc.nextInt();
		for(int i = 0; i< y; i++) {
			origin.add(x+i,sc.nextInt());
		}
	}
	static void delete() {
		int x = sc.nextInt();
		int y = sc.nextInt();
		for(int i = 0; i< y;i++) {
			origin.remove(x); // 알아서 당겨짐
		}
	}
	static void append() {
		int y = sc.nextInt();
		for(int i =0; i< y; i++) {
			origin.add(sc.nextInt());
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
				char cmd= sc.next().charAt(0);
				switch(cmd) {
				case 'I': // x부터 y개  s 추가
					insert(); break;
				case 'D': // x, y
					delete(); break;
				case 'A': //y,s
					append(); break;
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
