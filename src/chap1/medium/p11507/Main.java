package chap1.medium.p11507;

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
	
	static int ind = 0;
	static int process(String line) {
		int l = Integer.parseInt(line.trim());
		if(l == 0)
			return 0;
		String[] commands = readLine().trim().split(" ");
		
		Map<String, String> sol = new HashMap<String, String>();
		
		sol.put("+x +y", "+y");
		sol.put("+x -y", "-y");
		sol.put("+x +z", "+z");
		sol.put("+x -z", "-z");
		
		sol.put("+y +y", "-x");
		sol.put("+y -y", "+x");
		sol.put("+y +z", "+y");
		sol.put("+y -z", "+y");
		
		sol.put("+z +z", "-x");
		sol.put("+z -z", "+x");
		sol.put("+z +y", "+z");
		sol.put("+z -y", "+z");
		
		char dir = 'x'; boolean sign = true;
		for(int i = 0; i < l - 1; i++) {
			if(commands[i].compareTo("No") == 0)
				continue;
			String key = "+" + dir + " "+ commands[i].trim();
			String res = sol.get(key);
			dir = res.charAt(1);
			sign = sign ? res.charAt(0) == '+' : res.charAt(0) != '+'; 
		}
		
		System.out.println((sign ? "+" : "-") + dir);
		
		return 1;
	}
	

}
