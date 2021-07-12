package Lv3;

import java.util.*;

class 단어변환 {
    static class Word{
        String str;
        int cnt;
        public Word(String str, int cnt){
            this.str = str;
            this.cnt = cnt;
        }
    }
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean flag = false;
        for(int i =0 ; i< words.length; i++){
            if(target.equals(words[i]))
                flag =  true;
        }
        if(!flag)
            return answer;
        //BFS로 되나?
        Queue<Word> q = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        q.offer(new Word(begin, 0));
        int min = Integer.MAX_VALUE;
        while(!q.isEmpty()){
            // System.out.println(q.size());
            Word cur = q.poll();
            if(cur.str.equals(target)){
                min = Math.min(cur.cnt, min);
                answer = min;
            }
            for(int i = 0; i< words.length; i++){
                //target 없거나 한자리만 다른게 없다면 나와짐
                if(!visited[i] && Changable(cur.str, words[i])){
                    q.offer(new Word(words[i], cur.cnt+1));
                    visited[i] = true;
                }
            }
        }
        return answer;
    }
    private boolean Changable(String a, String b){
        int cnt =0;
        for(int i =0 ; i< a.length(); i++){
            if(a.charAt(i) != b.charAt(i))
                cnt++;
            if(cnt > 1)
                return false;
        }
        return true;
    }
}
