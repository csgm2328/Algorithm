package SW_TEST;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 활주로 건설
// 가로 행 세로 행 확인하면서
// 활주로 건설이 가능한 경우를 센다
// 다음칸이 같은, 한칸높은, 한칸낮은 경우
// 세가지에서 건설이 가능한 경우를 체크
// 1. 높은거 만날때 낮은거 만날때 flag 바꾸면서 length >= X인지 체크
public class _4014_활주로_건설 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static int N, X, arr[][];

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					arr[i][j] = Integer.parseInt(st.nextToken());
			}
			int sum = 0;
			// 가로 검사
			for (int i = 0; i < N; i++) {
				int height = arr[i][0];
				int length = 1;
				boolean setable = true;
				for (int j = 1; j < N; j++) {
					if (height == arr[i][j]) {//높이같으면 
						length++;
						if(!setable && length >= X) { //실수포인트! 수평일때 경사로 필요한 상태였으면 처리해줘야함
							setable = true;
							length = 0;
						}
					}
					else if (height == arr[i][j] + 1) { // 다음게 한칸 낮으면
						if (!setable) { // 경사로 필요한 상태면
							if (length < X)
								break;
						} // 맨 위인 경우
						length = 1;
						setable = false; // 경사로가 필요한상태로 변경
					} else if (height == arr[i][j] - 1 ){ // 다음게 더 높으면 경사로 설치 가능한지 바로 확인
						if (length >= X) { // 경사로 길이가 되면
							setable = true;
							length = 1;
						} else {
							setable = false;
							break; // 경사로 설치못하는행
						}
					}
					else {
						setable = false;
						length = 1;
						break;
					}
					height = arr[i][j];
				} // end for j

				// 한 행 다 봤으면
				if (setable)
					sum++;
				else if (!setable && length >= X) // 경사로필요한 상태인데 만들수있다면
					sum++;
//				if(length >= X)
//					sum++;
			}
			// 세로 검사
			for (int i = 0; i < N; i++) {
				int height = arr[0][i];
				int length = 1;
				boolean setable = true;
				for (int j = 1; j < N; j++) {
					if (height ==  arr[j][i]) {
						length++;
						if(!setable && length >= X) {
							setable = true;
							length = 0;
						}
					}
					else if (height == arr[j][i] + 1) { // 다음게 더 낮으면
						if (!setable) { // 경사로 필요한 상태면
							if (length < X)
								break;
						} // 맨 위인 경우
						length = 1;
						setable = false; // 경사로가 필요한상태로 변경
					} else if( height == arr[j][i] -1 ) { // 다음게 더 높으면 경사로 설치 가능한지 바로 확인
						if (length >= X) { // 경사로 길이가 되면
							setable = true;
							length = 1;
						} else {
							setable = false;
							break; // 경사로 설치못하는행
						}
					}
					else {
						setable = false;
						length = 1;
						break;
					}
					height = arr[j][i];
				}
				if (setable)
					sum++;
				else if (!setable && length >= X)
					sum++;
//				if(length >= X)
//					sum++;
			}

			sb.append("#" + tc + " " + sum + "\n");
		} // end TC
		System.out.println(sb.toString());
	}
}
