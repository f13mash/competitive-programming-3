package chap1.adhoc.timewaster.p556;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;

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
			if(str[i].compareTo("") != 0)
				ret[i] = Integer.parseInt(str[i].trim());
		}
		return ret;
	}
	static List<Integer> giveArrayList(String[] str) {
		List<Integer> ret = new ArrayList<>();
		for(int i = 0; i < str.length; i++) {
			if(str[i].compareTo("") != 0)
				ret.add(Integer.parseInt(str[i].trim()));
		}
		return ret;
	}
	
	static int process(String line) {
		int[] arr = giveArray(line.trim().split(" "));
		int m = arr[0], n = arr[1];
		if(m == 0 && n == 0)
			return 0;
		int[][] map = new int[m][n];
		for(int i = 0; i < m; i++) {
			char[] lin = readLine().trim().toCharArray();
			for(int j = 0; j < n; j++) {
				if(lin[j] == '1') {
					map[i][j] = -1;
				}
			}
		}
		int sx = m-1, sy = 0;
		int x = sx, y = sy;
		boolean started = false;
		int curr = 0;
		int[][] dirs = {
				{0, 1}, {-1, 0}, {0, -1}, {1, 0}
		};
		boolean dirChange = true;
		while((!(x==sx && y==sy)) || !started) {
			//System.out.println(x+" : "+y+" : "+curr);
			started = true;
			boolean blocked = false;
			int nx = dirs[curr][0] + x, ny = dirs[curr][1] + y;
			if(nx >= m || ny >= n ||nx < 0 || ny < 0 || map[nx][ny] == -1)
				blocked = true;
			int rx = dirs[(curr-1+4)%4][0] + x, ry = dirs[(curr-1+4)%4][1] + y;
			boolean rblock = false;
			if(rx >= m || ry >= n ||rx < 0 || ry < 0 || map[rx][ry] == -1)
				rblock = true;
			boolean rspace = false;
			if(!rblock && map[rx][ry] >= 0) {
				rspace = true;
			}
			if(rspace && !dirChange) {
				curr--;
				curr+=4;
				curr %= 4;
				dirChange = true;
			}
			else if(!blocked) {
				dirChange = false;
				x = nx; y = ny;
				map[x][y]++;
			}
			else {
				curr++;
				curr+=4;
				dirChange = true;
				curr %= 4;
			}
		}
		int[] res = new int[5];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] >= 0) {
					res[map[i][j]]++;
				}
			}
		}
		System.out.println(String.format("%s%s%s%s%s", threePlaces(res[0]), threePlaces(res[1]), threePlaces(res[2]), threePlaces(res[3]), threePlaces(res[4])));
		return 1;
	}
	public static String threePlaces(int val) {
		return ("   " + val).substring(Integer.toString(val).length());
	}

}
