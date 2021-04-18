import java.io.*;
import java.util.*;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

// 나무 재테크 

public class _16236_나무재테크 {
	static StringBuilder sb = new StringBuilder();
	static int N, M, K; // 1 ~ 10 , 1~ N^2
	static int[][] arr, adding;
	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class Tree implements Comparable<Tree>{
		int r, c, age;

		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}
		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.age, o.age);
		}
	}

	static PriorityQueue<Tree> Trees = new PriorityQueue<Tree>();
	static Queue<Tree> Alives = new LinkedList<Tree>();
	static Queue<Tree> Deads = new LinkedList<Tree>();
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		adding = new int[N + 1][N + 1];
		arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				adding[i][j] = Integer.parseInt(st.nextToken());
				arr[i][j] = 5;
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			Trees.offer(new Tree(r, c, age));
		}
		for (int i = 0; i < K; i++) { // K년 동안
			Spring();
			Summer();
			Autumn();
			Winter();
		}
		System.out.println(Trees.size());
	}

	private static void Spring() {
		// 나이순대로 나이만큼 양분먹기
		while (!Trees.isEmpty()) {
			Tree t = Trees.poll();
			if (t.age <= arr[t.r][t.c]) {
				arr[t.r][t.c] -= t.age;
				t.age++;
				Alives.offer(t);
			} else
				Deads.offer(t);
		}
	}

	private static void Summer() {
		// 죽은나무가 양분으로
		while (!Deads.isEmpty()) {
			Tree t = Deads.poll();
			arr[t.r][t.c] += t.age / 2;
		}
	}

	private static void Autumn() {
		// 8방 번식
		while (!Alives.isEmpty()) {
			Tree cur = Alives.poll();
			if (cur.age % 5 == 0) {
				for (int dir = 0; dir < 8; dir++) {
					int nr = cur.r + dr[dir];
					int nc = cur.c + dc[dir];
					if (isInBoundary(nr, nc))
						Trees.offer(new Tree(nr, nc, 1));
				}
			}
			Trees.offer(cur);
		}
	}

	private static void Winter() {
		// 양분 추가
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				arr[i][j] += adding[i][j];
	}

	private static boolean isInBoundary(int r, int c) {
		return 1 <= r && r <= N && 1 <= c && c <= N;
	}
}
