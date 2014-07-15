package chap1.adhoc.reallife.easy.p637;

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
		int val = Integer.parseInt(line.trim());
		if(val == 0)
			return 0;
		int pos = val + (4-(val%4))%4;
		
		int sheets = pos/4;

		String blnk = "Blank";
		
		System.out.println(String.format("Printing order for %d pages:", val));
		for(int i = 1; i <= sheets; i++) {
			int[] front = {pos +2 - (i*2), (i*2) - 1};
			int[] back = {front[1] + 1, front[0] - 1};
			front[0] = front[0] > val ? 0 : front[0];
			front[1] = front[1] > val ? 0 : front[1];
			back[0] = back[0] > val ? 0 : back[0];
			back[1] = back[1] > val ? 0 : back[1];
			if(front[0] > 0 || front[1] > 0) {
				System.out.println(String.format("Sheet %d, front: %s, %s", i, front[0] == 0 ? blnk : front[0], front[1] == 0 ? blnk : front[1]));
			}
			if(back[0] > 0 || back[1] > 0) {
				System.out.println(String.format("Sheet %d, back : %s, %s", i, back[0] == 0 ? blnk : back[0], back[1] == 0 ? blnk : back[1]));
			}
		}
		
		return 1;
	}

}
