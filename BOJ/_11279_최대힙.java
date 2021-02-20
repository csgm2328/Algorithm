
import java.io.*;
import java.util.*;

public class _11279_최대힙 {
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		
		});
		for(int i = 0 ; i< N;  i++) {
			int x = Integer.parseInt(br.readLine());
			if(x == 0) {
				if(pq.isEmpty())
					sb.append(0+ "\n");
				else 
					sb.append(pq.poll()+ "\n");
			}
			else {
				pq.offer(x);
			}
		}
		System.out.print(sb.toString());
	}
}
