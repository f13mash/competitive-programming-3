package chap1.easy.p12468;

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

		while(true) {
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
	
	static int ind = 0;
	static int process(String line) {
		if(line == null || line.trim().length() == 0) {
			return 1;
		}
		int[] arr = giveArray(line.trim().split(" "));
		if(arr[0] == -1 && arr[1] == -1)
			return 0;
		Arrays.sort(arr);
		int ans = Math.min(Math.abs(arr[1] - arr[0]), Math.abs(arr[0] + 100 - arr[1]));

		System.out.println(ans);
		ind++;
		return 1;

	}

}
