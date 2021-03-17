import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//144ms
public class _17136_색종이붙이기_참고1 {
    static int[][] paper = new int[10][10];
    static int[] color = {5,5,5,5,5};
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=0;i<10;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<10;j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = Integer.MAX_VALUE;
        
        dfs(0,0,0);
        
        if(ans==Integer.MAX_VALUE) {
            System.out.println(-1);
        }else {
            System.out.println(ans);
        }
        
    }
    static void dfs(int x, int y, int cnt) {
        if(ans<cnt) { //정답보다 색종이 사용개수가 많아지면 끝내기
            return;
        }
        
        if(y==10) { //줄바꿈
            x++;
            y=0;
        }
        if(x==10) {//탐색 끝
            ans = Math.min(ans, cnt);
            return;
        }
        if(paper[x][y]==0) {
            dfs(x,y+1,cnt);
            return;
        }
        for(int k=5;k>=1;k--) {//큰 색종이부터 붙일 수 있는지 검사

            if(x+k>10 || y+k>10 ||color[k-1]==0) //범위 벗어나거나 해당 색종이 다썼으면 넘어가기
                continue;

            boolean check = true;
            out: for(int i=0;i<k;i++) {
                for(int j=0;j<k;j++) {
                    if(paper[x+i][y+j]==0) {
                        check = false;
                        break out;
                    }
                }
            }
            if(check) { //색종이 붙일 수 있으면
                for(int i=0;i<k;i++) {
                    for(int j=0;j<k;j++) {
                        paper[x+i][y+j]=0;
                    }
                }
                color[k-1]--;
                dfs(x,y,cnt+1);

                for(int i=0;i<k;i++) {
                    for(int j=0;j<k;j++) {
                        paper[x+i][y+j]=1;
                    }
                }
                color[k-1]++;
            }
        }
    }
}