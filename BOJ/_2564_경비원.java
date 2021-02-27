import java.io.*;
import java.util.*;

//경비원 시계방향이나 반시계방향으로 돌면서 최단거리 찾기
//알고리즘을 냅다 짜는 것보다 더 좋은 방법을 계속 찾아보자

//기준점을 생각하지 말고 (0,0)에서의 길이를 구한후
//둘레를 이용하고 직선으로 펼쳐서 '나'의 위치에서  min(시계방향, 둘레-시계방향)
public class _2564_경비원 {
	static StringBuilder sb = new StringBuilder();
	static int R,C,stores;
	static int[] dist; //(0,0)부터 어느 위치에 있는지 저장
	static int Min, sum;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int dulle = (R+C)*2;
		stores = Integer.parseInt(br.readLine());
		
		dist = new int[stores+1];
		for(int i =0; i< stores+1; i++) {
			st = new StringTokenizer(br.readLine());
			//북 1 남 2 서 3  동 4
			int dir = Integer.parseInt(st.nextToken());
			int loc = Integer.parseInt(st.nextToken());
			if(dir == 1) {
				//북쪽
				dist[i] = loc;
			}
			if(dir == 2) {
				//남쪽
				dist[i] = C+R+C-loc;
			}
			if(dir == 3) {
				//서쪽
				dist[i] = dulle -loc;
			}
			if(dir == 4) {
				//동쪽
				dist[i] = C + loc;
			}
		}
		//동근이는 맨 마지막에
		//일열로 놓기
		for(int x: dist)
		{
			System.out.println(x);
		}
		int ans = 0;
		for(int i =0 ; i<stores; i++) {
			int sum = Math.abs(dist[stores] - dist[i]); //실수!! sum이 아니라 ans갱신
			ans += Math.min(sum, dulle - Math.abs(dist[stores] - dist[i]));
		}
//		
		System.out.print(ans);
	}
}
