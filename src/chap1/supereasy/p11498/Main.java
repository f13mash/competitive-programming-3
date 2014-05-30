package chap1.supereasy.p11498;

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
		
		String[] div = readLine().trim().split(" ");
		int j = Integer.parseInt(div[0]), k = Integer.parseInt(div[1]);
		
		for(int i = 0; i < n; i++) {
			String[] splts = readLine().trim().split(" ");
			int a = Integer.parseInt(splts[0]), b = Integer.parseInt(splts[1]);
			if(a == j || b == k) {
				System.out.println("divisa");
				continue;
			}
			if(a < j) {
				if(b < k) {
					System.out.println("SO");
				}
				else {
					System.out.println("NO");
				}
			}
			else {
				if(b < k) {
					System.out.println("SE");
				}
				else {
					System.out.println("NE");
				}
			}
		}
		return 1;

	}

}
