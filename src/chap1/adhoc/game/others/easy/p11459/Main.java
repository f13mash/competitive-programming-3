package chap1.adhoc.game.others.easy.p11459;

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
		int cases = Integer.parseInt(line.trim());
		for(int c = 1; c <= cases; c++) {
			String s = readLine().trim();
			while(s.compareTo("") == 0) {
				s = readLine().trim();
			}
			int[] arr = giveArray(s.split(" "));
			int n = arr[0], b = arr[1], r = arr[2];
			
			int[] jump = new int[101];
			for(int i = 0; i < b; i++) {
				String str = readLine().trim();
				int[] ld = giveArray(str.split(" "));

				jump[ld[0]] = ld[1];
			}
			
			int curr = 0;
			int[] pos = new int[n];
			//Arrays.fill(pos, jump[1] > 0 ? jump[1] : 1);
			Arrays.fill(pos, 1);
			while(r > 0) {
				int val = Integer.parseInt(readLine().trim());
				curr = curr % n;
				pos[curr] += val;
				if(pos[curr] > 100)
					pos[curr] = 100;
				if(jump[pos[curr]] > 0) {
					pos[curr] = jump[pos[curr]];
				}
				if(pos[curr] == 100) {
					break;
				}
				curr++;
				r--;
			}
			r--;
			while(r > 0) {
				readLine();
				r--;
			}
			for(int i = 1; i <= n; i++) {
				System.out.println(String.format("Position of player %d is %d.", i, pos[i - 1]));
			}
		}
		return 0;
	}

}
