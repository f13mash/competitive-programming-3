package chap1.medium.p11661;

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
		if(l == 0)
			return 0;
		
		int lr = -1, ld = -1;
		char[] arr = readLine().toCharArray();
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < l; i++) {
			if(arr[i] == 'R') {
				lr = i;
				if(ld >= 0)
					min = Math.min(min, i - ld);
			}
			if(arr[i] == 'D') {
				ld = i;
				if(lr >= 0)
					min = Math.min(min, i - lr);
			}
			if(arr[i] == 'Z') {
				min = 0;
				break;
			}
		}
		System.out.println(min);
		
		return 1;
	}
	

}
