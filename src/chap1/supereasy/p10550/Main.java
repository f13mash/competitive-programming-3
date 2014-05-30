package chap1.supereasy.p10550;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
	static int process(String line) {
		String[] splts = line.trim().split(" ");
		int[] arr = new int[4];
		boolean brk = true;
		for(int i = 0; i < 4; i++) {
			arr[i] = Integer.parseInt(splts[i]);
			if(arr[i] != 0)
				brk = false;
		}
		if(brk)
			return 0;
		int ans = 1080;
		
		int turn = -(arr[1] - arr[0]) * 9;
		if(turn < 0) 
			turn += 360;
		ans += turn;
		
		turn = -(arr[1] - arr[2]) * 9;
		if(turn < 0) 
			turn += 360;
		ans += turn;
		
		turn = -(arr[3] - arr[2]) * 9;
		if(turn < 0) 
			turn += 360;
		ans += turn;
		System.out.println(ans);
		return 1;

	}

}
