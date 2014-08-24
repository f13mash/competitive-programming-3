package chap3.greedy.sorting.p11100;


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
		int n = in.readInt();
		if(n == 0)
			return 0;
		
		int[] arr = IOUtils.readIntArray(in, n);
		Arrays.sort(arr);
		int[] cnts = new int[n];
		cnts[n-1] = 1;
		int max = n == 0 ? 0 : 1;
		Map<Integer, Integer> mp = new TreeMap<>();
		for(int i = n-2; i >= 0; i--) {
			if(arr[i] == arr[i+1]) {
				cnts[i] = cnts[i+1] + 1;
			}
			else {
				mp.put(-arr[i+1], cnts[i+1]);
				cnts[i] = 1;
				max = Math.max(cnts[i + 1], max);
			}
		}
		mp.put(-arr[0], cnts[0]);
		max = Math.max(cnts[0], max);
		
		List<List<Integer>> ll = new ArrayList<>();
		for(int i = 0; i < max; i++) {
			ll.add(new ArrayList<Integer>());
		}
		for(int key : mp.keySet()) {
			int val = mp.get(key);
			int ind = 0;
			Collections.sort(ll, new Comparator<List<Integer>>() {

				@Override
				public int compare(List<Integer> o1, List<Integer> o2) {
					// TODO Auto-generated method stub
					return o1.size() - o2.size();
				}

			});
			while(ind < ll.size() && val > 0) {
				ll.get(ind).add(-key);
				val--;
				ind++;
			}
			
			while(val > 0) {
				List<Integer> al = new ArrayList<Integer>();
				al.add(-key);
				ll.add(al);
				val--;
			}
		}
		if(casen > 1) {
			sb.append("\n");
		}
		String[] strl = new String[ll.size()];
		for(int i = 0; i < strl.length; i++) {
			strl[i] = "";
			for(int v : ll.get(i)) {
				strl[i] = v + " " + strl[i];
			}
		}
		Arrays.sort(strl);
		sb.append(ll.size()+"\n");
		for(String str : strl) {
			sb.append(str.toString().trim());
			sb.append("\n");
		}
		casen++;
		return 1;
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
