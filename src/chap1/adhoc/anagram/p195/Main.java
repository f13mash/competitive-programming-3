package chap1.adhoc.anagram.p195;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
				ret[i] = Integer.parseInt(str[i]);
		}
		return ret;
	}
	
	
	static int process(String line) {
		int w = Integer.parseInt(line.trim());
		char[][] words = new char[w][];
		for(int i = 0; i < w; i++) {
			words[i] = readLine().trim().toCharArray();
		}
		StringBuffer sb = new StringBuffer();

		Set<String> ans = new HashSet<>();
		for(int i = 0; i < w; i++) {
			char[] arr = words[i];
			Arrays.sort(arr);
			int d = 1;
			for(int j = 1; j < arr.length; j++) {
				if(arr[j] != arr[j-1])
					d++;
			}
			int[] len = new int[d];
			char[] ch = new char[d];
			int curr = 0;
			len[0] = 1; ch[0] = arr[0];
			for(int j = 1; j < arr.length; j++) {
				if(arr[j] == arr[j-1]) {
					len[curr]++;
				}
				else {
					curr++;
					len[curr]++;
					ch[curr] = arr[j];
				}
			}
			permute(ans, ch, len, new int[d], "", arr.length, -1);

			List<String> ll = new ArrayList<String>(ans);
			Collections.sort(ll, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					if(o1.length() != o2.length() && false)
						return o1.length() - o2.length();
					else {
						char[] a = o1.toCharArray();
						char[] b = o2.toCharArray();
						int max = Math.max(a.length, b.length);
						for(int i = 0; i < max; i++) {

							if(i >= a.length)
								return 1;
							if(i >= b.length)
								return -1;
							if(a[i] == b[i])
								continue;
							else {
								if(Character.isLowerCase(a[i]) ^ Character.isLowerCase(b[i])) {
									if(Character.toLowerCase(a[i]) == Character.toLowerCase(b[i])) {
										return Character.isLowerCase(a[i]) ? 1 : -1;
									}
									else {
										return Character.toLowerCase(a[i]) - Character.toLowerCase(b[i]);
									}
								}
								else {
									return a[i] - b[i];
								}
							}
						}
						return 0;
					}
				}
			});
			for(String wrd : ll ) {
				sb.append(wrd);
				sb.append("\n");
			}

			ans.clear();
			
		}

		/*Collections.sort(ll, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if(o1.length() != o2.length() && false)
					return o1.length() - o2.length();
				else {
					char[] a = o1.toCharArray();
					char[] b = o2.toCharArray();
					int max = Math.max(a.length, b.length);
					for(int i = 0; i < max; i++) {

						if(i >= a.length)
							return 1;
						if(i >= b.length)
							return -1;
						if(a[i] == b[i])
							continue;
						else {
							if(Character.isLowerCase(a[i]) ^ Character.isLowerCase(b[i])) {
								if(Character.toLowerCase(a[i]) == Character.toLowerCase(b[i])) {
									return Character.isLowerCase(a[i]) ? 1 : -1;
								}
								else {
									return Character.toLowerCase(a[i]) - Character.toLowerCase(b[i]);
								}
							}
							else {
								return a[i] - b[i];
							}
						}
					}
					return 0;
				}
			}
		});*/
		System.out.print(sb.toString());
		return 0;
	}
	
	static void permute(Set<String> hs, char[] ch, int[] len, int[] used, String word, int size, int last) {
		if(word.length() == size) {
			hs.add(word);
			return;
		}
		for(int i = 0; i < len.length; i++) {
			if(i == last || used[i] >= len[i]) {
				continue;
			}
			
			for(int j = 1; j <= (len[i] - used[i]); j++) {
				used[i] += j;
				permute(hs, ch, len, used, word+(String.format("%0"+j+"d", 0).replace('0', ch[i])), size, i);
				used[i] -= j;
			}
		}
	}
}
