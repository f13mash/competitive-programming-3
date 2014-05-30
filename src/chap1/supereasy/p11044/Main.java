package chap1.supereasy.p11044;

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
		int n = Integer.parseInt(line);
		for(int i = 0; i < n; i++) {
			String[] splts = readLine().trim().split(" ");
			int a = Integer.parseInt(splts[0]);
			int b = Integer.parseInt(splts[1]);
			
			a -= 2;
			b -= 2;
			
			a = a/3 + (a%3 == 0 ? 0 : 1);
			b = b/3 + (b%3 == 0 ? 0 : 1);
			
			System.out.println(a*b);
		}
		return 0;

	}

}
