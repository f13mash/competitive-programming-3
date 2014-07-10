package chap1.adhoc.palindromes.p401;

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
		
		init();
		
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
	
	static char[][] reverse = {
		{'A', 'A'}, 
		{'E', '3'},
		{'H', 'H'},
		{'I', 'I'}, 
		{'J', 'L'},
		{'L', 'J'},
		{'M', 'M'}, 
		{'O', 'O'}, 
		{'S', '2'},
		{'T', 'T'},
		{'U', 'U'},
		{'V', 'V'},
		{'W', 'W'},
		{'X', 'X'},
		{'Y', 'Y'},
		{'Z', '5'},
		{'1', '1'},
		{'2', 'S'},
		{'3', 'E'},
		{'5', 'Z'},
		{'8', '8'}
	};
	static char[] mp = new char[1 << Character.SIZE];
	static void init() {
		for(char[] arr : reverse) {
			mp[arr[0]] = arr[1];
		}
	}
	static boolean first = true;
	static int process(String line) {
		if(line == null || line.trim().length() == 0)
			return 0;
		String a = line.trim();
		String b = new StringBuffer(a).reverse().toString();
		boolean palindrome = true, mirrored = true, mirPal = false;
		if(a.compareTo(b) != 0) {
			palindrome = false;
		}
		char[] arr = a.toCharArray();
		for(char c : arr) {mirPal = true;
			if(mp[c] == 0) {
				mirrored = false;
				break;
			}
		}
		if(mirrored) {
			StringBuffer t = new StringBuffer();
			for(char c : arr) {
				t.append(mp[c]);
			}
			
			String o = new StringBuffer(t).reverse().toString();
			if(o.compareTo(a) != 0)
				mirrored = false;
		}
		String out = "";
		if(palindrome) {
			if(mirrored) {
				out = String.format("%s -- is a mirrored palindrome.", line.trim());
			}
			else {
				out = String.format("%s -- is a regular palindrome.", line.trim());
			}
		}
		else {
			if(mirrored) {
				out = String.format("%s -- is a mirrored string.", line.trim());
			}
			else {
				out = String.format("%s -- is not a palindrome.", line.trim());
			}
		}
		System.out.println(out);
		System.out.println();
		return 1;
	}
	

}
