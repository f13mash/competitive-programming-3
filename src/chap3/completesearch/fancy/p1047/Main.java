package chap3.completesearch.fancy.p1047;

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
	static int process() throws Exception{
		int n = in.readInt(), t = in.readInt();
		if(n == 0)
			return 0;
		max = res = 0;
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = in.readInt();
		}
		
		int m = in.readInt();
		int[][] com = new int[m][2];
		for(int i = 0; i < m; i++) {
			int cnt = in.readInt();
			while(cnt-- > 0) {
				int ind = in.readInt();
				com[i][0] |= 1 << (ind - 1);
			}
			com[i][1] = in.readInt();
			for(int j = 0; j < n; j++) {
				if(((1<<j) & com[i][0]) > 0)
					arr[j] -= com[i][1];
			}
		}
		recurse(arr, com, new boolean[n], 0, 0, t);
		System.out.println(String.format("Case Number  %d", casen));
		System.out.println(String.format("Number of Customers: %d", max));
		String str = "";
		for(int i = 0 ;  i < n; i++) {
			if(((1<<i) & res) > 0) {
				str +=" "+(1+i);
			}
		}
		System.out.println("Locations recommended:"+str);
		System.out.println();
		casen++;
		return 1;
	}
	static int max = 0, res = 0;
	static void recurse(int[] arr, int[][] com, boolean[] used, int ind, int cnt, int k) {
		if(cnt == k) {
			int sum = 0;
			int val = 0;
			for(int i = 0; i < used.length; i++) {
				if(used[i]) {
					sum+=arr[i];
					val |= 1 << i;
				}
			}
			for(int i = 0; i < com.length; i++) {
				if((val & com[i][0]) > 0)
					sum += com[i][1];
			}
			if(sum > max) {
				max = sum;
				res = val;
			}
			return;
		}
		if(ind < arr.length) {
			used[ind] = true;
			recurse(arr, com, used, ind + 1, cnt + 1, k);
			used[ind] = false;
			recurse(arr, com, used, ind + 1, cnt, k);
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
