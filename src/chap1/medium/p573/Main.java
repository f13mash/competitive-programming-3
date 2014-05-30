package chap1.medium.p573;

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
	
	static int process(String line) {
		int[] inp = giveArray(line.trim().split(" " ));
		
		if(inp[0] == 0)
			return 0;
		
		int h = inp[0] * 100, u = inp[1] * 100, d = inp[2] * 100, f = inp[3], les = f * u / 100;
		
		int curr = 0;
		int days = 0;
		for(; ; days++) {
			int gain = u - (les * (days));
			if(gain > 0)
				curr += gain;
			if(curr > h) {
				System.out.println("success on day "+(days + 1));
				break;
			}
			curr -= d;
			if(curr < 0) {
				System.out.println("failure on day "+(days + 1));
				break;
			}
		}
		
		
		return 1;

	}

}
