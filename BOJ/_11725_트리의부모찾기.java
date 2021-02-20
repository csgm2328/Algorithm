

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//2차 연결리스트로 루트부터 연결된 순으로 내려가면서 부모없는 것 갱신
public class _11725_트리의부모찾기 {
	static StringBuilder sb = new StringBuilder();  
	static List<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
	static int[] save;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		//2차원 리스트 내부 객체 생성
		for(int i =0; i<=N; i++) { //N까지 접근할 거니까
			arr.add(new ArrayList<Integer>());
		}
		
		for(int i = 0; i< N-1;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr.get(x).add(y);
			arr.get(y).add(x);
		}
		
		//부모 결과 저장할 배열
		save = new int[N+1];
		BFS(1); //root부터시작
		for(int i = 2; i<= N; i++)
			sb.append(save[i]+"\n");
		System.out.println(sb);
	}
	static void BFS(int start) {
		save[start] = -1; //시작에는 -1
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start); //루트 큐에 넣고
		
		while(!q.isEmpty()) {
			int parent = q.poll(); // 부모를 빼면서 시작
			for(int link : arr.get(parent)) { //연결된 거 다 체크
				if(save[link] == 0) {	//부모가 없으면
					q.offer(link);
					save[link] = parent;	//현재를 다 부모라고 저장
				}
			}
		}
	}
}
