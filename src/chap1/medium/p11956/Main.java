package chap1.medium.p11956;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		int cases = Integer.parseInt(line.trim());
		
		for(int i = 0; i < cases; i++) {
			char[] inst = readLine().trim().toCharArray();
			int size = 100;
			int[] mem = new int[size];
			int ind = 0;
			String out = "Case "+(i+1)+":";
			for(char c : inst) {
				switch (c) {
				case '>':
					ind++;
					break;
				case '<':
					ind--;
					break;
				case '+':
					mem[ind]++;
					break;
				case '-':
					mem[ind]--;
					break;
				case '.':
					//out += " "+getHexString(mem[ind]);
					break;
				default:
					break;
				}
				ind += size;
				ind %= size;
				mem[ind] += 256;
				mem[ind] %= 256;
			}
			for(int v : mem) {
				out += " "+getHexString(v);
			}
			System.out.println(out);
		}
		
		return 0;
	}
	
	public static String getHexString(int val) {
		int a = val / 16;
		int b = val % 16;
		String str = "";
		
		if(a < 10)
			str += a;
		else
			str += (char)(a - 10 + 'A') + "";
		

		if(b < 10)
			str += b;
		else
			str += (char)(b - 10 + 'A') + "";
		
		return str;
	}

}
