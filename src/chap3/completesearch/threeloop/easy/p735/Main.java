package chap3.completesearch.threeloop.easy.p735;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;

import javax.swing.text.Segment;

public class Main {
	/**
	 * @param args
	 */
	static InputReader in 		= new InputReader(System.in);
    //static OutputWriter out	=	new OutputWriter(System.out);
	static BufferedReader br=null;

	public static void main(String[] args)  {
		br=new BufferedReader(new InputStreamReader(System.in));
		while(true) {
				try {
					if(process()==0) {
						break;
					}
				} catch (Exception e) {
					return;
				}
            
            
		}
		//out.flush();
		//out.close();
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
	static int findClosest (List<Integer> ll, int val, boolean comp) {
		int a = 0, b = ll.size() - 1;
		
		if(ll.size() == 0)
			return -1;
		if(comp && ll.get(0) > val) {
			return -1;
		}
		if(!comp && ll.get(ll.size()-1) < val) {
			return -1;
		}
		while(a != b) {
			int mid = a + b;
			mid /= 2;
			if((b - a) == 1) {
				if(comp) {
					if(ll.get(b) <= val) {
						a = b;
					}
					else {
						b = a;
					}
				}
				else {
					if(ll.get(a) >= val) {
						b = a;
					}
					else {
						a = b;
					}
				}
				break;
			}
			
			boolean v1 = ll.get(mid) < val;
			boolean v2 = ll.get(mid) > val;
			boolean v3 = ll.get(mid) == val;
			if(v3) {
				a = b = mid;
				break;
			}
			if(comp) {
				if(v1) {
					a = mid;
				}
				else {
					b = mid;
				}
			}
			else {
				if(v2) {
					b = mid;
				}
				else {
					a = mid;
				}
			}
		}
		return a;
	}
	static List<String> ans = new ArrayList<>();
	static int casen = 1;
	static StringBuffer sb = new StringBuffer();
	static int process() {
		int n = in.readInt();
		if(n <= 0) {
			sb.append("END OF OUTPUT\n");
			System.out.print(sb);
			return 0;
		}
		int perm = 0, comb = 0;
		Set<Integer> values = new HashSet<>();
		for(int i = 1; i <= 20; i++) {
			values.add(i);
			values.add(i*2);
			values.add(i*3);
		}
		values.add(50);
		values.add(0);
		boolean[][][] vis = new boolean[61][61][61];
		
		
		for(int s1 : values) {
			if((n-s1) < 0 || ((n-s1) > 120))
				continue;
			for(int s2 : values) {
				if((n-s1-s2) < 0 || ((n-s1-s2) > 60))
					continue;
				int s3 = n - s1 - s2;
				if(values.contains(s3)){
					if((s1 + s2 + s3) == n) {
						perm++;
						if(!vis[s1][s2][s3]) {
							comb++;
							vis[s1][s2][s3] = vis[s1][s3][s2] = vis[s2][s1][s3] = vis[s2][s3][s1] = vis[s3][s1][s2] = vis[s3][s2][s1] = true;
						}
					}
				}
			}
		}
		if(perm == 0) {
			sb.append(String.format("THE SCORE OF %d CANNOT BE MADE WITH THREE DARTS.\n", n));
		}
		else {
			sb.append(String.format("NUMBER OF COMBINATIONS THAT SCORES %d IS %d.\nNUMBER OF PERMUTATIONS THAT SCORES %d IS %d.\n", n, comb, n, perm));
		}
		
		sb.append("**********************************************************************\n");
		return 1;
	}
	
	static void pick(int[] arr, boolean[] used, int k, int cnt, StringBuffer sb, int ind) {
		if(cnt == k) {
			String str = "";
			for(int i = 0; i < used.length; i++) {
				if(used[i]) {
					str +=" "+arr[i];
				}
			}
			sb.append(str.trim());
			sb.append("\n");
		}
		else if(ind < used.length){
			used[ind] = true;
			pick(arr, used, k, cnt+1, sb, ind+1);
			used[ind] = false;
			pick(arr, used, k, cnt, sb, ind+1);
		}
	}
	
}
class InputReader {

	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;

	public InputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int readInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public String readString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}

	public boolean isSpaceChar(int c) {
		if (filter != null)
			return filter.isSpaceChar(c);
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public String next() {
		return readString();
	}

	public interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
}

class OutputWriter {
	private final PrintWriter writer;

	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
	}

	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}

	public void print(Object...objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0)
				writer.print(' ');
			writer.print(objects[i]);
		}
	}

	public void printLine(Object...objects) {
		print(objects);
		writer.println();
	}

	public void close() {
		writer.close();
	}

	public void flush() {
		writer.flush();
	}

	}

class IOUtils {

	public static int[] readIntArray(InputReader in, int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++)
			array[i] = in.readInt();
		return array;
	}

}
