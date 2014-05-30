package chap1.easy.p621;

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
		String[] splts = line.split(" ");
		
		int dur = Integer.parseInt(splts[0]);
		if(dur < 0)
			return 0;
		
		double dpay = Double.parseDouble(splts[1]);
		double amt = Double.parseDouble(splts[2]);
		int cnt = Integer.parseInt(splts[3]);
		
		double[] dep = new double[dur + 1]; 
		for(int i = 0; i < cnt; i++) {
			String[] s = readLine().trim().split(" ");
			int mnt = Integer.parseInt(s[0]);
			double per = Double.parseDouble(s[1]);
			Arrays.fill(dep, mnt, dur+1, per);
		}
		//System.out.println(Arrays.toString(dep));
		double ded = dur == 0 ? amt : (amt) / dur;
		double pay = amt + dpay , val = amt + dpay;
		for(int i = 0; i <= dur; i++) {
			
			if(i == 0)
				pay -= dpay;
			else
				pay -= ded;
			val -= (val * dep[i]);

			if(pay < val) {
				if(i == 1) {
					System.out.println((i) + " month");
				}
				else {
					System.out.println((i) + " months");
				}
				break;
			}
		}
		
		return 1;

	}

}
