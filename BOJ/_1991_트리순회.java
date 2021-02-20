import java.io.*;
import java.util.*;

public class _1991_트리순회 {
	static int N;
	static StringBuilder sb = new StringBuilder();
	static Node[] tree; //노드여러개를 가진 Tree
	static class Node{
		char val;
		Node left;
		Node right;
		
		public Node(char val) {
			super();
			this.val = val;
		}
	}
//	else { //연속 갱신은 불가능함 다른 방법으로
//		tree[tree[y].parent.val] = new Node(y);
//		tree[y].parent = new Node(x);
//	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new Node[N];
		for(int i = 0 ; i< N;  i++) {
			tree[i] = new Node((char)('A'+i)); //노드들 미리 넣어놓고
		}
		for(int i = 0 ; i< N;  i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			if(left != '.')
				tree[root-'A'].left = tree[left-'A']; //해당 노드를 왼쪽 자식으로
			if(right != '.')
				tree[root-'A'].right = tree[right-'A'];
		}
		
		preorder(0); //전위
		sb.append("\n");
		inorder(0);	//중위
		sb.append("\n");
		postorder(0);//후위
		sb.append("\n");
		System.out.print(sb.toString());
	}
	static void preorder(int idx) {
		// root --> left --> right
		//출력하고 왼쪽자식있으면 가고
		sb.append(tree[idx].val);//먼저 출력
		if(tree[idx].left != null)
			preorder(tree[idx].left.val - 'A');
		if(tree[idx].right != null)
			preorder(tree[idx].right.val - 'A');
	}
	static void inorder(int idx) {
		//left --> root --> right
		//왼쪽 끝 오른쪽 끝에서 출력
		if(tree[idx].left != null)
			inorder(tree[idx].left.val - 'A');
		//왼쪽으로 갈 곳없으면 출력
		sb.append(tree[idx].val); //올라가면서도 출력하게 됨
		if(tree[idx].right != null)
			inorder(tree[idx].right.val - 'A');
		
	}
	static void postorder(int idx) {
		if(tree[idx].left != null)
			postorder(tree[idx].left.val - 'A');
		if(tree[idx].right != null)
			postorder(tree[idx].right.val - 'A');
		sb.append(tree[idx].val); //오른쪽 자식 없으면 출력
	}
	
}
