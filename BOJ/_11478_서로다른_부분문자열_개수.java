import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

//문자열의 부분 집합중에서 공통된 부분을 구한다.
//부분집합을 구해서 전부다 중복을 제거하는 해시로 저장한다. 

//2000ms 근데 이걸 substring을 저장한 후에
//정렬해서 그 다음의 부분문자열이랑 같은 구간이 몇개인지 세서
//빼주면 무려 80ms임

public class _11478_서로다른_부분문자열_개수 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringBuilder s = new StringBuilder();
	static HashSet<String> hs = new HashSet<>();
	static boolean[] visited;
	static int size;

	public static void main(String[] args) throws IOException {
		s.append(input.readLine());
		size = s.length();
		visited = new boolean[size];
		
		partial();
		System.out.println(hs.size());
	}

	private static void partial() {
		// 결과 저장하는데 부분집합과 부분문자열은 차이가 있다
		// 부분집합은 문자열의 중간을 빠뜨리는 경우도 있음
		// 그리고 사이즈가 최대 1000이라 2^1000이라 안됨
		// 그냥 슬라이딩으로 
		for (int i = 0; i < size; i++) {  // 1 ~ 사이즈만큼의 부분문자열 만들기
			for(int j =0; j< size-i; j++) {	//각 사이즈의 시작점
				StringBuilder temp =new StringBuilder();
				for(int k =j; k <= j+i;k++) {	//시작점에서 길이만큼 반복
				//길이 1의 부분문자열 0~사이즈
				//길이 2: 0,1  1,2, 2,3, 3,4
				//길이 3; 0,1,2 1,2,3
				temp.append(s.charAt(k));
				}
				hs.add(temp.toString());
			}
		}//end for 
	}
}
