package chap1.easy.p11679;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
		int[] arr = giveArray(line.trim().split(" "));
		if(arr[0] == 0 && arr[1] == 0)
			return 0;
		int b = arr[0], n = arr[1];
		
		int[] v = giveArray(readLine().trim().split(" "));
		
		for(int i = 0; i < n; i++) {
			int[] deb = giveArray(readLine().trim().split(" "));
			v[deb[0] - 1] -= deb[2];
			v[deb[1] - 1] += deb[2];
		}
		Arrays.sort(v);
		if(v[0] < 0)
			System.out.println("N");
		else
			System.out.println("S");
		return 1;

	}

}
