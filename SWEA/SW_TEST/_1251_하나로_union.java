package SW_TEST;

import java.util.PriorityQueue;
import java.util.Scanner;

public class _1251_하나로_union {
    public static boolean Union(int from,int to) {
        int aRoot=find(from);
        int bRoot=find(to);
        if(aRoot==bRoot)return true;
        parents[aRoot]=bRoot;
        return false;
    }
    public static int find(int x) {
        if(parents[x]==x)return x;
        return parents[x]=find(parents[x]);
    }
    static class island implements Comparable<island>{
        int x,y;
        double weight;
        public island(int x, int y, double weight) {
            super();
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
        @Override
        public int compareTo(island o) {
            // TODO Auto-generated method stub
            if(this.weight>o.weight)return 1;
            if(this.weight<o.weight)return -1;
            return 0;

        }

    }
    public static void make() {
        for(int i=0;i<N;i++)parents[i]=i;
    }
    static int N;
    static long total;
    static int[] parents;
    static long[] x;
    static long[] y;

public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    int TC=sc.nextInt();
    for(int t=1;t<=TC;t++) {
        N=sc.nextInt();
        x=new long[N];
        y=new long[N];
        double dist;
        double[][] map=new double[N][N];
        parents=new int[N];
        for(int i=0;i<N;i++)x[i]=sc.nextLong();
        for(int i=0;i<N;i++)y[i]=sc.nextLong();
        double E=sc.nextDouble();
        make();
        PriorityQueue<island> queue=new PriorityQueue<island>();
        for(int i=0;i<N;i++) {
            for(int j=i+1;j<N;j++) {
                if(i==j)continue;
                dist=(x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]);
                map[i][j]=dist;
                map[j][i]=dist;
                queue.offer(new island(i, j, dist));
                
            }
        }
        int cnt=0;
        total=0;
        while(!queue.isEmpty()) {
            if(cnt==N-1)break;
            island current = queue.poll();
            if(Union(current.x, current.y))continue;
            cnt++;
            total+=current.weight;
        }
        System.out.println("#"+t+" "+Math.round(E*total));
    }
}
}