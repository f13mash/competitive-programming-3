package chap1.adhoc.timewaster.p12085;

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
	static int casen = 1;
	static int process(String line) {
		int cnt = Integer.parseInt(line.trim());
		if(cnt == 0)
			return 0;
		String[] inp = new String[cnt];
		for(int i = 0; i < cnt; i++) {
			inp[i] = readLine().trim();
		}
		int[] prev = new int[cnt];
		System.out.println(String.format("Case %d:", casen));
		
		for(int i = 0; i < cnt;) {
			//System.out.println("start : "+i);
			int n = i + 1;
			while(n < cnt) {
				if(inp[n].length() == inp[i].length()) {
					long a = Long.parseLong(inp[i]), b = Long.parseLong(inp[n]);
					if((b - a) == (n - i)) {
						n++;
					}
					else {
						break;
					}
				}
				else {
					break;
				}
			}
			prev[i] = n - 1 - i;
			
			if(prev[i] == 0) {
				System.out.println(inp[i].trim());
			}
			else {
				long a = Long.parseLong(inp[i]);
				long b = a + prev[i];
				long comm = 1;
				while((a/comm) != (b/comm)) {
					comm*=10;
				}
				System.out.println(inp[i].trim()+"-"+(b % comm));
			}
			
			i = n;
			//System.out.println(i+" : "+cnt);
		}
		System.out.println();
		casen++;
		return 1;
	}
	public static String threePlaces(int val) {
		return ("   " + val).substring(Integer.toString(val).length());
	}
	public static int gcd(int a, int b) {
		if(a > b){
			int t = a;
			a = b; 
			b = t;
		}
		if(a == 0)
			return b;
		return gcd(a, b-a);
	}

}
