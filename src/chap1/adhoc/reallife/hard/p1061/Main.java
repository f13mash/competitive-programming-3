package chap1.adhoc.reallife.hard.p1061;

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
	static int cs = 0;
	static int process(String line) {
		if(line == null || line.trim().length() == 0)
			return 1;
		cs++;
		if(line.trim().compareTo("E N D") == 0)
			return 0;
		String[] inp = line.trim().replaceAll("[\\ ]+", " ").split(" ");
		int[] type = new int[3];
		int[] fact = new int[3];
		int miss = 0;
		for(int i = 0; i < 3; i++) {
			if(inp[i].trim().compareTo("?") == 0) {
				miss = i;
			}
			else {
				char[] arr = inp[i].trim().toCharArray();
				if(arr[arr.length - 1] == '+') {
					fact[i] = 1;
				}
				else {
					fact[i] = -1;
				}
				if(arr.length == 2) {
					switch (arr[0]) {
					case 'A':
						type[i] = 1; 
						break;
					case 'B':
						type[i] = 2; 
						break;
					case 'O':
						type[i] = 3;
					default:
						break;
					}
				}
				else {
					type[i] = 0;
				}
			}
		}
		char[][] mp = {
				{'A', 'B'},
				{'A', 'O'},
				{'B', 'O'},
				{'O', 'O'}
		};

		Set<String> res = new HashSet<String>();
		int sign = 0;
		boolean imp = false;
		if(miss == 2) {
			char[] a = mp[type[0]], b = mp[type[1]];
			for(char ca : a) {
				for(char cb : b) {
					String add = null;
					if(cb >= ca)
						add = ca + "" + cb;
					else 
						add = cb + "" + ca;
					if(add.endsWith("O")) {
						add = add.substring(0, 1);
					}
					if(add.compareTo("AA") == 0 || add.compareTo("BB") == 0)
						add = add.substring(0, 1);
					res.add(add);
				}
			}
			if(fact[0] == -1 && fact[1] == -1) {
				sign = -1;
			}
		}
		else {
			int av = miss + 1;
			imp = true;
			av %= 2;
			char[] a = mp[type[av]], b = mp[type[2]];
			Set<Character> req = new HashSet<>();
			req.add(b[0]);req.add(b[1]);
			for(char ca : a) {
				for(char cb : b) {
					if(ca == cb){
						imp = false;
						req.remove(ca);
					}
				}
			}
			if(fact[av] == -1 && fact[2] == 1) {
				sign = 1;
			}
			
			if(!imp) {
				if(req.size() == 0) {
					req.add(a[0]);
					req.add(a[1]);
				}
				boolean isO = type[2] == 3;
				for(char ca : req) {
					char[] arr = {'A','B','O'};
					for(char cb : arr) {
						String add = null;
						if(cb >= ca)
							add = ca + "" + cb;
						else 
							add = cb + "" + ca;
						if(add.endsWith("O")) {
							add = add.substring(0, 1);
						}
						if(add.compareTo("AA") == 0 || add.compareTo("BB") == 0)
							add = add.substring(0, 1);
						if(isO && add.compareTo("AB") == 0)
							continue;
						res.add(add);
					}
				}
				if(!isO && type[av] == 0)
					res.add("AB");
			}
		}
		String[] out = inp;
		if(imp) {
			out[miss] = "IMPOSSIBLE";
		}
		else if(res.size() == 1 && sign != 0) {
			out[miss] = res.iterator().next() + (sign > 0 ? "+" : "-");
		}
		else {
			out[miss] = "";
			List<String> al = new ArrayList<>(res);
			Collections.sort(al);
			for(String c : al) {
				if(sign == 0) {
					out[miss] += c+"-"+" ";
					out[miss] += c+"+"+" ";
				} else {
					out[miss] += c + (sign > 0 ? "+" : "-") +" ";		
				}
			}
			out[miss] = "{"+out[miss].trim().replaceAll(" ", ", ")+"}";
		}
		System.out.println(String.format("Case %d: %s %s %s", cs, out[0], out[1], out[2]));
		return 1;
	}

}
