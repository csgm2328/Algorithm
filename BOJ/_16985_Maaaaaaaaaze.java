import java.io.*;
import java.util.*;

// Maaaaaaze
// 90,180,270도 도는 걸 따로 처리하면 카피배열을 써서 기본상태를 유지해야되지만
// 그냥 dfs는 원래부터 270도 까지 순서대로 불려가는 특징을 이용해서
// 그냥 90도 상태를 유지하고 다음에 180도를 똑같은 방식으로 90도 행을 보면서 열부터 써주면 된다

// 순서: 5층을 돌리는 4가지 상태를 dfs로 하고 5층 상태 선택되면
// 층을 놓는 경우를 순열로 놓은 후 bfs
public class _16985_Maaaaaaaaaze {
	static int[][][] arr = new int[5][5][5];
	static int ans = Integer.MAX_VALUE;

	static class Node {
		int h, r, c, cnt;

		Node(int h, int r, int c, int cnt) {
			this.h = h;
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int[] dh = { -1, 1, 0, 0, 0, 0 };
	static int[] dr = { 0, 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 0, -1, 1 };

	static void rotate(int height) {
		int[][] tmp = new int[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				tmp[j][5 - i - 1] = arr[height][i][j];
			}
		}
		arr[height] = tmp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for (int h = 0; h < 5; h++) {
			for (int r = 0; r < 5; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < 5; c++)
					arr[h][r][c] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	// dfs로 5개 층 골라서 돌리기
	static void dfs(int height_idx) {
		if (height_idx == 5) {
			perm(0); // 5층 다 돌렸으면 층 배치
			return;
		}
		for (int i = 0; i < 4; i++) {
			rotate(height_idx); // 돌리고
			dfs(height_idx + 1); // 다음으로
		}
	}

	static void perm(int idx) {
		if (idx == 5) {
			if (arr[0][0][0] == 1 && arr[4][4][4] == 1)
				bfs(); // 층 배치 다했으면 bfs
			return;
		}
		for (int i = idx; i < 5; i++) {
			swap(i, idx);
			perm(idx + 1);
			swap(i, idx);
		}
	}

	// 층 배치하기( 층끼리 바꾸기)
	static void swap(int a, int b) {
		int[][] tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	static void bfs() {
		boolean[][][] visited = new boolean[5][5][5];
		Queue<Node> queue = new LinkedList<>();
		visited[0][0][0] = true;
		queue.add(new Node(0, 0, 0, 0));

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.h == 4 && node.r == 4 && node.c == 4) {
				ans = Math.min(ans, node.cnt);
				if (ans == 12) { //최적 해
					System.out.println(12);
					System.exit(0);
				}
				return;
			}
			for (int i = 0; i < 6; i++) {
				int nh = node.h + dh[i];
				int nr = node.r + dr[i];
				int nc = node.c + dc[i];
				if (nh < 0 || nr < 0 || nc < 0 || nh > 4 || nr > 4 || nc > 4)
					continue;
				if (arr[nh][nr][nc] == 1 && !visited[nh][nr][nc]) {
					visited[nh][nr][nc] = true;
					queue.add(new Node(nh, nr, nc, node.cnt + 1));
				}
			}
		}
	}
}