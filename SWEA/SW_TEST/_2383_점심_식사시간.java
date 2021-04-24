package SW_TEST;
import java.io.*;
import java.util.*;
 
public class _2383_점심_식사시간 {
    static int N;
    static int[][] arr;
    static List<int[]> peoples, steps;
    static class people {
        int dis,move;
 
        public people(int dis, int move) {
            super();
            this.dis = dis;
            this.move = move;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine()); // 학생수
            arr = new int[N][N];
            steps = new ArrayList<int[]>();
            peoples = new ArrayList<int[]>();
             
            for(int i = 0; i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for(int  j =0;j<N;j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if(arr[i][j] == 1)
                        peoples.add(new int[] {i,j});
                    else if(arr[i][j] != 0)
                        steps.add(new int[] {i,j});
                }
            }
            //부분집합으로 각 계단으로 들어갈 사람 정하기
            check = new boolean[peoples.size()];
            subset(0);
             
            sb.append("#" + tc + " " + MIN +"\n");
            MIN = Integer.MAX_VALUE;
        }
        System.out.println(sb.toString());
    }
    static int MIN = Integer.MAX_VALUE;
    static boolean[] check;
    private static void subset(int idx) {
        if (idx == peoples.size()) {
            List<people> toStep1 = new ArrayList<people>();
            List<people> toStep2 = new ArrayList<people>(); 
            for(int i =0; i< idx; i++) {
                if(check[i]) {
                    int[] x = peoples.get(i);
                    int dis = Math.abs(steps.get(0)[0] - x[0]) + Math.abs(steps.get(0)[1] - x[1]);
                    toStep1.add(new people(dis, 0));
                }
                     
                else {
                    int[] x = peoples.get(i);
                    int dis = Math.abs(steps.get(1)[0] - x[0]) + Math.abs(steps.get(1)[1] - x[1]);
                    toStep2.add(new people(dis, 0));
                }
            }
            MIN = Math.min(MIN, getTime(toStep1,toStep2));
            return;
        }
        check[idx] = true;
        subset(idx+1);
        check[idx] = false;
        subset(idx+1);
    }
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
     
    private static int getTime(List<people> toStep1, List<people> toStep2) {
        int timer1 = 0;
        int timer2 = 0;
        int step1_length = arr[steps.get(0)[0]][steps.get(0)[1]];
        int step2_length = arr[steps.get(1)[0]][steps.get(1)[1]];
         
        List<people> inStep1 = new ArrayList<people>();
        List<people> inStep2 = new ArrayList<people>();
         
        //따로따로해서 maxtimer를 리턴
         
        while(!toStep1.isEmpty() || !inStep1.isEmpty()) {
            timer1++;
            for(int i = 0; i< toStep1.size();i++) {
                toStep1.get(i).dis--;
                if(toStep1.get(i).dis == 0) {
                    people cur = toStep1.remove(i);
                    i--;
                    inStep1.add(new people(cur.dis, 0));
                }
            }
            for(int i = 0; i< inStep1.size();i++) {
                if(i >= 3 && inStep1.get(i).move != 0) //3번쨰이상이면 대기중인로만 만들어줌
                    continue;
                inStep1.get(i).move++;
                if(inStep1.get(i).move == step1_length+2) {//바로 들어오자마자 계단처리를 하니까 
                    inStep1.remove(i);
                    i--;
                }
            }
        }
        while(!toStep2.isEmpty() || !inStep2.isEmpty()) {
            timer2++;
            for(int i = 0; i< toStep2.size();i++) {
                toStep2.get(i).dis--;
                if(toStep2.get(i).dis == 0) {
                    people cur = toStep2.remove(i);
                    i--;
                    inStep2.add(new people(cur.dis, 0));
                }
            }
            for(int i = 0; i< inStep2.size();i++) {
                if(i >= 3 && inStep2.get(i).move != 0) //3번쨰이상이면 대기중인로만 만들어줌
                    continue;
                inStep2.get(i).move++;
                if(inStep2.get(i).move == step2_length+2) {
                    inStep2.remove(i);
                    i--;
                }
            }
        }
        return Math.max(timer1, timer2);
    }
}