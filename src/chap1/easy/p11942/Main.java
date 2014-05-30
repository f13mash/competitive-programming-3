package chap1.easy.p11942;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

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
		System.out.println("Lumberjacks:");
		for(int i = 0; i < n; i++) {
			int[] arr = giveArray(readLine().trim().split(" "));
			boolean v = true;
			for(int j = 1; j < arr.length - 1; j++) {
				int d1 = arr[j] - arr[j-1], d2 = arr[j+1] - arr[j];
				if((d1 * d2) <= 0) {
					v = false;
					break;
				}
			}
			if(v)
				System.out.println("Ordered");
			else
				System.out.println("Unordered");
		}
		return 0;

	}

}
