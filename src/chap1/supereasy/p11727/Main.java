package chap1.supereasy.p11727;

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
			String[] splts = readLine().trim().split(" ");
			int[] a = new int[3];
			for(int j = 0; j < 3; j++) {
				a[j] = Integer.parseInt(splts[j]);
			}
			Arrays.sort(a);
			System.out.println("Case "+(i+1)+": "+a[1]);
		}
		return 0;

	}

}
