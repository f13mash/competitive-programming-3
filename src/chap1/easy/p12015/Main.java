package chap1.easy.p12015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	static BufferedReader br=null;
	/**
	 * @param args
	 */
	static String out = "";

	public static void main(String[] args) {
		br=new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		line = readLine();

		while(line != null && line.length()!=0) {
            if(process(line)==0) {
            	return;
            }
            
			line = readLine();
		}
		
	}
	
	static String readLine(){
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	static int[] giveArray(String[] str) {
		int[] ret = new int[str.length];
		for(int i = 0; i < str.length; i++) {
			ret[i] = Integer.parseInt(str[i]);
		}
		return ret;
	}
	
	static int process(String line) {
		int n = Integer.parseInt(line.trim());
		for(int i = 0; i < n; i++) {
			int max = -1;
			List<String> ll = new ArrayList<>();
			
			for(int j = 0; j < 10; j ++) {
				String[] splts = readLine().trim().split(" ");
				int v = Integer.parseInt(splts[1]);
				if(v > max) {
					max = v;
					ll.clear();
				}
				
				if(v >= max) {
					ll.add(splts[0]);
				}
			}
			System.out.println("Case #"+(i+1)+":");
			for(String str : ll) {
				System.out.println(str);
			}
		}
		return 0;

	}

}
