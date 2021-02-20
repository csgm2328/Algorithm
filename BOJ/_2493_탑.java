
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


//배열로 풀려면 작은타워를 만나면 바로 그거 보다 큰 타워 인덱스로 뛰어야함
//그러면 -133ms


public class _2493_탑 {
	static class Pair { //default 선언은 패키지 내 접근 가능해서 스태틱으로 선언해야함
		int val;
		int idx;

		public int first() {
			return val;
		}
		public int second() {
			return idx;
		}
		public Pair(int val, int idx) {
			this.val = val;
			this.idx = idx;
		}
	}
	static int N, height;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Pair> tower = new Stack<Pair>();

		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			height = Integer.parseInt(st.nextToken());
			while (true) {
				if (tower.isEmpty()) {
					sb.append(0 + " ");
					tower.push(new Pair(height, i+1));	 // 현재 타워도 추가
					break;
				}
				else if (height < tower.peek().first()) { // 앞에있는 타워가 더 높으면
					sb.append(tower.peek().second() + " "); // 그 인덱스 기록
					tower.push(new Pair(height, i+1));
					break;
				} 
				else // 낮으면
					tower.pop();
			}
		}
		System.out.println(sb.toString());
		br.close(); //넣어주니까 오히려 +16ms
	}
}
