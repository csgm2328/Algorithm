import java.io.*;
import java.util.*;

public class _1931_회의실_배정 {
	static int N, min = Integer.MAX_VALUE;
	static boolean[] check;
	static List<Pair> arr = new ArrayList<Pair>();

	static class Pair implements Comparable<Pair>{
		int start;
		int end;

		public Pair(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Pair o) {
			//끝나는 순으로 정렬
			int diff = this.end - o.end;
			return diff != 0 ? diff: this.start - o.start;
		}
//		@Override
//		public int compare(Pair o1, Pair o2) {
//			//끝나는 순으로 정렬
//			int diff = o1.end - o2.end;
//			return diff != 0 ? diff: o1.start - o2.start;
//		}
	}


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bw.readLine());
		int open =Integer.MAX_VALUE, close =-1;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bw.readLine());
			int start = Integer.parseInt(st.nextToken());
			//start 중복체크해서 end 이른것만 남게 하고 싶은데	
			if(open > start) open = start;
			int end = Integer.parseInt(st.nextToken());
			if(close < end) close = end;
			arr.add(new Pair(start, end));
		}
		//open ~ close
//		Collections.sort(arr, new Comparator<Pair>() {
//
//			@Override
//			public int compare(Pair o1, Pair o2) {
//				//끝나는 순으로 정렬
//				int diff = o1.end - o2.end;
//				return diff != 0 ? diff: o1.start - o2.start;
//			}
//			
//		});
		Collections.sort(arr);
		int idx = 0;
		int cnt =0;
		while(idx < N) {
			if(open > arr.get(idx).start) { //현재 시작시간이 지금 꺼낸 회의의 시작시간 보다 크다면 패스(탐욕법)
				idx++;	//전꺼를 검증하지않는다
				continue;
			}
			open = arr.get(idx++).end;
			cnt ++;
		}
		System.out.print(cnt);
	}
}
