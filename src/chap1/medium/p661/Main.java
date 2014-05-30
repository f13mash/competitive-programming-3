package chap1.medium.p661;

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
		String[] splts = line.trim().split(" ");
		
		int n = Integer.parseInt(splts[0]), m = Integer.parseInt(splts[1]);
		long c = Long.parseLong(splts[2]);
		
		if(n == 0 && m == 0 && c == 0)
			return 0;
		
		long[] cons = new long[n];
		boolean[] state = new boolean[n];
		long p = 0;
		
		for(int i = 0; i < n; i++) {
			cons[i] = Long.parseLong(readLine().trim());
		}
		long max = 0;
		boolean f = false;
		for(int i = 0; i < m; i++) {
			int dev = Integer.parseInt(readLine().trim()) - 1;

			if(f)
				continue;
			if(state[dev]) {
				p -= cons[dev];
			}
			else {
				p += cons[dev];
			}
			max = Math.max(max, p);
			if(p > c)
				f = true;
			state[dev] = !state[dev];
		}
		
		System.out.println("Sequence "+(++ind));
		if(p > c) {
			System.out.println("Fuse was blown.");
		}
		else {
			System.out.println("Fuse was not blown.");
			System.out.println("Maximal power consumption was "+max+" amperes.");
		}

		System.out.println();
		return 1;

	}

}
