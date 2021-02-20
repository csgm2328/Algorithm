package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class 정사각형방_참고 {
    static BufferedReader bf = new  BufferedReader(new InputStreamReader(System.in));
    static int T, N;
    static int[][] room;
    static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};//상하좌우
    public static void main(String[] args) throws NumberFormatException, IOException {
        T = Integer.parseInt(bf.readLine());
         
         
        for (int tc = 1; tc <= T; tc++) {
             
            //입력
            N = Integer.parseInt(bf.readLine());
            room = new int[N*N+1][];
             
            for (int r = 0; r < N; r++) {
                String[] tmp = bf.readLine().split(" ");
                for (int c = 0; c < N; c++) {
                    int idx = Integer.parseInt(tmp[c]);
                    room[idx] = new int[] {r,c};
                }
            }
             
            //1~N 탐색
            for (int num = 1; num < room.length; num++) {
                Idx = num;
                move(num);
                num = lastIdx;
            }
            System.out.println("#"+tc+" "+maxIdx+" "+max);
             
            //초기화
            cnt = 1; 
            Idx = 0; 
            max = 1; 
            maxIdx = 0; 
            lastIdx = 0; 
             
        }//for-tc
         
    }
     
    static int cnt = 1; //몇번이동?
    static int Idx = 0; //현재 인덱스
    static int max = 1; //최대이동횟수
    static int maxIdx = 0; //최대 이동횟수 시작 번호
    static int lastIdx = 0; //마지막 탐색 번호
     
    //num부터 움직이기 시작
    static void move(int num) {
        boolean flag = false;
         
        if(num<=N*N-1) {
            for (int direc = 0; direc < 4; direc++) { //4방탐색
                int x = room[num][0]+dx[direc];
                int y = room[num][1]+dy[direc];
                if(x>=0 && y>=0 && x<N && y<N) { //범위안
                    if(x == room[num+1][0] && y == room[num+1][1]) { //다음번호 위치와 일치
                        cnt++;
                        flag=true; 
                        move(num+1);
                    }
                }
            }
        }
         
        if(flag==false) { //4방탐색실패
            if(max<cnt) { //최대값과 비교
                max=cnt;
                maxIdx=Idx;
            }
            cnt=1; //초기화
            lastIdx=num;
            return;
        }
    }
 
}