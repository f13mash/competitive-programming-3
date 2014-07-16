package chap1.adhoc.time.p11947;

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
		int[][] data = {
				{21, 0}, 
				{20, 1},
				{21, 2},
				{21, 3},
				{22, 4},
				{22, 5},
				{23, 6},
				{22, 7},
				{24, 8},
				{24, 9},
				{23, 10},
				{23, 11}
		};
		String[] sign= {"Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn"};
		for(int c = 1; c <= cases; c++) {
			String str = readLine().trim();
			int month = Integer.parseInt(str.substring(0, 2));
			int day = Integer.parseInt(str.substring(2, 4));
			int year = Integer.parseInt(str.substring(4));
			Calendar gc = new GregorianCalendar(year, month - 1, day);
			gc.add(Calendar.WEEK_OF_MONTH, 40);
			month = gc.get(gc.MONTH); day = gc.get(gc.DAY_OF_MONTH); year = gc.get(gc.YEAR);
			String s = sign[sign.length - 1];
			for(int i = 0; i < data.length; i++) {
				int n = (i + 1)%data.length;
				if((month == data[i][1] && day >= data[i][0]) || month > data[i][1])
					if(month < data[n][1] || (month == data[n][1] && day < data[n][0])) {
						s = sign[i];
						break;
					}
			}
			
			System.out.println(c+" "+twoPlaces(month+1)+"/"+twoPlaces(day) + "/" +fourPlaces(year)+" "+s.toLowerCase());
		}
		return 0;
	}
	public static String twoPlaces(int val) {
		return ("00" + val).substring(Integer.toString(val).length());
	}
	public static String fourPlaces(int val) {
		return ("0000" + val).substring(Integer.toString(val).length());
	}
}
