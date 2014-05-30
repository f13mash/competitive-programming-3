package chap1.supereasy.p12577;

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
	
	static int[] giveArray(String[] str) {
		int[] ret = new int[str.length];
		for(int i = 0; i < str.length; i++) {
			ret[i] = Integer.parseInt(str[i]);
		}
		return ret;
	}
	
	static int ind = 1;
	static int process(String line) {
		
		for(int i = 0; true; i++) {
			String str= line.trim();
			
			switch (str) {
			case "Hajj":
				System.out.println("Case "+(ind++)+": Hajj-e-Akbar");
				break;
			case "Umrah":
				System.out.println("Case "+(ind++)+": Hajj-e-Asghar");
				break;
			case "*": 
				return 0;
			default:
				break;
			}
			line = readLine();
		}

	}

}
