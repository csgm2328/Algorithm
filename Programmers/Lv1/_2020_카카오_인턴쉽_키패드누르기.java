package Lv1;
import java.util.*;

public class _2020_카카오_인턴쉽_키패드누르기 {
	public static void main(String[] args) {
	}

	private static String solution(int[] numbers, String hand) {
		// 4x3 배열에서 3,5,8,0은 현재 손가락 위치에서 가까운 거리계산
		// 같을 때는 손잡이 기준
		// 거리계산은 델타 이동거리
		int[][] arr = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { -1, 0, -2 } };
		String answer = "";
		int leftPos[] = new int[] { 3, 0 };
		int rightPos[] = new int[] { 3, 2 };
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == 1) {
				answer += "L";
				leftPos = new int[] { 0, 0 };
			} else if (numbers[i] == 4) {
				answer += "L";
				leftPos = new int[] { 1, 0 };
			} else if (numbers[i] == 7) {
				answer += "L";
				leftPos = new int[] { 2, 0 };
			} else if (numbers[i] == 3) {
				answer += "R";
				rightPos = new int[] { 0, 2 };
			} else if (numbers[i] == 6) {
				answer += "R";
				rightPos = new int[] { 1, 2 };
			} else if (numbers[i] == 9) {
				answer += "R";
				rightPos = new int[] { 2, 2 };
			} else { // 2,5,8,0
				if (numbers[i] == 2) { // 0,1
					int leftDist = Math.abs(0 - leftPos[0]) + Math.abs(1 - leftPos[1]);
					int rightDist = Math.abs(0 - rightPos[0]) + Math.abs(1 - rightPos[1]);
					if (leftDist < rightDist) {
						answer += "L";
						leftPos = new int[] { 0, 1 };
					} else if (leftDist == rightDist) {
						if (hand.equals("left")) {
							answer += "L";
							leftPos = new int[] { 0, 1 };
						} else {
							answer += "R";
							rightPos = new int[] { 0, 1 };
						}
					} else {
						answer += "R";
						rightPos = new int[] { 0, 1 };
					}
				} else if (numbers[i] == 5) { // 1,1
					int leftDist = Math.abs(1 - leftPos[0]) + Math.abs(1 - leftPos[1]);
					int rightDist = Math.abs(1 - rightPos[0]) + Math.abs(1 - rightPos[1]);
					if (leftDist < rightDist) {
						answer += "L";
						leftPos = new int[] { 1, 1 };
					} else if (leftDist == rightDist) {
						if (hand.equals("left")) {
							answer += "L";
							leftPos = new int[] { 1, 1 };
						} else {
							answer += "R";
							rightPos = new int[] { 1, 1 };
						}
					} else {
						answer += "R";
						rightPos = new int[] { 1, 1 };
					}
				} else if (numbers[i] == 8) { // 2,1
					int leftDist = Math.abs(2 - leftPos[0]) + Math.abs(1 - leftPos[1]);
					int rightDist = Math.abs(2 - rightPos[0]) + Math.abs(1 - rightPos[1]);
					if (leftDist < rightDist) {
						answer += "L";
						leftPos = new int[] { 2, 1 };
					} else if (leftDist == rightDist) {
						if (hand.equals("left")) {
							answer += "L";
							leftPos = new int[] { 2, 1 };
						} else {
							answer += "R";
							rightPos = new int[] { 2, 1 };
						}
					} else {
						answer += "R";
						rightPos = new int[] { 2, 1 };
					}
				} else if (numbers[i] == 0) { // 3,1
					int leftDist = Math.abs(3 - leftPos[0]) + Math.abs(1 - leftPos[1]);
					int rightDist = Math.abs(3 - rightPos[0]) + Math.abs(1 - rightPos[1]);
					if (leftDist < rightDist) {
						answer += "L";
						leftPos = new int[] { 3, 1 };
					} else if (leftDist == rightDist) {
						if (hand.equals("left")) {
							answer += "L";
							leftPos = new int[] { 3, 1 };
						} else {
							answer += "R";
							rightPos = new int[] { 3, 1 };
						}
					} else {
						answer += "R";
						rightPos = new int[] { 3, 1 };
					}
				}
			}
		}
		return answer;
	}
}
