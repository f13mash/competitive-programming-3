package chap1.easy.p10114;

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
		line = line.trim();
		int n = Integer.parseInt(line.trim());
		
		for(int i = 0; i < n; i++) {
			String s = readLine().trim();
			
			if(s.compareTo("1") == 0 || s.compareTo("4") == 0 || s.compareTo("78") == 0) {
				System.out.println('+');
				continue;
			}
			if(s.endsWith("35")) {
				System.out.println('-');
				continue;
			}
			if (s.startsWith(Integer.toString(190))) {
				System.out.println('?');
				continue;
			}
			System.out.println('*');
		}
		
		return 0;

	}

}
