package chap3.divide_conquer.binary_section.p11413;

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

	public static void main(String[] args) throws Exception  {
		br=new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try{
					if(process()==0) {
						System.out.print(sb);
						break;
					}
			}
			catch(Exception e) {
				System.out.print(sb);
				throw e;
				//return;
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
	
	static int findClosest(int[] arr, int val, boolean comp, int s, int e) {
		//System.out.println(s+" : "+e+" : "+val+ " : "+Arrays.toString(arr));
		if(s == -1 || e == -1 || s > e)
			return -1;
		if(arr.length == 0)
			return -1;
		if(comp && arr[s] > val) {
			return -1;
		}
		
		if(!comp && arr[e] < val) {
			return -1;
		}
		
		if(s == e) {
			return s;
		}
		
		if((e - s) == 1) {
			if(comp) {
				if(arr[e] <= val) {
					return e;
				}
				else {
					return s;
				}
			}
			else {
				if(arr[s] >= val) {
					return s;
				}
				else {
					return e;
				}
			}
		}
		int mid = (s + e) / 2;
		if(comp) {
			if(arr[mid] > val) {
				return findClosest(arr, val, comp, s, mid);
			}
			else {
				return findClosest(arr, val, comp, mid, e);
			}
		}
		else {
			if(arr[mid] < val) {
				return findClosest(arr, val, comp, mid, e);
			}
			else {
				return findClosest(arr, val, comp, s, mid);
			}
		}
	}
	static int findClosest(int[] arr, int val, boolean comp) {
		return findClosest(arr, val, comp, 0, arr.length - 1);
	}
	
	static int casen = 1;
	static StringBuffer sb = new StringBuffer();
	static int process() throws Exception{
		String line = readLine();
		if(line == null || line.trim().length() == 0)
			return 0;
		int[] inp = giveArray(line.trim().split(" " ));
		int n = inp[0];
		int m = inp[1];
		int[] arr = giveArray(readLine().trim().split(" "));
		int[] sum = new int[n];
		int max = 0, min = 0;
		for(int i = 0; i < n; i++) {
			min = Math.max(min, arr[i]);
			if(i == 0) {
				sum[i] = arr[i];
			}
			else {
				sum[i] = sum[i-1] + arr[i];
			}
		}
		max = sum[n-1];
		int[] ans = null;
		int mans = min, pr = Integer.MAX_VALUE;
		while(min < max) {
			int mid = (min + max)/2;
			int[] res = calc(arr, sum, mid);
			//System.out.println(min + " : "+max +" : "+mid +" : "+Arrays.toString(res));

			if(res[0] > m ) {
				if((max - min) == 1) {
					if(arr[0] != m) {
						min = max;
					}
					else {
						max = min;
					}
					ans = res = calc(arr, sum, max);
				}
				else {
					min = mid;
					ans = res;	
				}
			}
			else {
				max = mid;
				ans = res;
			}
			if(ans[0] == m && ans[1] < pr) {
				pr = ans[1];
			}
		}
		if(ans == null || ans[0] < m) {
			pr = mans;
		}
		System.out.println(pr);
		return 1;
	}
	
	static int[] calc(int[] arr, int[] sum, int lim) {
		int ret = 0, curr = arr.length - 1, max = 0;
		while(curr >= 0) {
			int req = sum[curr] - lim;
			if(req > sum[0]) {
				int ind = findClosest(sum, req, false, 0, curr);
				if(ind == -1) {
					//System.out.println("error : ");
					return null;
				}
				else {
					max = Math.max(max, sum[curr] - sum[ind]);
					curr = ind;
				}
				ret++;
			}
			else {
				max = Math.max(max, sum[curr]);
				ret++;
				break;
			}
			
		}
		return new int[]{ret, max};
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
