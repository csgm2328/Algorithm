import java.io.*;
import java.util.*;

//Pair에 저장하는 건 아마 시간이 얼마 안걸릴 것
//하지만 정렬할때 두배의 시간이 소요된다?
//배열로 입력받으면서 정렬되게 하면  1520 - 750ms
public class _1946_신입사원 {
	static int T, N;
	
	static StringBuilder sb = new StringBuilder();
	static class Pair implements Comparable<Pair>{ //default 선언은 패키지 내 접근 가능해서 스태틱으로 선언해야함
		int doc;
		int meet;

		public int first() {
			return doc;
		}
		public int second() {
			return meet;
		}
		public Pair(int val, int idx) {
			this.doc = val;
			this.meet = idx;
		}
		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return this.doc - o.doc;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			List<Pair> rank = new ArrayList<Pair>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int doc = Integer.parseInt(st.nextToken()); //점수가 아니라 순위였네 하..
				int meet = Integer.parseInt(st.nextToken());
				rank.add(new Pair(doc , meet ));
			}
			Collections.sort(rank);
			int cnt =0;
			int min = rank.get(0).meet;
			for (int i = 1; i < N; i++) {
				if(min < rank.get(i).meet)
					cnt++;
				else if(min > rank.get(i).meet) //더 작은게 나타나면 갱신
					min = rank.get(i).meet;
			}
			sb.append((N-cnt) + "\n");
		}
		System.out.print(sb.toString());

	}
}
