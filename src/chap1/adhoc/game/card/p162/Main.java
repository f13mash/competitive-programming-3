package chap1.adhoc.game.card.p162;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	
	static List<Integer> parseCards(String cards) {
		String[] arr = cards.trim().split(" ");
		List<Integer> ll = new ArrayList<Integer>();
		for(String s : arr) {
			ll.add(cardValue(s));
		}
		
		return ll;
	}
	
	static int cardValue(String card) {
		char[] arr = card.toCharArray();
		int base = 0, val = 0;
		switch (arr[0]) {
			case 'S':
				base = 0;
				break;
			case 'H':
				base = 1;
				break;
			case 'D':
				base = 2;
				break;
			case 'C':
				base = 3;
				break;
			default:
				break;
		}
		
		if(arr[1] <= '9' && arr[1] >= 2) {
			val = arr[1] - '1';
		}
		else {
			switch(arr[1]) {
				case 'T':
					val = 9;
					break;
				case 'A':
					val = 0;
					break;
				case 'J':
					val = 10;
					break;
				case 'Q':
					val = 11;
					break;
				case 'K':
					val = 12;
					break;
			}
		}
		base = 0;
		return base*13 + val;
	}
	static boolean isFace(int c) {
		if(c %13 == 0 || (c % 13) > 9)
			return true;
		return false;
	}
	static int process(String line) {
		if(line.compareTo("#") == 0)
			return 0;
		
		List<Integer>[] play = new ArrayList[2];
		play[0] = new ArrayList<>();
		play[1] = new ArrayList<>();
		List<Integer> com = new ArrayList<>();
		int sz = 4;
		for(int i = 0; i < sz; i++) {
			com.addAll(parseCards(line));
			if(i < (sz - 1))
				line = readLine();
		}
		
		int ind = 0;
		
		while(com.size() > 0) {
			play[ind % 2].add(com.remove(0));
			ind ++;
			ind %= 2;
		}
		Collections.reverse(play[0]);Collections.reverse(play[1]);
/*
System.out.println(play[0]);
System.out.println(play[1]);
System.out.println(com);*/
		
		int trn = 0, face = 0, oth = 1;
		while(play[trn].size() > 0) {
			
			int v = play[trn].remove(0);
			com.add(v);
			int nface = 0;
			if(v == 0 || (v % 13) > 9) {
				nface = (v - 1 + 13) % 13 - 8;
				face = 0;
			}
			if(face == 0) {
				oth = trn;
				trn = (trn + 1) % 2;
				face = nface;
			}
			else {
				face--;
				if(face == 0) {
					//Collections.reverse(com);
					play[oth].addAll(com);
					com.clear();
					oth = trn;
					trn = (trn + 1) % 2;
				}
			}
		}
		int win = 2, cnt = play[0].size();
		if(play[0].size() == 0) {
			win = 1;
			cnt = play[1].size();
		}
		System.out.println(win + String.format("%3d", cnt));
		return 1;
	}

}
