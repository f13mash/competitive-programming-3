package chap1.supereasy.p272;

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
	static boolean uq = false;
	static int process(String line) {
		char[] arr = line.toCharArray();
		StringBuffer out = new StringBuffer();
		for(char c : arr) {
			if(c == '"') {
				if(uq)
					out.append("''");
				else
					out.append("``");
				uq = !uq;
			}
			else
				out.append(c);
		}
		System.out.println(out.toString());
		return 1;

	}

}
