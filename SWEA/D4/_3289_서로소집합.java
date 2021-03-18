package D4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class _3289_서로소집합 {
	static int T,N,M;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M =Integer.parseInt(st.nextToken());
			
			arr = new int[N+1]; //최대 백만
			for(int i =0; i<= N; i++)
				arr[i] = i; //make_set()
				
			sb.append("#" + tc + " ");
			for(int i =0; i< M; i++) {
				st = new StringTokenizer(br.readLine());
				int menu = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				
				switch (menu) {
				case 0: //union
					union(a,b);
					break;
				case 1: //find a,b가 같은집합인지
					sb.append(check(a,b));
					break;

				default:
					break;
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	private static int check(int a, int b) { //union이랑 같은 조건을 검사하지만 실제로 바꾸지는 않아야함
		int a_p = find(a);
		int b_p = find(b);
		if( a_p == b_p)
			return 1;
		return 0;
	}
	private static int find(int a) {
		if(arr[a] == a) //찾는 게 그 집합의 부모이면 바로 리턴
			return a;
		else return arr[a] = find(arr[a]); //아닐 때는 부모까지 찾아나서면서 경로를 압축한다
	}
	private static void union(int a, int b) {
		//이렇게 하면 연결구조 파악을 못하지 바로위 루트를 보는게 아니니
//		if(arr[a] == arr[b]) //부모가 같으면 끝
//			return;
//		else //다르면 b의 부모를 a의 부모로 넣음
//			arr[b] = arr[a];
		int a_p = find(a);
		int b_p = find(b);
		if(a_p == b_p)
			return;
		else 
			arr[b_p] = arr[a_p];
	}
}