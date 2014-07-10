package chap1.adhoc.game.others.hard.p1384;

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
			int[] arr = giveArray(readLine().trim().split(" "));
			int m = arr[0], n = arr[1], d = arr[2];
			char[][][] mp = new char[2][m + 2][n + 2];
			for(int a = 1; a <= m; a++) {
				char[] inp = readLine().trim().toCharArray();
				for(int b = 1; b <= n; b++) {
					mp[0][a][b] = inp[b-1];
				}
			}
			for(int k = 1; k <= d; k++) {
				for(int a = 1; a <= m; a++) {
					for(int b = 1; b <= n; b++) {
						switch (mp[(k-1)%2][a][b]) {
						case 'R':
							if(mp[(k-1)%2][a-1][b] == 'P' || mp[(k-1)%2][a+1][b] == 'P' || mp[(k-1)%2][a][b-1] == 'P' || mp[(k-1)%2][a][b+1] == 'P') {
								mp[(k)%2][a][b] = 'P';
							}
							else
								mp[(k)%2][a][b] = mp[(k-1)%2][a][b];
							break;
						case 'P':
							if(mp[(k-1)%2][a-1][b] == 'S' || mp[(k-1)%2][a+1][b] == 'S' || mp[(k-1)%2][a][b-1] == 'S' || mp[(k-1)%2][a][b+1] == 'S') {
								mp[(k)%2][a][b] = 'S';
							}
							else
								mp[(k)%2][a][b] = mp[(k-1)%2][a][b];
							break;
						case 'S':
							if(mp[(k-1)%2][a-1][b] == 'R' || mp[(k-1)%2][a+1][b] == 'R' || mp[(k-1)%2][a][b-1] == 'R' || mp[(k-1)%2][a][b+1] == 'R') {
								mp[(k)%2][a][b] = 'R';
							}
							else
								mp[(k)%2][a][b] = mp[(k-1)%2][a][b];
							break;

						default:
							break;
						}
					}	
				}
			}
			if(i > 0)
				System.out.println();
			for(int c = 1; c <= m; c++) {
				System.out.println(new String(mp[d%2][c], 1, n));
			}
		}
		return 0;
	}

}
