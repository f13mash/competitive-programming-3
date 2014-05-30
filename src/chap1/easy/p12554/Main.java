package chap1.easy.p12554;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	static int process(String line) {
		int n = Integer.parseInt(line.trim());
		String[] people = new String[n];
		for(int i = 0; i < n; i++) {
			people[i] = readLine().trim();
		}
		String[] song= "Happy birthday to you Happy birthday to you Happy birthday to Rujia Happy birthday to you".trim().split(" ");
		int cnt = 16;
		if(n > 16) {
			cnt = n/16 + (n%16 == 0 ? 0 : 1);
			
			cnt *= 16;
		}
		
		for(int i = 0; i < cnt; i++) {
			System.out.println(people[i % n]+": "+song[i % 16]);
		}
		return 0;

	}

}
