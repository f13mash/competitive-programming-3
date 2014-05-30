package chap1.easy.p11559;

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
		if(line == null || line.trim().length() == 0)
			return 0;
		int[] inp = giveArray(line.trim().split(" "));
		int n = inp[0], b = inp[1], h = inp[2], w = inp[3];
		int[][] havail = new int[h][];
		int[] hcost = new int[h];
		
		for(int i = 0; i < h; i++) {
			hcost[i] = Integer.parseInt(readLine().trim());
			havail[i] = giveArray(readLine().trim().split(" "));
		}
		long cost = Long.MAX_VALUE;
		for(int i = 0; i < w; i++) {
			long min = Long.MAX_VALUE;
			for(int j = 0; j < h; j++) {
				if(havail[j][i] >= n)
					min = Math.min(min, hcost[j]);
			}
			if(min == Long.MAX_VALUE) {
			}
			else {
				cost = Math.min(min * n, cost);
			}
		}
		
		if(cost > b) {
			System.out.println("stay home");
		}
		else
			System.out.println(cost);
		
		return 1;

	}

}
