package chap1.adhoc.timewaster.p12060;

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
		int[] arr = giveArray(line.trim().split(" "));
		if(arr.length == 1 && arr[0] == 0)
			return 0;
		int sum = -arr[0], cnt = arr[0];
		for(int val : arr) {
			sum += val;
		}
		int a = Math.abs(sum) / cnt, b = Math.abs(sum) % cnt, c = cnt;
		int sign = sum < 0 ? -1 : 1;
		System.out.println(String.format("Case %d:", casen));
		String pref = sign == -1 ? "- " : "";
		if(b == 0) {
			System.out.println(pref+a);
		}
		else {
			int gcd = gcd(b, c);
			b /= gcd;
			c /= gcd;
			String val = a == 0 ? "" : Integer.toString(a);
			String dash = "";
			int i = Integer.toString(c).length();
			while(i-- > 0) {
				dash+="-";
			}
			String mid = pref+val+dash;
			String top = "", bott = "";
			i = mid.length() - Integer.toString(b).length();
			while(i-- > 0) {
				top+=" ";
			}
			top+=b;
			i = mid.length() - Integer.toString(c).length();
			while(i-- > 0) {
				bott+=" ";
			}
			bott+=c;
			System.out.println(top+"\n"+mid+"\n"+bott);
		}
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
