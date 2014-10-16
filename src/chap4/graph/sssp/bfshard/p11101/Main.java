package chap4.graph.sssp.bfshard.p11101;

import java.awt.Point;
import java.awt.geom.Point2D;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
	
	static int casen = 1;
	static StringBuffer sb = new StringBuffer();
	static int process() throws Exception{
		int p = in.readInt();
		if(p == 0)
			return 0;
		
		int[][] m1 = new int[p][2];
		p = read(m1);
		int q = in.readInt();
		int[][] m2 = new int[q][2];
		q = read(m2);
		
		long min = Long.MAX_VALUE;
		for(int i = 0; i < p; i++) {
			int[] a1 = m1[i], a2 = m1[(i + 1)%p];
			for(int j = 0; j < q; j++) {
				int[] b1 = m2[j], b2 = m2[(j + 1)%q];
				if(a1[0] == a2[0]) {
					min = Math.min(min, min0(b1, a1, a2));
				}
				else {
					min = Math.min(min, min1(b1, a1, a2));
				}
				if(b1[0] == b2[0]) {
					min = Math.min(min, min0(a1, b1, b2));
				}
				else {
					min = Math.min(min, min1(a1, b1, b2));	
				}
			}
		}
		System.out.println(min);
		
		return 1;
	}
	static int read(int[][] m1) {
		int p = m1.length;
		int dir = -1, s1 = 1;
		m1[0][0] = in.readInt(); m1[0][1] = in.readInt();
		for(int i = 1; i < p; i++) {
			int x = in.readInt(), y = in.readInt();
			if(dir == 0 && x == m1[s1-1][0]) {
				m1[s1-1][1] = y;
				continue;
			}
			if(dir == 1 && y == m1[s1-1][1]) {
				m1[s1-1][0] = x;
				continue;
			}
			m1[s1][0] = x;
			m1[s1][1] = y;
			if(m1[s1-1][0] == x)
				dir = 0;
			else dir = 1;
			s1++;
		}
		return s1;
		
	}
	static long min0(int[] a, int[] b, int[] c) {
		long ydiff = Math.abs(a[0] - b[0]);
		long xdiff = (a[1] > b[1] && a[1] > c[1]) || (a[1] < b[1] && a[1] < c[1]) ? Math.min(Math.abs(a[1] - b[1]), Math.abs(a[1] - c[1])): 0;
		return ydiff + xdiff;
	}
	
	static long min1(int[]a, int[] b, int[] c) {
		long xdiff = Math.abs(a[1] - b[1]);
		long ydiff = (a[0] > b[0] && a[0] > c[0]) || (a[0] < b[0] && a[0] < c[0]) ? Math.min(Math.abs(a[0] - b[0]), Math.abs(a[0] - c[0])): 0;
		return ydiff + xdiff;
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
	
	static int findClosest(List<Integer> arr, int val, boolean comp, int s, int e) {
		//System.out.println(s+" : "+e+" : "+val+ " : "+Arrays.toString(arr));
		if(s == -1 || e == -1 || s > e)
			return -1;
		if(arr.size() == 0)
			return -1;
		if(comp && arr.get(s) > val) {
			return -1;
		}
		
		if(!comp && arr.get(e) < val) {
			return -1;
		}
		
		if(s == e) {
			return s;
		}
		
		if((e - s) == 1) {
			if(comp) {
				if(arr.get(e) <= val) {
					return e;
				}
				else {
					return s;
				}
			}
			else {
				if(arr.get(s) >= val) {
					return s;
				}
				else {
					return e;
				}
			}
		}
		int mid = (s + e) / 2;
		if(comp) {
			if(arr.get(mid) > val) {
				return findClosest(arr, val, comp, s, mid);
			}
			else {
				return findClosest(arr, val, comp, mid, e);
			}
		}
		else {
			if(arr.get(mid) < val) {
				return findClosest(arr, val, comp, mid, e);
			}
			else {
				return findClosest(arr, val, comp, s, mid);
			}
		}
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
	static int findClosest(List<Integer> arr, int val, boolean comp) {
		return findClosest(arr, val, comp, 0, arr.size() - 1);
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

class UnionFind {
	int[] arr;
	public UnionFind(int size) {
		this.arr = new int[size];
		for(int i = 0; i < size; i++)
			this.arr[i] = i;
	}
	
	public void add(int a, int b) {
		while(arr[a] != a) { 
			a = arr[a];
		}
		while(arr[b] != b) {
			b = arr[b];
		}
		if(a > b) {
			int t = a;
			a = b;
			b = t;
		}
		arr[b] = a;
	}
	
	public int rootOf(int a) {
		int ta = a;
		
		while(arr[a] != a)
			a = arr[a];
		
		return a;
	}
	
	public boolean areConnected(int a, int b) {
		return rootOf(a) == rootOf(b);
	}
	
	public boolean disconnect(int a, int b) {
		if(arr[a] == b) {
			arr[a] = a;
			return true;
		}
		else if(arr[b] == a) {
			arr[b] = b;
			return false;
		}
		else return false;
	}
}