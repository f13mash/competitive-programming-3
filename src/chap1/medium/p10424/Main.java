package chap1.medium.p10424;

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
		if(line == null || line.trim().length() == 0)
			return 0;
		String a = line.trim(), b = readLine().trim();
		int ia = value(a), ib = value(b);
		if(ia > ib) {
			int t = ia;
			ia = ib;
			ib = t;
		}
		double ans = (double) ia / ib * 100;
		System.out.format("%.2f %%%n", ans);
		
		return 1;
	}
	
	static int value(String str) {
		char[] arr = str.toCharArray();
		int val = 0;
		for(char c : arr) {
			c = Character.toLowerCase(c);
			if(c >= 'a' && c <= 'z') {
				val += (c - 'a' + 1);
			}
		}
		
		while(val/10 > 0) {
			int t = 0;
			while(val != 0) {
				t += val % 10;
				val /=10;
			}
			val = t;
		}
		return val;
	}

}
