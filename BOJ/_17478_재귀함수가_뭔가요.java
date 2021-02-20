import java.util.Scanner;

public class _17478_재귀함수가_뭔가요 {
	//\r 쓰면 출력형식 오류남
	static String under = "____";
	static String A = "\"재귀함수가 뭔가요?\"\n";
	static String[] text = { "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n",
			"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n",
			"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n" };
	static String B = "라고 답변하였지.\n";
	static String C = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
	static int N;

	static void powerset(int cnt) {
		String prefix = "";
		for (int i = 0; i < cnt; i++) {
			prefix += under; 
		}
		
		System.out.print(prefix + A);
		
		if (cnt == N) { //탈출조건
			System.out.print(prefix + C);
			System.out.print(prefix + B);
			return;
		} 
		
		else 
			for (int j = 0; j < 3; j++) 
				System.out.print(prefix +text[j]);
		
		powerset(cnt + 1);
		System.out.print(prefix+B);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		powerset(0);
//		String[] x = { "1", "2" };
//		System.out.print(x[0]);

	}
}
