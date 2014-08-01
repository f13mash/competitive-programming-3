package chap2.ds.custom.tree.p11235;

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

	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				if(process()==0) {
	            	break;
	            }				
			}
			catch(Exception e) {
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
	static int process() throws Exception  {
		int n = in.readInt();
		if(n == 0)
			return 0;
		int m = in.readInt();
		int[] data = new int[n];
		
		for(int i = 0; i < n; i++) {
			data[i] = in.readInt();
		}
		int[][] cnts = new int[n][2];
		cnts[0][0] = 1;
		cnts[n-1][1] = 1;
		for(int i = 1; i < n; i++) {
			if(data[i] == data[i-1]) {
				cnts[i][0] = cnts[i-1][0] + 1;
			}
			else {
				cnts[i][0] = 1;
			}
		}
		for(int i = n - 2; i >= 0; i--) {
			if(data[i] == data[i+1]) {
				cnts[i][1] = cnts[i+1][1] + 1;
			}
			else {
				cnts[i][1] = 1;
			}
		}
		
		SegmentTree stree = new SegmentTree(cnts);
		StringBuffer out = new StringBuffer();
		while(m-- > 0) {
			int s = in.readInt();
			int e = in.readInt();
			out.append(stree.find(s-1, e-1)+"\n");
		}
		System.out.print(out);
		
		
		return 1;
	}
	
	static class IntegerPair {
		int a, b;
		public IntegerPair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
	}
	
	static class SegmentTree {
		int[][] data = null;
		int[] st = null;
		int n;
		
		private int left(int p) {return p << 1;}
		private int right(int p) {return (p << 1) + 1;}
		private int indOf(int i) {return (int) (Math.pow(2, Math.ceil((Math.log(n)/Math.log(2)))) - 1 + i);};
		
		public SegmentTree(int[][] data) {
			this.data = data;
			n = data.length;
			/*
			for(int i = 0; i < n; i++) {
				System.out.println(Arrays.toString(data[i]));
			}
			*/
			st = new int[4*n];
			
			build(1, 0, n-1);
		}
		
		private void build(int p, int L, int R) {
			if(L == R) {
				st[p] = 1;
			}
			else {
				int brk = (L + R) / 2;
				build(left(p), L, brk);
				build(right(p), brk + 1, R);
				int mx = 0;
				if(data[brk + 1][0] == (data[brk][0] + 1)) {
					int lc = 0, rc = 0;
					lc = Math.min(brk - L + 1, data[brk][0]);
					rc = Math.min(R - brk, data[brk+1][1]);
					mx = lc + rc;
				}
				mx = Math.max(st[left(p)], mx);
				mx = Math.max(st[right(p)], mx);
				//System.out.println(p+" : "+L+" : "+R+" : : "+mx);
				st[p] = mx;
			}
		}
		
		private int rmq(int p, int L, int R, int i, int j) {
			if (i >  R || j <  L) return -1;
			if (L >= i && R <= j) return st[p];
			int brk = (L+R)/2;
		    int p1 = rmq(left(p), L, brk, i, j);
		    int p2 = rmq(right(p), brk + 1, R, i, j);
			int mx = 0;
			if(data[brk + 1][0] == (data[brk][0] + 1) && p1 > 0 && p2 > 0) {
				int lc = 0, rc = 0;
				lc = Math.min(brk - Math.max(L, i) + 1, data[brk][0]);
				rc = Math.min(Math.min(j, R) - brk, data[brk+1][1]);
				mx = lc + rc;
			}
			mx = Math.max(mx, p1);
			mx = Math.max(mx, p2);
			//System.out.println(p+" : "+L+" : "+R+" : : "+mx+" : "+p1+" : "+p2);
			return mx;
		}
		
		public int find(int s, int e) {
			return rmq(1, 0, n-1, s, e);
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
