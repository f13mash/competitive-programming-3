package chap1.adhoc.game.chess.p278;

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
		int cases = Integer.parseInt(line.trim());
		for(int k = 0; k < cases; k++) {
			String[] arr = readLine().trim().split(" ");
			int play = arr[0].charAt(0);
			int m = Integer.parseInt(arr[1]), n = Integer.parseInt(arr[2]);
			int ans = 0;
			int a, b;
			switch (play) {
			case 'Q':
			case 'r':
				ans = Math.min(m, n);
				break;
			case 'K':
				a = (m+1) / 2;
				b = (n+1) / 2;
				ans = a*b;
				break;
			case 'k':
				ans = ((m + 1) / 2) * ((n + 1) / 2) + ((m / 2) * (n / 2));
				break;
			default:
				break;
			}
			System.out.println(ans);
		}
		return 0;
	}

}
