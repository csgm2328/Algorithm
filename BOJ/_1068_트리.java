import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 트리에서의 노드 삭제
// 2:00~ 3:30
public class _1068_트리 {
	static int N, remove;

	static class Node {
		int val;
		List<Node> Children; // 이진트리가 아니였다

		public Node(int val) {
			this.val = val;
		}
	}

	static Node[] tree;
	static List<Integer> roots = new ArrayList<Integer>(); // 루트가 여러개일 수 있다

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		tree = new Node[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = new Node(i); // 먼저 노드 생성 (노드가 순서대로 생성되지 않을 수있음을 대비)
			tree[i].Children = new ArrayList<Node>();
		}
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken());
			if (x == -1)
				roots.add(i);
			else
				tree[x].Children.add(new Node(i));
		}
		remove = Integer.parseInt(br.readLine());
		for (int x : roots)
			SearchTarget(x);
		for (int x : roots)
			CountLeaf(x);
		System.out.println(cnt);
	}

	// 리프 노드 세기
	static int cnt = 0;
	private static void CountLeaf(int cur) {
		if (tree[cur] == null)
			return;
		if (tree[cur].Children.size() == 0)
			cnt++;
		else {
			for (int i = 0; i < tree[cur].Children.size(); i++) {
				CountLeaf(tree[cur].Children.get(i).val);
			}
		}
	}

	// 해당노드 찾기
	static boolean removed = false; // 삭제 여부

	private static void SearchTarget(int cur) {
		if (remove == cur) {
			RemoveChild(cur);
			removed = true;
		} else {
			for (int i = 0; i < tree[cur].Children.size(); i++) {
				SearchTarget(tree[cur].Children.get(i).val);
				if (removed) {
					tree[cur].Children.remove(i); // 자식이 지워졌음을 표시
					removed = false; // 더이상 안지워지도록
				}
			}
		}
	}

	// 자식들 다 지우기
	private static void RemoveChild(int cur) {
		for (int i = 0; i < tree[cur].Children.size(); i++) {
			RemoveChild(tree[cur].Children.get(i).val);
		}
		tree[cur] = null; // 이걸 가리키고 있는 부모노드의 자식노드도 지워야함

	}
}