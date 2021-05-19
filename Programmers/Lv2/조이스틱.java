package Lv2;
//탐욕법 : 순간순간 최적선택하면 끝
// 1. A에서 정방향 거리 역방향거리 중에 작은 거 
// 2. 그 후 A가아닌게 나타나는 역방향 정방향 중에 가까운거
class 조이스틱 {
	public static void main(String[] args) {
		System.out.println(solution("ZZAAAZZ"));
	}

	public static int solution(String name) {
		answer = 0;
		StringBuilder origin = new StringBuilder();
		for (int i = 0; i < name.length(); i++)
			origin.append("A");
		int cursor = 0;
		while (!name.equals(origin.toString())) {
			if (name.charAt(cursor) == origin.charAt(cursor)) { // 커서있는 곳이 같다면
				int next = nextCursor(cursor, name, origin.toString());
				if (cursor == next)
					break;
				cursor = next;
				continue;
			}
			char a = origin.charAt(cursor);
			char b = name.charAt(cursor);
			int dis = b - a; // 알파벳 거리
			System.out.println(dis);
			int re_dis = 26 - dis; // 반대 거리
			System.out.println(re_dis);
			if (dis < re_dis)
				answer += dis;
			else
				answer += re_dis;
			origin.setCharAt(cursor, b); // 반영
			// 이제 다음거 찾기
			int next = nextCursor(cursor, name, origin.toString());
			if (cursor == next)
				break;
			cursor = next;
		}
		return answer;
	}

	static int answer;

	private static int nextCursor(int cursor, String name, String origin) {
		boolean reverse = false;
		int idx = cursor + 1; // 정방향으로가다가
		int forward = 0;
		// 정방향
		while (true) {
			if (idx == cursor) // cursor로 돌아오면 바꿀거 없음
				return cursor;
			if (idx == name.length()) { // 끝에 넘어서도 가면
				idx = 0;
				reverse = true; // 역방향으로 찾은거
			}
			if (name.charAt(idx) != origin.charAt(idx)) { // 바꿔야할거 발견
				forward = Math.abs(idx - cursor);
				if (reverse)
					forward = name.length() - forward;

				break;
			}
			idx++;
		}
		// 역방향
		int backidx = cursor - 1;
		int backward = 0;
		reverse = false;
		while (true) {
			if (backidx == -1) { // 끝에 넘어서도 가면
				backidx = name.length() - 1;
				reverse = true; // 역방향으로 찾은거
			}
			if (name.charAt(backidx) != origin.charAt(backidx)) { // 바꿔야할거 발견
				backward = Math.abs(backidx - cursor);
				if (reverse)
					backward = name.length() - backward;
				break;
			}
			backidx--;
		}
		// 반례: forward backward 같을때 한쪽을 다채우고 넘어가야 최단
		if (forward <= backward) {
			answer += forward;
			return idx;
		} else {
			answer += backward;
			return backidx;
		}

	}
}