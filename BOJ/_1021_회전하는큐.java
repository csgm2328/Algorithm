import java.io.*;
import java.util.*;

//왤케 어렵게 푸는걸까?
//현재 위치랑 원래 위치를 저장할 필요가 있을까?
//큐를 탐색하는 방법을 몰라서 그랬지만 이터레이터를 사용하면 된다.
//현재위치를 찾기위해서 현재위치를 저장하고 다니는게 아니라
//그냥 index++하면서 큐를 돌다가 input과 똑같은 걸 발견하면 끝내면 된다.

public class _1021_회전하는큐 {
	static int N, M;
	static Deque<Pair> dq;
	static StringTokenizer st;

	static class Pair { // default 선언은 패키지 내 접근 가능해서 스태틱으로 선언해야함
		int origin_loc;
		int current_loc;

		public int origin() {
			return origin_loc;
		}

		public int current() {
			return current_loc;
		}

		public Pair(int origin_loc, int current_loc) {
			this.origin_loc = origin_loc;
			this.current_loc = current_loc;
		}
	}

	static int update_index(int origin_loc) { // 현재 위치갱신하면서 타겟의 현재위치찾기
		int target_loc = 1;
		for (int i = 0; i < dq.size(); i++) {
			if (origin_loc == dq.peekFirst().origin()) {
				target_loc = i + 1;
			}
			Pair p = dq.removeFirst();
			p.current_loc = i + 1;
			dq.addLast(p);
		}
		return target_loc;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dq = new ArrayDeque<>();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			dq.add(new Pair(i + 1, i + 1));
		}

		st = new StringTokenizer(br.readLine());
		int impl = 0; // 동작 수
		for (int i = 0; i < M; i++) {
			int origin_loc = Integer.parseInt(st.nextToken());
//			int target_loc = origin_loc; //타겟 위치는 위치계산용
//			while(dq.peekFirst().origin() != origin_loc) {
//				target_loc = update_index(origin_loc); //타겟의 위치 게산
//				//당기고 미는 동작 실행
//				if(target_loc <= (dq.size()+1)/2) {  //원하는 위치가 반절이하에 있으면
//					dq.addLast(dq.removeFirst()); //뒤로보내기
//					target_loc = update_index(origin_loc);
//				}
//				else { 
//					dq.addFirst(dq.removeLast()); //앞으로당기기
//					target_loc = update_index(origin_loc);
//				}
//				impl++;
//			}
//			dq.removeFirst(); //그리고 앞에꺼 삭제
			
			//-8ms
			while (true) {
				int idx = 1;
				Iterator<Pair> it = dq.iterator();
				while (it.hasNext()) {
					if (it.next().origin() == origin_loc)
						break;
					idx++;
				}
				if (idx == 1) {
					dq.pollFirst();
					break;
				} 
				else {
					if (idx > (dq.size() + 1) / 2)
						dq.offerFirst(dq.pollLast());
					else
						dq.offerLast(dq.pollFirst());
					impl++;
				}
			}
		}
		System.out.print(impl);
	}
}
