package chap1.adhoc.game.chess.p11494;

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
		int[] arr = giveArray(line.trim().split(" "));
		if(arr[0] == 0 && arr[1] == 0 && arr[2] == 0 && arr[3] == 0)
			return 0;
		
		int dx = Math.abs(arr[0] - arr[2]);
		int dy = Math.abs(arr[1] - arr[3]);
		
		int cnt = 0;
		if(dx > 0  || dy > 0) {
			if(dx > 0 && dy > 0) {
				dx -= dy; dy = 0;
				cnt++;				
			}
			if(dx != 0  || dy != 0) {
				cnt++;
			}
		}
		System.out.println(cnt);
		return 1;
	}

}
