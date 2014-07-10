package chap1.adhoc.palindromes.p11221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
			if(str[i].compareTo("") != 0)
				ret[i] = Integer.parseInt(str[i]);
		}
		return ret;
	}
	static int process(String line) {
		int cases = Integer.parseInt(line.trim());
		for(int c = 1; c <= cases; c++) {
			line = readLine().trim();
			StringBuffer temp = new StringBuffer();
			for(char ch : line.trim().toCharArray()) {
				if(Character.isAlphabetic(ch)){
					temp.append(ch);
				}
			}
			String a = temp.toString();
			String rev = new StringBuffer(a).reverse().toString();
			int len = a.length();
			int k = (int) Math.sqrt(len);
			String out = "No magic :(";
			if(k*k == len && rev.compareTo(a) == 0) {
				char[] arr = a.toCharArray();
				char[] n = new char[arr.length];
				for(int i = 0; i < k; i++) {
					for(int j = 0; j < k; j++) {
						n[(j*k) + i] = arr[(i*k) + j];
					}
				}
				
				String t = new String(n);
				if(t.compareTo(a) == 0) {
					out = Integer.toString(k);
				}
			}
			System.out.println("Case #"+c+":");
			System.out.println(out);			
		}
		
		return 0;
	}
	

}
