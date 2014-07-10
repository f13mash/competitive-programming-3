package chap1.adhoc.palindromes.p10945;

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
		if(line.trim().compareTo("DONE") == 0)
			return 0;
		String a = line.trim().toLowerCase().replaceAll("[\\.\\,\\!\\? ]", "");
		String rev = new StringBuffer(a).reverse().toString();
		//System.out.println(rev + " : "+a);
		String out = "Uh oh..";
		if(rev.compareTo(a) == 0)
			out = "You won't be eaten!";
		System.out.println(out);
		return 1;
	}
	

}
