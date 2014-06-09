package chap1.adhoc.game.card.p555;

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
	
	static List<Card> parseCards(String cards) {
		char[] arr = cards.trim().toCharArray();
		List<Card> ll = new ArrayList<Card>();
		for(int i = 0; i < arr.length/2; i++) {
			char[] card = new char[2];
			card[0] = arr[2*i];
			card[1] = arr[2*i + 1];
			ll.add(new Card(card));
		}
		
		return ll;
	}
	
	static boolean isFace(int c) {
		if(c %13 == 0 || (c % 13) > 9)
			return true;
		return false;
	}
	static int process(String line) {
		if(line.compareTo("#") == 0)
			return 0;
		char dir = line.trim().charAt(0);
		int start = 0;
		switch (dir) {
		case 'N':
			start = 0;
			break;
		case 'E':
			start = 1;
			break;
		case 'S':
			start = 2;
			break;
		case 'W':
			start = 3;
			break;

		default:
			break;
		}
		
		String inp = readLine().trim() + readLine().trim();
		List<Card> cards = parseCards(inp);
		Card[][] arr = new Card[4][13];
		for(int i = 0; i < cards.size(); i++, start++) {
			arr[(start + 1) % 4][i/4] = cards.get(i);
		}
		for(int i = 0; i < arr.length; i++) {
			Arrays.sort(arr[i]);
		}
		System.out.println("S:"+cardArray(arr[2]));
		System.out.println("W:"+cardArray(arr[3]));
		System.out.println("N:"+cardArray(arr[0]));
		System.out.println("E:"+cardArray(arr[1]));
		return 1;
	}
	static String cardArray(Card[] arr) {
		String out = "";
		for(Card c : arr) {
			out += " "+c;
		}
		return out;
	}
	static class Card implements Comparable<Card>{
		char[] arr = null;
		int val;
		public Card(char[] arr) {
			this.arr = arr;
			this.val = val;
		}
		int cardValue() {
			int base = 0, val = 0;
			switch (arr[0]) {
				case 'C':
					base = 0;
					break;
				case 'D':
					base = 1;
					break;
				case 'S':
					base = 2;
					break;
				case 'H':
					base = 3;
					break;
				default:
					break;
			}
			
			if(arr[1] <= '9' && arr[1] >= 2) {
				val = arr[1] - '2';
			}
			else {
				switch(arr[1]) {
					case 'T':
						val = 8;
						break;
					case 'A':
						val = 12;
						break;
					case 'J':
						val = 9;
						break;
					case 'Q':
						val = 10;
						break;
					case 'K':
						val = 11;
						break;
				}
			}
			return base*13 + val;
		}
		@Override
		public int compareTo(Card o) {
			if(o.cardValue() / 13 > this.cardValue() / 13) {
				return -1;
			}
			if(o.cardValue() / 13 < this.cardValue() / 13) {
				return 1;
			}
			if(o.cardValue() % 13 > this.cardValue() % 13) {
				return -1;
			}
			if(o.cardValue() % 13 < this.cardValue() % 13) {
				return 1;
			}
			return 0;
		}
		
		@Override
		public String toString() {
			return arr[0]+""+arr[1];
		}

	}

}
