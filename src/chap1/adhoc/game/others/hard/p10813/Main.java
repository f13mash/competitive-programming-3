package chap1.adhoc.game.others.hard.p10813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
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
		for(int i =0 ;i < cases; i++) {
			int[][] mp = new int[5][];
			int[][] pos = new int[76][]; 
			for(int j = 0; j < mp.length; j++) {
				mp[j] = giveArray(readLine().trim().split(" "));
			}
			int[] temp = new int[5];
			for(int j = 0; j < 4; j++) {
				temp[j + (j/2 > 0 ? 1 : 0)] = mp[2][j];
			}
			mp[2] = temp;
			for(int j = 0; j < mp.length; j++) {
				for(int k = 0; k < mp.length; k++) {
					pos[mp[j][k]] = new int[]{j, k};
				}
			}
			int[] rows = {0,0,1,0,0};
			int[] columns = {0,0,1,0,0};
			int[] diagonal = {1,1};
			int cnt = 0, ans = 0;
			boolean found = false;
			while(cnt < 75) {
				int[] inp = giveArray(readLine().trim().split(" "));
				cnt+= inp.length;
				if(found)
					continue;
				for(int val : inp) {
					if(found)
						break;
					ans++;
					if(pos[val] != null) {
						rows[pos[val][0]]++;
						columns[pos[val][1]]++;
						if(pos[val][0] == pos[val][1]) {
							diagonal[0]++;
						}
						if(pos[val][0] == (4-pos[val][1])) {
							diagonal[1]++;
						}
					}
					if(!found)
						for(int v : rows) {
							if(v == 5) {
								found = true;
								break;
							}
						}
					if(!found)
						for(int v : columns) {
							if(v == 5) {
								found = true;
								break;
							}
						}
					if(!found)
						for(int v : diagonal) {
							if(v == 5) {
								found = true;
								break;
							}
						}
				}
			}
			System.out.println(String.format("BINGO after %d numbers announced", ans));
		}
		return 0;
	}

}
