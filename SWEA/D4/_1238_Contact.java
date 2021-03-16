package D4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//컨택트
//유향그래프
//가장 늦게 받는 중 번ㅇ호 큰사람은?
//최대인원 100명
public class _1238_Contact {
	static int T, length, start;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = 10;
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			length = Integer.parseInt(st.nextToken());
			start =Integer.parseInt(st.nextToken());
			
			//제발 전역변수 초기화좀
			List<List<Integer>> arr = new ArrayList<List<Integer>>();
			boolean[] visited = new boolean[101]; //인원 100명
			for(int i =0; i<= 100; i++) 
				arr.add(new ArrayList<>());
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < length/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				boolean in = false; //from to 중복검사
				for(int j = 0; j < arr.get(from).size();  j++) {
					if(arr.get(from).get(j) == to) {
						in = true;
						break;
					}
				}
				if(!in)
					arr.get(from).add(to);
			}
			
			Queue<int[]> q = new LinkedList<int[]>(); //번호, cnt
			visited[start] = true;
			q.offer(new int[] {start, 0});
			
			//100명의 리스트에
			//중복되는 입력이 들어오는건 어떻게 체크하나
			//연결할 수 있는 리스트를 리스트로 표현한다
			//bfs로 돌아야하니까
			//시작하면 해당 번호의 리스트에 연결된 거를 큐에 넣고
			//큐 안을 검사할때 cnt를 저장한다 (크거나 같으면(가장 큰 번호))
			int[] max = {-1,-1}; //번호, 연락걸린시간
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				if(max[1] < cur[1]) { //max기록 연락순위는 늦거나 같고 번호는 클때
					max[0] = cur[0];
					max[1] = cur[1];
				}
				else if(max[1] == cur[1] && max[0] < cur[0]) {
					max[0] = cur[0];
					max[1] = cur[1];
				}
				
				for (int i =0; i< arr.get(cur[0]).size(); i++) { //그 번호에 연결된 번호만큼
					int next = arr.get(cur[0]).get(i);
					if (!visited[next]) { 
							visited[next] = true; //다음 방문처리
							q.offer(new int[] {next, cur[1] + 1});
						}
					} // end for list
			} // end while
			sb.append("#" + tc + " " + max[0] + "\n");
		}
		System.out.println(sb.toString());
	}
}