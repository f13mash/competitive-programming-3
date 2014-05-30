package chap1.supereasy.p11547;

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
		int n = Integer.parseInt(line.trim());
		
		if(n == 0)
			return 0;
		
		for(int i = 0; i < n; i++) {
			long a = Long.parseLong(readLine().trim());
			a *= 567;
			a /= 9;
			a += 7492;
			a*= 235;
			a /= 47;
			a -= 498;
			
			a /= 10;
			System.out.println(Math.abs(a%10));
		}
		return 0;

	}

}
