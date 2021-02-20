package D3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 소득불균형 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc =1 ; tc <=T;tc++) {
			int num = Integer.parseInt(br.readLine());
			List<Integer> incomes = new ArrayList<Integer>();
			int sum =0;
			
			//bw는 tokenizer이용해야함
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i =0; i< num; i++) {
				int in  = Integer.parseInt(st.nextToken());
				incomes.add(in);
				sum += in;
			}
			//average
			double aver = (double) sum / num;
			int cnt = 0;
			for(int i = 0 ; i<incomes.size();i++) {
				if(incomes.get(i) <= aver) // 평균소득이하라면
					cnt++;
			}
			System.out.println("#" + tc  + " " + cnt);
		}
	}
}
