package chap1.adhoc.game.others.easy.p489;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	static int process(String line) {
		int c = Integer.parseInt(line.trim());
		if(c == -1)
			return 0;
		String ln = readLine().trim();
		char[] aln = ln.toCharArray();
		String inp = readLine().trim();
		char[] arr = inp.toCharArray();
		boolean[] mp = new boolean[26];
		boolean[] contains = new boolean[26];
		int unq = 0;
		for(char ch : aln) {
			if(!contains[ch - 'a'])
				unq++;
			contains[ch - 'a'] = true;
		}
		int cnt = 0;
		for(int i = 0; i < arr.length; i++) {
			if(i > 0 && mp[arr[i] - 'a'])
				continue;
			if(cnt == 7 || unq == 0)
				break;
			mp[arr[i] - 'a'] = true;
			if(!contains[arr[i] - 'a']) {
				cnt++;
			}
			else {
				unq--;
				contains[arr[i] - 'a'] = false;
			}
		}
		
		String out = "chickened out.";

		if(cnt == 7) {
			
			for(boolean val : contains) {
				if(val) {
					out = "lose.";		
					break;
				}
			}
		}
		else 
			if(unq == 0)
				out = "win.";
		System.out.println("Round "+c);
		System.out.println("You "+out);
		return 1;
	}

}
