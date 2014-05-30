package chap1.supereasy.p12279;

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
	static int ind = 1;
	static int process(String line) {
		
		int a = Integer.parseInt(line.trim());
		if(a == 0)
			return 0;
		
		String[] splts = readLine().trim().split(" ");
		int cnt = 0;
		for(int i = 0; i < a; i++) {
			if(Integer.parseInt(splts[i]) == 0)
				cnt++;
		}
		System.out.println("Case "+(ind++)+": "+(a - 2*cnt));
		return 1;

	}

}
