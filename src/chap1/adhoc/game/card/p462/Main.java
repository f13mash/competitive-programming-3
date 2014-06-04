package chap1.adhoc.game.card.p462;

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
		switch (arr[1]) {
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
		
		if(arr[0] <= '9' && arr[0] >= 2) {
			val = arr[0] - '1';
		}
		else {
			switch(arr[0]) {
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
		com.addAll(parseCards(line));
		int[] cnt = new int[4];
		for(int v : com) {
			cnt[v/13]++;
		}
		boolean[] suits = new boolean[4];
		for(int j = 0; j < 4; j++) {
			int[] cards = {-1, -1, -1};
			boolean foundAce = false;
			for(int v : com) {
				int s = v / 13, i = v % 13;
				if(s == j) {
					cards[0] = i;
					if(i == 0)
						foundAce = true;
				}
				Arrays.sort(cards);
			}
			if(foundAce) {
				suits[j] = true;
				continue;
			}
			if(cards[0] == 0 || cards[1] == 0 || cards[2] == 0) {
				suits[j] = true;
			}
			else if(cards[2] == 12 && cnt[j] > 1) {
				suits[j] = true;
			}
			else if((cards[1] == 11 || cards[2] == 11) && cnt[j] > 2) {
				suits[j] = true;
			}
		}
		boolean suitStop = suits[0] && suits[1] && suits[2] && suits[3];
		int score = 0;
		for(int v : com) {
			int s = v / 13, i = v % 13;
			if(((i - 1 + 13) % 13) > 8) {
				score += ((i - 1 + 13) % 13) - 8;
			}
			
			if(i == 12 && cnt[s] <= 1) {
				score--;
			}
			if(i == 11 && cnt[s] <= 2) {
				score--;
			}
			if(i == 10 && cnt[s] <= 3) {
				score--;
			}
		}
		String out = "PASS";
		if(suitStop && score >= 16) {
			out = "BID NO-TRUMP";
		}
		else {
			int max = -1, ind = 0;
			for(int i = 0; i < 4; i++) {
				int v = cnt[i];
				if(v == 0)
					score+=2;
				if(v == 1)
					score += 2;
				if(v == 2)
					score+= 1;
				if(v > max) {
					ind = i;
					max = v;
				}
			}
			
			if(score >=  14) {
				out = "BID ";
				switch (ind) {
				case 0:
					out += "S";
					break;
				case 1:
					out += "H";
					break;
				case 2:
					out += "D";
					break;
				case 3:
					out += "C";
					break;
				default:
					break;
				}
			}
		}
		System.out.println(out);
		return 1;
	}

}
