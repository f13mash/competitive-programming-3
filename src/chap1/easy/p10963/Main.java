package chap1.easy.p10963;

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
		int n =  Integer.parseInt(line.trim());
		
		for(int i = 0; i < n; i++) {
			readLine();
			int w = Integer.parseInt(readLine().trim());
			int gap = -1;
			boolean done = true;
			for(int j = 0; j < w; j++) {
				int[] arr = giveArray(readLine().trim().split(" "));
				if(j == 0)
					gap = arr[1] - arr[0];
				else
					if(gap != (arr[1] - arr[0]))
						done = false;
			}
			if(i > 0)
				System.out.println();
			System.out.println(done ? "yes" : "no");
		}
		
		return 0;

	}

}
