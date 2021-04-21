package D4;
import java.io.*;
import java.util.*;

//원점으로 집합
// 등차로 늘어나는 움직이는 거리로 만들 수 있는 조합중에
// 최대거리가 몇번 째 거리조합에 들어가는지 확인한다
// 거리가 홀짝이냐에 따라 거리 조합도 홀 짝이다
// 조합 안에 찾는게 포함되는지를 간단하게 찾을 수 있는 방법
public class _8458_원점으로_집합 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			boolean check = true;
			int precheck = 0;
			int max = -1;
			for(int i = 0; i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				//거리계산하다가 홀 짝이 안맞으면 체크해서 -1
				int dis = Math.abs(0 - r) + Math.abs(0 - c);
				if(i ==0)
					precheck = dis % 2;
				else {
					if(dis % 2 != precheck)
						check = false; //홀 짝 이 다른 거리가 나오면 같은 그룹에서 나올수 있는 경우가 아님
				}
				max = Math.max(max, dis);
			}
			if(!check) {
				sb.append("#" + tc + " " + -1 + "\n");
				continue;
			}
			int i =0,sum =0;
			while(true) {
				if(max <= sum && (sum - max) %2 == 0)
					break;
				i++;
				sum +=i; 
			}
			sb.append("#" + tc + " " + i + "\n");
		}
		System.out.println(sb.toString());
	}
}
