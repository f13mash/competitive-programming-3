package chap3.completesearch.backtrack.hard.p416;

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
						System.out.print(sb);
						break;
					}
				} catch (Exception e) {
					System.out.print(sb);
					throw e;
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
	static int casen = 1;
	static StringBuffer sb = new StringBuffer();
	static String[] led = {"1111110", "0110000", "1101101", "1111001", "0110011", "1011011", "1011111", "1110000", "1111111", "1111011"};
	static Map<Integer, Integer> mp = new HashMap<>();
	static {
		for(int i = 0; i < led.length; i++) {
			mp.put(Integer.parseInt(led[i], 2), i);
		}
	};
	
	static int process() {
		int n = in.readInt();
		if(n == 0)
			return 0;
		boolean valid = true;
		int[] av = new int[7];
		Arrays.fill(av, -1);
		int[] arr = new int[n];
		
		
		for(int i = 0; i < n; i++) {
			String str = in.readString().trim().substring(0, 7);
			char[] temp = str.toCharArray();
			arr[i] = Integer.parseInt(str.replace('Y', '1').replace('N', '0'), 2);
			for(int j = 0; j < 7; j++) {
				if(temp[j] == 'Y') {
					av[j] = i;
				}
			}
			//System.out.println(Integer.toBinaryString(arr[i]));
		}
		//System.out.println(Arrays.toString(av));
		boolean found = false;
		
		for(int i = 0; !found && i < ((1 << 7)); i++) {
			found = res(arr, av, i);
		}
		
		//found = res(arr, av, ((1<<7) - 1)^(1 << 2));
		System.out.println(found ? "MATCH" : "MISMATCH");
		return 1;
	}
	static boolean res(int[] arr, int[] av, int sel) {
		HashSet<Integer>[] ans = new HashSet[arr.length];
		//System.out.println(Integer.toBinaryString(sel));

		HashSet<Integer> prev = new HashSet<>();
		for(int i = 0; i <= 10; i++) {
			prev.add(i);
		}
		//System.out.println(prev);
		for(int i = 0; i < arr.length; i++) {
			int or = 0;
			for(int j = 0; j < 7; j++) {
				if((sel & (1 << j)) > 0 && i > av[6-j]) {
					or |= (1 << (j));
				}
			}
			ans[i] = new HashSet();
			//System.out.println(Integer.toBinaryString(or));

			for(int k : mp.keySet()) {
				//System.out.println("\t"+Integer.toBinaryString(arr[i]|or)+" : "+Integer.toBinaryString(k|or));
				if((k|or) == (arr[i]|or) && prev.contains(mp.get(k) + 1))
					ans[i].add(mp.get(k));
			}
			prev = ans[i];
			//System.out.println(prev);
		}
		if(prev.size() > 0) 
			return true;
		else
			return false;
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
