import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//java 11 1위
//마찬가지로 큐로 풀리는 이것도 큐칙성이 있다.
//1 2 3 4 5 6
//홀수 위치가 계속 버려진다
//그 다음으로 2의 제곱수만 남는다

//출력값으로 찾은 규칙
//1 2
//2 4
//2 4 6 8 
//2 4 6 8 10 12 14 16
//만약에 15이면 15보다 작고 가장 가까운 2의 승수인 8로 나눈 나머지 7에 곱하기 2 하면 답
//안나누고 그냥 빼면되잖아?

//여기서 더 최적화하면
//가깝지만 작은게 아니라 더 큰 수를 찾고
//그 수에서 N의 두배를 빼면 끝이다
//9*2 - 16 = 2
//5*2 - 8 = 2

public class _2164_카드2_최적화 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, powTwo = 1;
		N = Integer.parseInt(br.readLine());
		
		while (powTwo < N) //가장 가까운 2의 거듭제곱 수 찾기
			powTwo <<= 1;
		
		if(powTwo == N) 
			System.out.print(N);	//printf = print + 12ms
		else
			System.out.print((N<<1) - powTwo); // N * 2 - 찾아낸 2의 거듭제곱수
			//println = print + 4ms
		br.close();
	}
}
