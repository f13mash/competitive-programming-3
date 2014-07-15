package chap1.adhoc.reallife.easy.p161;

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
		if(line.trim().compareTo("0 0 0") == 0){
			return 0;
		}
		
		List<Integer> lights = new ArrayList<Integer>();
		while(true) {
			List<Integer> inp = giveArrayList(line.trim().split(" "));
			boolean brk = false;
			for(int val : inp) {
				if(val == 0) {
					brk = true;
					break;
				}
				lights.add(val);
			}
			if(brk) 
				break;
			line = readLine();
			
		}

		int ot = 5;
		
		int lim = 5 * 3600;
		boolean[] timeline = new boolean[lim + 102];
		Arrays.fill(timeline, true);
		
		for(int val : lights) {
			int st = val - ot + 1;
			int end = (2*val) - 1;
			while(st <= (lim+100)) {
				for(int j = st; j <= end && j <= (lim+100); j++) {
					timeline[j] = false;
				}
				st += (2*val);
				end += (2*val);
			}
		}
		
		boolean firstOrange = false;
		int ind = -1;
		for(int i = 0; i <= lim; i++) {
			if(!firstOrange) {
				if(!timeline[i])
					firstOrange = true;
			}
			else {
				if(timeline[i] && timeline[i + 1]) {
					ind = i;
					break;
				}
			}
		}
		String out = "Signals fail to synchronise in 5 hours";
		if(ind != -1) {
			out = String.format("%s:%s:%s", twoPlaces(ind/3600), twoPlaces((ind/60)%60), twoPlaces(ind%60));
		}
		System.out.println(out);
		return 1;
	}
	
	public static String twoPlaces(int val) {
		return ("00" + val).substring(Integer.toString(val).length());
	}
	

}
