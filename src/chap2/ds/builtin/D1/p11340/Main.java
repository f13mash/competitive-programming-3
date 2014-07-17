package chap2.ds.builtin.D1.p11340;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
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
		int cases = Integer.parseInt(line.trim());
		while(cases-- > 0) {
			int[] cost = new int[Character.MAX_VALUE + 1];
			int n = Integer.parseInt(readLine().trim());
			for(int i = 0; i < n; i++) {
				String[] arr = readLine().trim().split(" ");
				cost[arr[0].charAt(0)] = Integer.parseInt(arr[1]);
			}
			int k = Integer.parseInt(readLine().trim());
			int ans = 0;
			for(int i = 0; i < k; i++) {
				char[] arr = readLine().toCharArray();
				for(char c : arr) {
					ans += cost[c];
				}
			}
			double da = (double) ans;
			da /= 100;
			System.out.println(String.format("%.2f$", da));
		}
		return 0;
	}
	public static String threePlaces(int val) {
		return ("   " + val).substring(Integer.toString(val).length());
	}

}
