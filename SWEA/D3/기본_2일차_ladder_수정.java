package D3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//도착지에서 부터 찾자
//근데 왜 더 오래걸리지? + 2ms
//교수님말대로 다리로 한방에 뛰어보자

public class 기본_2일차_ladder_수정 {
	static int ladder_size = 100;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			int[][] ladder = new int[ladder_size][ladder_size];
			List<Integer> lanes = new ArrayList<Integer>(); // 다리 위치 저장할 리스트
			int tNum = Integer.parseInt(br.readLine());

			for (int i = 0; i < ladder_size; i++) {
				String[] s = br.readLine().split(" ");
				for (int j = 0; j < ladder_size; j++) {
					ladder[i][j] = Integer.parseInt(s[j]);
				}
			}
			// 다리가 있는 위치 저장
			int idx = 0;
			int goalIdx = 0;
			
			for (int j = 0; j < ladder_size; j++) {
				if(ladder[ladder_size-1][j] != 0) { //1, 2 한번에 체크
					if(ladder[ladder_size-1][j] == 2)
						goalIdx = idx;	//idx 저장 따로 작업안하도록
					lanes.add(j);
					idx++;
				}
			}
			for(int i = ladder_size-1 ; i >= 0; i--) {
				int current_col = lanes.get(goalIdx);
				
				if(current_col-1 >= 0 && ladder[i][current_col - 1] == 1) {
					goalIdx--; //인덱스는 참 헷갈린다
				}
				else if(current_col+1 < ladder_size && ladder[i][current_col +1] == 1) {
					goalIdx++;
				}
			}
			System.out.println("#" + tNum + " " + lanes.get(goalIdx));
		}
		br.close(); //-13ms 감축
	}
}
