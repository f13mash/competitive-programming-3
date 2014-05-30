package chap1.supereasy.p10911;

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
            	System.out.print(out);
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
	
	static double[][] costm = null;
	static int casen = 1;
	static double best = Double.MAX_VALUE;
	
	static int process(String line) {
		int n = 2 * Integer.parseInt(line.trim());
		if(n == 0)
			return 0;
		int[] x = new int[n];
		int[] y = new int[n];
		costm = new double[n][n];
		
		
		for (int i = 0; i < n; i++) {
			String[] str = readLine().trim().split(" ");
			x[i] = Integer.parseInt(str[1]);
			y[i] = Integer.parseInt(str[2]);
		}
		for (int i = 0; i < n; i++) {
			for(int j = 0; j < i; j++) {
				int d1 = x[i] - x[j], d2 = y[i] - y[j]; 
				costm[i][j] = costm[j][i]= Math.sqrt(d1*d1 + d2*d2);
			}
		}
		int lim = 1 << n, cnt = n/2;
		boolean[] vis = new boolean[lim];
		best = Double.MAX_VALUE;
		for(int i = 0; i < lim; i++) {
			if(vis[i])
				continue;
			int set = 0;
			for(int j = 0; j < n; j++) {
				if((i & (1 << j)) > 0) 
					set++;
			}
			if(set != cnt)
				continue;
			int o = 1 << n;
			o -= 1;
			o ^= i;
			vis[o] = vis[i] = true;
			best = Math.min(best, eval(i, o, n));
			
		}
		DecimalFormat decim = new DecimalFormat("0.00");
		out += "Case "+casen++ +": "+decim.format((double)Math.round(best * 100) / 100)+"\n";
		return 1;

	}
	
	static double eval(int a, int b, int n) {
		int[] fix = new int[n/2];
		int[] fill = new int[n/2];
		for(int i = 0, ind = 0; i < n; i++) {
			if((a & (1 << i)) > 0)
				fix[ind++] = i;
		}
		
		for(int i = 0, ind = 0; i < n; i++) {
			if((b & (1 << i)) > 0)
				fill[ind++] = i;
		}
		
		int state = 0;
		int[] ans = new int[n/2];
		return perm(0, state, ans, fill, fix, 0);
		
	}
	
	static double perm(int pos, int state, int[] ans, int[] fill, int[] fix, double c) {
		if(pos == fill.length) {
			best = Math.min(best, c);
			return cost(ans, fix);
		}
		if(c > best)
			return best;
		double cost = Double.MAX_VALUE;
		for(int i = 0; i < fill.length; i++) {
			if((state & (1 << i)) == 0) {
				ans[pos] = fill[i];
				state |= (1 << i);
				cost = Math.min(cost, perm(pos + 1, state, ans, fill, fix, c + costm[fix[i]][fill[i]]));
				state ^= (1 << i);
			}
		}
		return cost;
	}
	
	static double cost(int[] a, int[] b) {
		double cost = 0;
		for(int i = 0; i < a.length; i++) {
			cost += costm[a[i]][b[i]];
		}
		return cost;
	}
}
