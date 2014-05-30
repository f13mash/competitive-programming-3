package chap1.medium.p10324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	static int ind = 0;
	static int process(String line) {
		if(line == null || line.trim().length() == 0)
			return 0;
		line = line.trim();
		int cnt = Integer.parseInt(readLine().trim());
		char[] la = line.toCharArray();
		int[] val = new int[la.length];
		val[0] = 1;
		for(int i = 1; i < val.length; i++) {
			if(la[i] == la[i-1])
				val[i] = val[i-1] + 1;
			else
				val[i] = 1;
		}
		System.out.println("Case "+(++ind)+":");
		for(int i = 0; i < cnt; i++) {
			int[] arr = giveArray(readLine().trim().split(" "));
			int a = Math.min(arr[0], arr[1]), b = Math.max(arr[0], arr[1]);
			
			boolean valid = true;
			if(val[b] < (b - a + 1)) {
				valid = false;
			}
			System.out.println(valid ? "Yes" : "No");
		}
		
		return 1;

	}

}
