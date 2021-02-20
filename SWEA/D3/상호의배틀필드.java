package D3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//120m
public class 상호의배틀필드 {
	static char[][] arr;
	static int[] dr = { -1, 1, 0, 0 }; // 내려가는게
	static int[] dc = { 0, 0, -1, 1 };
	static char[] printdir = {'^','v','<','>'};
	
	static int cu_row, cu_col;
	static int height, width;

	static void forward(int dir) {
		arr[cu_row][cu_col] = '.';
		int NextR = cu_row + dr[dir];
		int NextC = cu_col + dc[dir];
		if (NextR >= 0 && NextR < height && NextC >= 0 && NextC < width && arr[NextR][NextC] == '.') {
			cu_row = NextR;
			cu_col = NextC;
		}
	}

	static void Shoot(int dir) {
		int NextR = cu_row + dr[dir];
		int NextC = cu_col + dc[dir];
		while (NextR >= 0 && NextR < height && NextC >= 0 && NextC < width) { // 범위 밖으로 나갈 떄까지
			if (arr[NextR][NextC] == '*') {
				arr[NextR][NextC] = '.';
				break;
			} else if (arr[NextR][NextC] == '#')
				break;

			NextR += dr[dir]; //실수 cu값에 더하면 더 안가지
			NextC += dc[dir];
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			height = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			arr = new char[height][width];

			for (int i = 0; i < height; i++) {
				String[] s = br.readLine().split("");
				for (int j = 0; j < width; j++) {
					arr[i][j] = s[j].charAt(0);
					if (arr[i][j] == '^' || arr[i][j] == 'v' || arr[i][j] == '<' || arr[i][j] == '>') {
						cu_row = i;
						cu_col = j;
					}
				}
			}
			
			int dir = 0;
			for(int i = 0; i< 4; i++) 
				if (arr[cu_row][cu_col] == printdir[i])
					dir = i;	//처음에 바라보고 있는 위치를 수로 변환
			
			int operNum = Integer.parseInt(br.readLine());
			String[] s = br.readLine().split("");
			
			//동작
			for (int i = 0; i < operNum; i++) {
				char op = s[i].charAt(0);
				switch (op) {
				case 'U': // 위로 돌고 평지면 한칸 전진
					dir = 0;
					forward(dir);
					break;
				case 'D':
					dir = 1;
					forward(dir);
					break;
				case 'L':
					dir = 2;
					forward(dir);
					break;
				case 'R':
					dir = 3;
					forward(dir);
					break;
				case 'S': // 대포발사
					Shoot(dir);
					break;
				}
			}
			//출력 준비
			arr[cu_row][cu_col] = printdir[dir];

			sb.append("#" + tc + " ");
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					sb.append(arr[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}
}
