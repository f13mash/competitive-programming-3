package chap1.supereasy.p12289;

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
		
		int n = Integer.parseInt(line.trim());
		for(int i = 0; i < n; i++) {
			char[] arr = readLine().trim().toCharArray();
			if(arr.length == 5) {
				System.out.println(3);
				continue;
			}
			int m = 0;
			char[] one = "one".toCharArray();
			for(int j = 0; j < 3; j++) {
				if(one[j] == arr[j])
					m++;
			}
			if(m >= 2)
				System.out.println(1);
			else
				System.out.println(2);
		}
		return 0;

	}

}
