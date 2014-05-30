package chap1.supereasy.p12250;

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
		int ind = 1;
		while(true) {
			if(line.compareTo("#") == 0)
				break;
			
			switch (line.trim()) {
			case "HELLO":
				System.out.println("Case "+ind+": ENGLISH");
				break;
			case "HOLA":
				System.out.println("Case "+ind+": SPANISH");
				break;
			case "HALLO":
				System.out.println("Case "+ind+": GERMAN");
				break;
			case "BONJOUR":
				System.out.println("Case "+ind+": FRENCH");
				break;
			case "CIAO":
				System.out.println("Case "+ind+": ITALIAN");
				break;
			case "ZDRAVSTVUJTE":
				System.out.println("Case "+ind+": RUSSIAN");
				break;

			default:
				System.out.println("Case "+ind+": UNKNOWN");
				break;
			}
			
			ind++;
			line = readLine().trim();
		}
		return 0;

	}

}
