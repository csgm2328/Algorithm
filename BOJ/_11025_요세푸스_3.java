
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//10h
//마지막 수 찾기 N <= 500만
//점화식 O(KlogN)
// f(x,k) = ((f(n-1,k) + k) % n) , f(1,k) = 0
// 재귀는 메모리 초과 C++은 되는거 같음

//(7, 3) = 1 2 X 4 5 X 7
// 7 % 3 = 1
// 1칸씩당김
// (7 - 7/3, 3) = 2 3 X 4 5 X 1
// (5, 3) 의 답 4라고 알고 있을 때
// (7, 3) 의 답도 같은 평행한 위치인 4임
// 이 평행 규칙이 공식으로 설명됨

public class _11025_요세푸스_3 {
	static int N, K;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		//index 0 부터 시작
		//이전꺼의 답을 알때 K만큼 뒤로가면서 현재 길이안에서 원을 돌게 하면 답이다
		//이전꺼의 답 0 일때
		//+ K만큼 돌고
		//원이니까 % 현재 길이
		//다음 답의 인덱스 위치 나옴
		
		int result = 0;
		for(int i = 1; i<= N; i++) {
			result = (result + K) % i;
		}
		System.out.println(result +1); 
	}
}
