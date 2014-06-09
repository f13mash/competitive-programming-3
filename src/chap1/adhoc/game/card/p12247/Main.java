package chap1.adhoc.game.card.p12247;

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
	
	static int ind = 0;
	static int process(String line) {
		int[] inp = giveArray(line.trim().split(" "));
		if(inp[0] == 0)
			return 0;
		Arrays.sort(inp, 0, 3);
		Arrays.sort(inp, 3, 5);
		boolean[] used = new boolean[60];
		for(int i : inp) {
			used[i] = true;
		}
		int loses = 0;
		
		int a = 0, b = 2, c = 4;
		
		if(inp[c] > inp[b]) {
			a++;
		}
		else {
			loses++;
			b--;
		}
		
		c--;
		if(inp[c] > inp[b]) {
			a++;
		}
		else {
			loses++;
			b--;
		}
		int ans = -1;
		if(loses <= 1) {
			if(loses == 0) {
				int ind = 0;
				for(int i = 1; i <= 52 ; i++) {
					if(used[i])
						continue;
					ans = i;
					break;
				}
			}
			else {
				ans = inp[1] + 1;
				if(inp[1] > inp[3])
					ans = inp[2] + 1;
				while(used[ans]) {
					ans++;
				}
			}
		}
		
		if(ans > 52)
			ans = -1;
		
		System.out.println(ans);
		return 1;

	}

}
