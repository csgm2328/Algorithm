import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//한나
class Wheel {	// 톱니바퀴
	int topIndex = 0;
	int[] pole = new int[8];
	void spin(int direction) {
		topIndex += direction * -1;
		if (topIndex < 0) {
			topIndex += 8;
		} else if (topIndex > 7) {
			topIndex -= 8;
		}
	}
	int getTopPole() {
		return pole[topIndex];
	}
	int getLeftPole() {
		return pole[(topIndex + 6) % 8];
	}
	int getRightPole() {
		return pole[(topIndex + 2) % 8];
	}
}

public class _14891_톱니바퀴_참고 {
	static Wheel[] wheel = new Wheel[5];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int spinNum;	// 회전횟수
		int score = 0;	// 점수

		// 톱니정보 입력받기
		for (int i = 1; i <= 4; i++) {
			st = new StringTokenizer(br.readLine());
			wheel[i] = new Wheel();
			String poleList = st.nextToken();
			for (int j = 0; j < 8; j++) {
				wheel[i].pole[j] = Integer.parseInt(poleList.substring(j, j+1));
			}
		}

		st = new StringTokenizer(br.readLine());
		spinNum = Integer.parseInt(st.nextToken());	// 회전획수 입력받기

		// 톱니 회전시키기
		for (int i = 1; i <= spinNum; i++) {
			st = new StringTokenizer(br.readLine());
			int wheelIndex = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			spin(wheelIndex, direction);
		}

		// 점수계산
		for (int i = 1; i <= 4; i++) {
			score += wheel[i].getTopPole() * Math.pow(2, i-1);
		}
		System.out.println(score);
	}

	private static void spin(int index, int direction) {
		spinLeftSide(index-1, direction * -1);
		spinRightSide(index+1, direction * -1);
		wheel[index].spin(direction);
	}

	private static void spinLeftSide(int index, int direction) {
		if (index < 1 || index+1 > 4) return;
		if (wheel[index].getRightPole() != wheel[index+1].getLeftPole()) {
			spinLeftSide(index-1, direction * -1);
			wheel[index].spin(direction);
		}
	}

	private static void spinRightSide(int index, int direction) {
		if (index > 4 || index-1 < 1) return;
		if (wheel[index-1].getRightPole() != wheel[index].getLeftPole()) {
			spinRightSide(index+1, direction * -1);
			wheel[index].spin(direction);
		}
	}
}
