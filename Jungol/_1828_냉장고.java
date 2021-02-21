/**************************************************************
    Problem: 1828
    User: csgm2328
    Language: Java
    Result: Success
    Time:165 ms
    Memory:12980 kb
****************************************************************/
 
import java.io.*;
import java.util.*;
 
public class _1828_냉장고 {
    static int N;
    static ArrayList<Pair> arr = new ArrayList<Pair>();
 
    static class Pair implements Comparable<Pair>{
        int low;
        int high;
 
        public Pair(int low, int high) {
            super();
            this.low = low;
            this.high = high;
        }
 
        @Override
        public int compareTo(Pair o) {
            //낮은 온도순 정렬 실수!!!!!!!!!!!!!!!!
            //높은 온도순 정렬해야한다 왜?
            // 
            int diff = this.high - o.high;
            return diff != 0 ? diff: this.low - o.low;
        }
    }
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
 
        N = Integer.parseInt(bw.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bw.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr.add(new Pair(start, end));
        }
        Collections.sort(arr); //정렬해야 전 냉장고를 신경안써도 됨
         
        int idx = 0;
        int cnt = 1;
        int max = arr.get(idx++).high;
        while(idx < N) {
            //지금 보관중인 가장 높은 온도보다 더 높은 low온도가 필요한 게 오면 ++
            if(max < arr.get(idx).low ) { 
                max = arr.get(idx).high;
                cnt ++;
            }
            //두번째 경우 min보다 더 낮은 높은게 올때도 체크해야되는데 정렬하면 이럴 일 없음
            //낮은 온도 순으로 정렬하면 max가 더 낮아질 수 있다
            idx++;
        }
        System.out.print(cnt);
    }
}