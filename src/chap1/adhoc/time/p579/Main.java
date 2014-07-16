package chap1.adhoc.time.p579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;

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
				ret[i] = Integer.parseInt(str[i].trim());
		}
		return ret;
	}
	static List<Integer> giveArrayList(String[] str) {
		List<Integer> ret = new ArrayList<>();
		for(int i = 0; i < str.length; i++) {
			if(str[i].compareTo("") != 0)
				ret.add(Integer.parseInt(str[i].trim()));
		}
		return ret;
	}
	
	static int process(String line) {
		if(line.trim().compareTo("0:00") == 0)
			return 0;
		
		String[] inp = line.trim().split(":");
		int[] arr = {Integer.parseInt(inp[0]), Integer.parseInt(inp[1])};
		if(arr[0] == 0 && arr[1] == 0)
			return 0;
		int mul = 1000, max = 60 * mul;
		double h1 = ((double)arr[0] * 5* mul) + ((double)arr[1]*mul/12);
		double h2 = arr[1] * mul;
		double ans = 0;
		if(h1 > h2) 
			ans = h1 - h2;
		else
			ans = h2 - h1;
		
		ans = Math.min(ans, max - ans);
		ans *= 6;
		ans /= 1000;
		int a = (int) ans;
		System.out.println(String.format("%.3f", ans));
		return 1;
	}
	public static String twoPlaces(int val) {
		return ("000" + val).substring(Integer.toString(val).length());
	}

}
