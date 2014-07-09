package chap1.adhoc.game.others.hard.p584;

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
			if(str[i].compareTo("") != 0)
				ret[i] = Integer.parseInt(str[i]);
		}
		return ret;
	}
	static int process(String line) {
		if(line.trim().compareTo("Game Over") == 0)
			return 0;
		String[] arr = line.trim().split(" ");
		int[] scores = new int[arr.length];
		int[] carry = new int[arr.length];
		int f = 0, d = 0, end = arr.length;
		for(int i = 0; i < arr.length; i++) {
			char c = arr[i].charAt(0);
			if(f == 10)
				end = Math.min(end, i);
			if(Character.isDigit(c)) {
				scores[i] = c - '0';
				d++;
				if(d == 2) {
					d = 0;
					f++;
				}
			}
			else {
				switch (c) {
				case 'X':
					scores[i] = 10;
					if((i + 2) < arr.length)
						carry[i] = 2;

					d = 0;
					f++;
					break;
					
				case '/':
					scores[i] = 10 - scores[i-1];
					if((i + 1) < arr.length)
						carry[i] = 1;

					d = 0;
					f++;
					break;
				default:
					break;
				}
			}
		}
		int ans = 0, bonus = 0;
		for(int i = 0; i < scores.length; i ++) {
			if(i < end) ans += scores[i];
			if(carry[i] > 0) {
				bonus += scores[i+1];
			}
			if(carry[i] > 1) {
				bonus += scores[i+2];
			}
		}
		//System.out.println(ans + " : " + bonus + " : "+Arrays.toString(carry)+" : "+end);
		System.out.println(ans + bonus);
		return 1;
	}

}
