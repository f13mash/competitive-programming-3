package chap1.medium.p10141;

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
		int[] arr = giveArray(line.trim().split(" "));
		int n = arr[0], p = arr[1];
		if(n == 0 && p == 0)
			return 0;
		for(int i = 0; i < n; i++) {
			readLine();
		}
		String name = null;
		int met = -1;
		double best = -1;
		
		for(int i = 0; i < p; i++) {
			String s = readLine().trim();
			String[] splts = readLine().trim().split(" ");
			double price = Double.parseDouble(splts[0]);
			int req = Integer.parseInt(splts[1]);
			if(req > met) {
				name = s;
				met = req;
				best = price;
			}
			else
				if(req == met && price < best) {
					best = price;
					name = s;
				}
			for(int j = 0; j < req; j++)
				readLine();
		}
		if(ind > 0)
			 System.out.println();
		System.out.println("RFP #" + (++ind));
		System.out.println(name);
		
		return 1;

	}

}
