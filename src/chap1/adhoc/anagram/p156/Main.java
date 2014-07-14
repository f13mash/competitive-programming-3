package chap1.adhoc.anagram.p156;

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
				ret[i] = Integer.parseInt(str[i]);
		}
		return ret;
	}
	
	
	static int process(String line) {
		Map<String, List<String>> hs = new HashMap<>();
		boolean done = false;
		while(!done) {
			if(line == null || line.trim().length() == 0)
				continue;
			line = line.trim().replaceAll("([ ]+)", " ");
			String[] words = line.split(" ");
			for(String w : words) {
				if(w.compareTo("#") == 0) {
					done = true;
					break;
				}
				char[] arr = w.toLowerCase().toCharArray();
				Arrays.sort(arr);
				String key  = new String(arr);
				if(!hs.containsKey(key)) {
					hs.put(key, new ArrayList<String>());
				}
				hs.get(key).add(w);
			}
			if(done)
				break;
			line = readLine();
		}
		List<String> q = new ArrayList<>();
		for(String key : hs.keySet()) {
			if(hs.get(key).size() == 1) {
				q.add(hs.get(key).get(0));
			}
		}
		Collections.sort(q);
		for(String w : q) {
			System.out.println(w);
		}
		
		return 0;
	}
	

}
