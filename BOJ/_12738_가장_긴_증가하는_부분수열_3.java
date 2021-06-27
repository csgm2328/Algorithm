import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//가장 긴 증가하는 부분수열 3
// 수열길이가 최대백만일므로 O(n^2) 불가
// 1. 뒤에 큰수가 나오면 연속길이 증가
// 2. 아니라면 들어가야할 자리에 갱신
// --> 길이에 영향을 미치진 않지만 앞으로 더 작은 수로 이어질수 있게 하기위함

// upper 와 lower bound의 차이는
// 수열안에서 값과 타겟이 일치할때 lower는 그 값 인덱스를 리턴하고
// upper는 그 값보다 한칸 뒤를 리턴한다는 게 차이점
public class _12738_가장_긴_증가하는_부분수열_3 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		List<Integer> arr = new ArrayList<>();
		arr.add(Integer.MIN_VALUE);
		st = new StringTokenizer(br.readLine());
		for(int i  =0; i< N; i++) {
			int x = Integer.parseInt(st.nextToken());
			if(arr.get(arr.size() - 1) < x)
				arr.add(x);
			else {
				//자리 찾아서 갱신
				int index = lower_bound(arr, x);
				arr.set(index, x);
			}
		}
		System.out.println(arr.size()-1);
	}
	private static int lower_bound(List<Integer> arr, int target) {
		//이분 탐색으로 들어갈 위치 리턴
		int start = 0;
		int end = arr.size()-1;
		while(start < end) {
			int mid = (start+end)/2;
			if(arr.get(mid) >= target) //목표가 중간보다 작다면
				end = mid;
			else
				start = mid +1;
		}
		return end;
	}
}