package chap1.easy.p12157;

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
		for(int i = 0; i < n; i++) {
			int calls = Integer.parseInt(readLine().trim());
			int[] dur = giveArray(readLine().trim().split(" "));
			int cost1 = 0, cost2 = 0;
			for(int t : dur) {
				cost1 += (t/30) + 1; 
				cost2 += (t/60) + 1;
			}
			cost1 *= 10;
			cost2 *= 15;
			
			if(cost1 < cost2) {
				System.out.println("Case "+(i + 1)+": Mile "+cost1);
			}
			else
				if(cost1 > cost2) {
					System.out.println("Case "+(i + 1)+": Juice "+cost2);
				}
				else
					System.out.println("Case "+(i + 1)+": Mile Juice "+cost2);
		}
		return 0;

	}

}
