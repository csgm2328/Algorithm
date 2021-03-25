import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _17135_캐슬디펜스_참고 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer str = new StringTokenizer(input.readLine());
        N = Integer.parseInt(str.nextToken());
        M = Integer.parseInt(str.nextToken());
        D = Integer.parseInt(str.nextToken());

        map = new int[N][];
        for(int i = 0; i < N; i++){
            str = new StringTokenizer(input.readLine());
            int[] temp = new int[M];
            for(int j = 0; j < M; j++){
                temp[j] = Integer.parseInt(str.nextToken());
            }
            map[i] = temp;
        }
        arrow = new boolean[M];
        max_kill = 0;
        setArrowLoc(0, 0);
        System.out.println(max_kill);
    }

    static int N, M, D, max_kill;
    static int[][] map;
    static boolean[] arrow;
    static void setArrowLoc(int idx, int cnt){
        if(cnt == 3){
            startGame();
            return;
        }
        for(int i = idx; i < M; i++){
            arrow[i] = true;
            setArrowLoc(i+1, cnt+1);
            arrow[i] = false;
        }
    }
    static void startGame(){
        int[] arrow_loc = new int[3];
        boolean[][] enemy = new boolean[N][M];
        for(int i = 0, idx =0; i < M && idx < 3; i++){
            if(arrow[i]){
                arrow_loc[idx++] = i;
            }
        }
        int kill = 0;
        int[][] enemy_loc = new int[3][];
        for(int i = N; i > 0; i--){
            for(int j = 0; j < 3; j++){
                enemy_loc[j] = killEnemy(i, arrow_loc[j], enemy);
            }
            for(int j = 0; j < 3; j++){
                int r = enemy_loc[j][0];
                int c = enemy_loc[j][1];
                if(isIn(r,c) && !enemy[r][c]){
                    kill++;
                    enemy[r][c] = true;
                }
            }
        }
        if(max_kill < kill)
            max_kill = kill;
    }
    static int[] dr = {0, -1, 0};
    static int[] dc = {-1, 0, 1};
    // 궁수의 위치 row, col
    static int[] killEnemy(int row, int col, boolean[][] enemy){
        Queue<int[]> queue = new LinkedList<>();
        int e_row = row - 1;
        int e_col = col;
        int dist = 1;
        if(map[e_row][e_col] == 1 && !enemy[e_row][e_col]){
            return new int[]{e_row, e_col};
        }

        queue.add(new int[]{e_row, e_col});
        dist++;
        while (dist <= D && !queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] temp = queue.poll();
                for (int d = 0; d < 3; d++) {
                    int nr = temp[0] + dr[d];
                    int nc = temp[1] + dc[d];
                    if (isIn(nr, nc)) {
                        if (map[nr][nc] == 1 && !enemy[nr][nc]) {
                            return new int[]{nr, nc};
                        }
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
            dist++;
        }
        return new int[]{-1, -1};
    }

    static boolean isIn(int row, int col){
        return row < N && row >= 0 && col < M && col >= 0;
    }
}