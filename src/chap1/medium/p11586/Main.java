package chap1.medium.p11586;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
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
		int l = Integer.parseInt(line.trim());
		for(int i = 0; i < l; i++) {
			char[] arr = readLine().trim().toCharArray();
			int cnt = 0;
			int p = 0;
			for(char c : arr) {
				if(c == ' ')
					p++;
				if(c == 'M')
					cnt++;
				if(c == 'F')
					cnt--;
			}
			if(cnt == 0 && p >= 1) {
				System.out.println("LOOP");
			}
			else {
				System.out.println("NO LOOP");
			}
		}
		return 0;
	}
	

}
