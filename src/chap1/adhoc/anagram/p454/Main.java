package chap1.adhoc.anagram.p454;

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
		int cases = Integer.parseInt(line.trim().toString());
		String s = readLine();
		boolean prev = false;
		while(cases-- > 0) {
			StringBuffer out = new StringBuffer();
			Map<String, String> hs = new HashMap<>();
			List<String> words = new ArrayList<String>();
			s = readLine();
			while(!(s == null || s.trim().length() == 0)) {
				char[] arr = s.replaceAll(" ", "").toCharArray();
				Arrays.sort(arr);
				String key = new String(arr);
				words.add(s);
				hs.put(s, key);
				s=readLine();
			}
			Collections.sort(words);
			int sz = words.size();
			for(int i = 0; i < sz; i++) {
				for(int j = i+1; j < sz; j++) {
					String a = words.get(i), b = words.get(j);
					if(hs.get(a).compareTo(hs.get(b)) == 0) {
						out.append(a+" = "+b+"\n");
					}
				}
			}
			System.out.print(out.toString());
			if(cases != 0)
				System.out.println();
		}
		return 0;
	}
	

}
