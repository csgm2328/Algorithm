import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//트리구조에서 가장 큰 경로 찾는 DP
//1차원 배열로 풀면 332-130ms
//2차원 리스트로 동적 객체 생성과 검색이 느린듯
public class _1932_정수_삼각형 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static List<List<Integer>> dp = new ArrayList<List<Integer>>();
	static int MAX = -1; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// global init
		N = Integer.parseInt(br.readLine());
		
		//첫번째, 두번쨰는 바로
		StringTokenizer st = new StringTokenizer(br.readLine());
		dp.add(new ArrayList<>());
		dp.get(0).add(Integer.parseInt(st.nextToken()));
		
		st = new StringTokenizer(br.readLine());
		dp.add(new ArrayList<>());
		dp.get(1).add(dp.get(0).get(0) + Integer.parseInt(st.nextToken()));
		dp.get(1).add(dp.get(0).get(0) + Integer.parseInt(st.nextToken()));
		
		//3번쨰 부터 입력받으면서 dp에 저장
		for(int i =2; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[i+1];
			
			for(int j =0 ; j<= i; j++) {
				int x = Integer.parseInt(st.nextToken());
				dp.add(new ArrayList<>());
				if(j-1 < 0) { //맨 왼쪽 노드일때
					dp.get(i).add(dp.get(i-1).get(0) + x);
				}
				else if(j == i) { //맨 오른쪽 노드일때
					dp.get(i).add(dp.get(i-1).get(i-1) + x);
				}
				else { //그 외 일때는 두 부모중에 큰걸 선택해서 저장한다
					int a = dp.get(i-1).get(j-1);
					int b = dp.get(i-1).get(j);
					int max = Math.max(a, b);
					dp.get(i).add(max + x);
				}
				//MAX값도 저장
				if(MAX < dp.get(i).get(j))
					MAX = dp.get(i).get(j);
			}
		}
		System.out.print(MAX);
	}
}
