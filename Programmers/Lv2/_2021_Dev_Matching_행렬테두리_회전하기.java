package Lv2;
import java.util.*;

public class _2021_Dev_Matching_행렬테두리_회전하기 {
	public static void main(String[] args) {
		int[][] queries = { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } };
		System.out.println(Arrays.toString(solution(6, 6, queries)));
//		int[][] queries = { { 1,1,2,2}, { 1,2,2,3}, {2,1,3,2},{2,2,3,3} };
//		System.out.println(Arrays.toString(solution(3, 3, queries)));
	}

	static int min;
	static int temp;

	private static int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		int[][] arr = new int[rows][columns];
		int idx = 1;
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				arr[i][j] = idx++;
		for (int i = 0; i < queries.length; i++) {
			int[] start = new int[] { queries[i][0] - 1, queries[i][1] - 1 };
			int[] end = new int[] { queries[i][2] - 1, queries[i][3] - 1 };
			// 돌리기
			temp = arr[start[0]][start[1]];
			min = Integer.MAX_VALUE;
			min = Math.min(temp, min);
			
			for (int j = start[1]; j < end[1]; j++)  // 오른쪽으로
				Swap(arr, start[0], j + 1);
			for (int j = start[0]; j < end[0]; j++)  // 아래로
				Swap(arr, j + 1, end[1]);
			for (int j = end[1]; j > start[1]; j--)  // 왼쪽으로
				Swap(arr, end[0], j - 1);
			for (int j = end[0]; j > start[0]; j--)  // 위로
				Swap(arr, j - 1, start[1]);
			answer[i] = min;
		}
		return answer;
	}

	private static void Swap(int[][] arr, int i, int j) {
		int swap = temp;
		temp = arr[i][j];
		min = Math.min(temp, min);
		arr[i][j] = swap;
	}
}
