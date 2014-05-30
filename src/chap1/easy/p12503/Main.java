package chap1.easy.p12503;

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
			int cnt = Integer.parseInt(readLine().trim());
			boolean[] cmds = new boolean[cnt];
			long p = 0;
			for(int j = 0; j < cnt; j++) {
				String str = readLine().trim();
				switch (str) {
				case "LEFT":
					cmds[j] = false;
					break;
				case "RIGHT":
					cmds[j] = true;
					break;
				default:
					cmds[j] = cmds[Integer.parseInt(str.split(" ")[2]) - 1];
					break;
				}
				
				if(cmds[j]) {
					p++;
				}
				else {
					p--;
				}
			}
			System.out.println(p);
		}
		return 0;

	}

}
