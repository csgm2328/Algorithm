import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class _1550_16진수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String hex = br.readLine();
		
		
		//parseInt 이진수로 파싱하기
		int decimal =  Integer.parseInt(hex, 16);
		
		//정석대로 하기
//		int decimal = 0;
//		for(int i = 0; i< hex.length(); i++) {
//			char x = hex.charAt(i);
//			int charToint = 0;
//			try{
//				charToint = Integer.parseInt(String.valueOf(x));
//			}
//			catch(NumberFormatException e) {
//				charToint = (int) hex.charAt(i) - 55;
//			}
//			
//			decimal += charToint * (int) Math.pow(16, hex.length() - (1 + i));
//		}
		//System.out.println(decimal);
		bw.write(""+decimal);
		bw.flush();
		bw.close();
		br.close();
	}
}
