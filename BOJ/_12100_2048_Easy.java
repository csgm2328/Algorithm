import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * 2048(Easy)
 * list를 사용해서 블록 합친 결과를 맵에 옮긴다
 * 5번의 최대니까 블록깨기랑 마찬가지로 중복순열의 결과를 사용한다
 * int를 가지는 list.get()끼리 == 연산이 안되서 1시간 디버깅함
 * -128 ~ 127 까지는 메모리에 캐시되서 상수로 관리되지만 그 이상은 Integer 객체로 관리된다
 */
public class _12100_2048_Easy {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;

	public static void main(String[] args) throws IOException {
		// global init
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		permutation(0, 5, arr);
		System.out.println(MAX_BLOCK);
	}

	static int MAX_BLOCK = -1;

	private static void permutation(int idx, int r, int[][] map) {
		// 5번 이동해본 것중 최대
		if (idx == r) {
			// 가장 큰 블록 최대값 검사
			MAX_BLOCK = Math.max(getMaxBlock(map), MAX_BLOCK);
			return;
		}
		for (int dir = 0; dir < 4; dir++) { // 상하좌우
			int[][] copy = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					copy[i][j] = map[i][j];
			permutation(idx + 1, r, MergeBlock(copy, dir));
		}
	}

	private static int getMaxBlock(int[][] map) {
		int max = -1;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				if (max < map[i][j])
					max = map[i][j];
			}
		return max;
	}

	private static int[][] MergeBlock(int[][] copy, int dir) {
		List<Integer> list = new ArrayList<Integer>();
		// dir에 따라서 넣는 게 다름
		// 이미 합쳐진 블락은 또 안합쳐짐
		switch (dir) {
		case 0: // 상
			for (int j = 0; j < N; j++) {
				list.clear(); // 비교할 줄마다 CLEAR
				for (int i = 0; i < N; i++) {// 위부터 보면서 차례대로 넣음
					if (copy[i][j] == 0)
						continue;
					list.add(copy[i][j]);
					copy[i][j] = 0;
				}
				int idx = 0;
				if (list.size() == 1) { // 리스트에 하나밖에 없을떄
					copy[idx][j] = list.get(0);
					continue;
				}
				
				for (int x = 0, y = 1; y < list.size();) { // 하나이상이면 비교
//					if(list.get(x) == list.get(y)) {
						if (list.get(x).equals(list.get(y))) { // 디버깅 : == 비교가 안된다?????????
						copy[idx++][j] = list.get(y) + list.get(x);
						x += 2;
						y += 2; // 합쳐졌으면 다음에 그 합쳐진 블록 안보도록
					} else {
						copy[idx++][j] = list.get(x);
						x++;
						y++;
					}
					if (x == list.size() - 1) // y가 넘어가면서 마지막껄 놓치는 경우
						copy[idx][j] = list.get(x);
				}
			}
			break;
		case 1:// 하
			for (int j = 0; j < N; j++) {
				list.clear();
				for (int i = N - 1; i >= 0; i--) {// 바닥부터 봄
					if (copy[i][j] == 0)
						continue;
					list.add(copy[i][j]); //아래부터 넣음
					copy[i][j] = 0;
				}
				int idx = N - 1;
				if (list.size() == 1) { // 리스트에 하나밖에 없을떄
					copy[idx][j] = list.get(0);
					continue;
				}
				for (int x = 0, y = 1; y < list.size();) {
//					if(list.get(x) == list.get(y)) {
					if (list.get(x).equals(list.get(y))) {
						copy[idx--][j] = list.get(y) + list.get(x);
						x += 2;
						y += 2; // 합쳐졌으면 다음에 그 합쳐진 블록 안보도록
					} else {
						copy[idx--][j] = list.get(x);
						x++;
						y++;
					}
					if (x == list.size() - 1) // y가 넘어가면서 마지막껄 놓치는 경우
						copy[idx][j] = list.get(x);
				}
			}
			break;
		case 2: // 좌
			for (int i = 0; i < N; i++) { // 왼쪽부터 보면서 차례대로 넣음
				list.clear();
				for (int j = 0; j < N; j++) {
					if (copy[i][j] == 0)
						continue;
					list.add(copy[i][j]);
					copy[i][j] = 0;
				}
				int idx = 0;
				if (list.size() == 1) { // 리스트에 하나밖에 없을떄
					copy[i][idx] = list.get(0);
					continue;
				}
				for (int x = 0, y = 1; y < list.size();) {
//					if(list.get(x) == list.get(y)) {
						if (list.get(x).equals(list.get(y))) {
						copy[i][idx++] = list.get(y) + list.get(x);
						x += 2;
						y += 2; // 합쳐졌으면 다음에 그 합쳐진 블록 안보도록

					} else {
						copy[i][idx++] = list.get(x);
						x++;
						y++;
					}
					if (x == list.size() - 1) // y가 넘어가면서 마지막껄 놓치는 경우
						copy[i][idx] = list.get(x);
				}
			}
			break;
		case 3: // 우
			for (int i = 0; i < N; i++) {
				list.clear();
				for (int j = N - 1; j >= 0; j--) { // 우측부터 봄 와 --를 한시간동안 찾았다
					if (copy[i][j] == 0)
						continue;
					list.add(copy[i][j]); // 위에서 차례대로 넣음
					copy[i][j] = 0;
				}
				int idx = N - 1;
				if (list.size() == 1) { // 리스트에 하나밖에 없을떄
					copy[i][idx] = list.get(0);
					continue;
				}
				for (int x = 0, y = 1; y < list.size();) {
//					if(list.get(x) == list.get(y)) {
						if (list.get(x).equals(list.get(y))) {
						copy[i][idx--] = list.get(y) + list.get(x);
						x += 2;
						y += 2; // 합쳐졌으면 다음에 그 합쳐진 블록 안보도록
					} else {
						copy[i][idx--] = list.get(x);
						x++;
						y++;
					}
					if (x == list.size() - 1) // y가 넘어가면서 마지막껄 놓치는 경우
						copy[i][idx] = list.get(x);
				}
			}
			break;
		default:
			break;
		}
		return copy;
	}
}
