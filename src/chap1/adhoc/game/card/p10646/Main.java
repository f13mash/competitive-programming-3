package chap1.adhoc.game.card.p10646;

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
		String[] splts = cards.split(" ");
		List<Card> ll = new ArrayList<Card>();
		
		for(String s : splts) {
			ll.add(new Card(s.toCharArray()));
		}
		
		return ll;
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
			int suitIndex = 1, valIndex = 0;
			switch (arr[suitIndex]) {
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
			
			if(arr[valIndex] <= '9' && arr[valIndex] >= 2) {
				val = arr[valIndex] - '2';
			}
			else {
				switch(arr[valIndex]) {
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
	static int process(String line) {
		int cases = Integer.parseInt(line.trim());
		for(int i = 0; i < cases; i++) {
			List<Card> cards = parseCards(readLine().trim());
			System.out.println(String.format("Case %d: %s", i + 1, cards.get(32)));
		}
		return 0;
	}

}
