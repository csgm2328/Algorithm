import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//4h
//Z
//R,C가 4일때
//2로 나눌 수 있으면
//나누고 (0,0) 부터 (0,0+4/2), (0+2,0), (2,2)에서 시작한다
//방향은 딱 두가지
//근데 범위체크가 시작점에서 R+2,c+2이다

public class _1074_Z {
	static StringBuilder sb = new StringBuilder();
	static int N, r, c;
	static char[][] arr;
	static long cnt; // 재귀가 방문한 횟수

	static int[] dr = { 0, 1, 1 };
	static int[] dc = { 1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// global init
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		int size = (int) Math.pow(2, N);
		Z(size, 0, 0);
	}
	
//2의 15승이라 배열로 체크할 수가 없다.
//그래서 어차피 해당 size를 4분할로 쪼갠 곳을 4갈래의 재귀로 돌거니까
//경계 체크없이
//cnt로 방문 횟수만 체크만 하면 된다
//그래서
//size에 대한 4분할 재귀를 돌건데
//각 사분면을 size/2로 크기를 줄이면서 재귀를 돌게끔하여
//자연스러운 분할을 한다

//근데 시간초과
//메모리(512MB)를 많이쓰면서 시간을 줄여야하는데
//그냥 연산 줄이기 위해서 사분면들어가기 전에 시작범위와 끝범위에 목표지점이 존재하는지 검사

	private static void Z(int size, int cu_r, int cu_c) {
		if (cu_r == r && cu_c == c) { // 목표지점 도달
			System.out.print(cnt);
			System.exit(0);
		}
		//시작 ~ 끝에 목표지점이 있는지
		if (cu_r + size / 2 > r && cu_c + size / 2 > c && cu_r <= r && cu_c <= c) 
			Z(size / 2, cu_r, cu_c); // 현재
		else
			cnt += size/2 * size/2; // 싹 다 방문처리
		
		if (cu_r + size / 2 > r && cu_c + size > c && cu_r <= r && cu_c + size/2 <= c)
			Z(size / 2, cu_r, cu_c + size / 2); // 오른쪽
		else
			cnt += size/2 * size/2;
		
		if (cu_r + size > r && cu_c + size / 2 > c && cu_r + size / 2 <= r && cu_c <= c)
			Z(size / 2, cu_r + size / 2, cu_c); // 아래
		else
			cnt += size/2 * size/2;
		
		if (cu_r + size > r && cu_c + size > c && cu_r+ size/2 <= r && cu_c+size/2 <= c)
			Z(size / 2, cu_r + size / 2, cu_c + size / 2); // 대각선
		else
			cnt += size/2 * size/2;
	}
}
