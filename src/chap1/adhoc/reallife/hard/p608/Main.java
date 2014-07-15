package chap1.adhoc.reallife.hard.p608;

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
		int cases = Integer.parseInt(line.trim());
		while(cases-- > 0) {
			String[][] inp = new String[3][];
			Set<Character>[] ss = new HashSet[3];
			Set<Character> global = new HashSet<>();
			char[] arra = "ABCDEFGHIJKL".toCharArray();
			int[] cnts = new int[12];
			Set<Character> valid = new HashSet<Character>();
			for(char a : arra) {
				global.add(a);
			}
			for(int i = 0; i < 3; i++) {
				inp[i] = readLine().trim().split(" ");
				ss[i] = new HashSet<>();
				char[] arr;
				arr = inp[i][0].toCharArray();
				for(char a : arr) {
					ss[i].add(a);
				}
				arr = inp[i][1].toCharArray();
				for(char a : arr) {
					ss[i].add(a);
				}
				
				if(inp[i][2].trim().compareTo("even") == 0) {
					valid.addAll(ss[i]);
				}
				else {
					int mul = 1;
					if(inp[i][2].trim().compareTo("up") == 0) {
						mul = -1;
					}
					arr = inp[i][0].toCharArray();
					for(char a : arr) {
						cnts[a-'A'] += mul;
					}
					arr = inp[i][1].toCharArray();
					for(char a : arr) {
						cnts[a-'A'] -= mul;
					}
				}
			}
			for(char a : valid) {
				cnts[a-'A'] = 0;
			}
			String weight = "heavy";
			char ans = 0;
			int val = 0;
			for(int i = 0; i < cnts.length; i++) {
				if(cnts[i] !=0 && Math.abs(cnts[i]) > val) {
					if(cnts[i] > 0)
						weight = "light";
					ans = (char) (i + 'A');
					val = Math.abs(cnts[i]);
				}
			}
			System.out.println(String.format("%s is the counterfeit coin and it is %s.", ""+ans, weight));
			//System.out.println(Arrays.toString(cnts));
		}
		return 0;
	}

}
