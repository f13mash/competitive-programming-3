package chap1.medium.p119;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			ret[i] = Integer.parseInt(str[i]);
		}
		return ret;
	}
	
	static boolean notfirst = false;
	static int process(String line) {
		if(line == null || line.trim().length() == 0)
			return 0;
		int n = Integer.parseInt(line.trim());
		String[] p = readLine().trim().split(" ");
		
		Map<String, Long> mp = new HashMap<>();
		for(String str : p) {
			mp.put(str, (long) 0);
		}
		
		for(int i = 0; i < n; i++) {
			String[] splts = readLine().trim().split(" ");
			String k= splts[0].trim();
			long amt = Long.parseLong(splts[1].trim());
			int cnt = Integer.parseInt(splts[2]);
			
			long keep = -amt, dist = 0;
			if(cnt > 0) {
				keep += amt % cnt;
				dist = amt / cnt;
			}
			else {
				keep = 0;
			}
			for(int j = 3; j < (3 + cnt); j++) {
				String s = splts[j].trim();
				mp.put(s, mp.get(s) + dist);
			}
			mp.put(k, mp.get(k) + keep);
		}
		if(notfirst)
			System.out.println();
		else
			notfirst = true;
		for(String s : p) {
			System.out.println(s + " " + mp.get(s));
		}
		return 1;

	}

}
