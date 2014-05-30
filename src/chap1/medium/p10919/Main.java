package chap1.medium.p10919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
		if(arr.length == 1 && arr[0] == 0)
			return 0;
		
		int n = arr[0], c = arr[1];
		String[] chosen = readLine().trim().split(" ");
		boolean[] mp = new boolean[10002];
		for(String s : chosen) {
			mp[Integer.parseInt(s)] = true;
		}
		boolean fail = false;
		for(int i = 0; i < c; i++) {
			int[] cat = giveArray(readLine().trim().split(" "));
			int min = cat[1];
			for(int j = 2; j < 2 + cat[0]; j++) {
				if(mp[cat[j]])
					min--;
			}
			if(min > 0)
				fail = true;
		}
		System.out.println(fail ? "no" : "yes");
		return 1;
	}
	

}
