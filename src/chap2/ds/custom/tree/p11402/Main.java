package chap2.ds.custom.tree.p11402;
/**
 * TLE error received. will attempt this problem later
 */
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
			if(process()==0) {
            	break;
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
	static int process()   {
		int cases = in.readInt();
		int cas = 1;
		StringBuffer sb = new StringBuffer();
		while(cases-- > 0) {
			int m = in.readInt();
			List<String> ll = new ArrayList<>();
			int len = 0;

			boolean[] arr = new boolean[1<<20];
			int ind = 0;
			while(m-- > 0) {
				int rep = in.readInt();
				String str = in.readString();
				char[] sarr = str.toCharArray();
				while(rep-- > 0) {
					len += sarr.length;
					for(char c : sarr) {
						arr[ind++] = c == '1' ? true : false;
					}

				}
			}
			SegmentTree st = new SegmentTree(arr, len);

			int cnt = in.readInt();
			//System.out.println("cnt : "+cnt);
			int q = 1;
			sb.append(String.format("Case %d:\n", cas++));
			while(cnt-- > 0) {
				char c = in.readString().trim().charAt(0);
				int s = in.readInt(), e = in.readInt();
				switch (c) {
				case 'F':
					st.bucca(s, e);
					break;
				case 'E':
					st.barb(s, e);
					break;
				case 'I':
					st.inverse(s, e);
					break;
				case 'S':
					sb.append(String.format("Q%d: %d\n", q++, st.find(s, e)));
					break;
				default:
					break;
				}
			}
		}
		System.out.print(sb);

		return 0;
	}
	
	static class IntegerTriple {
		int a, b, c;
		public IntegerTriple(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
		
	}
	
	static class SegmentTree {
		boolean[] data = null;
		int[] st = null;
		int n;
		List<IntegerTriple> nl = new ArrayList<>();
		
		private int left(int p) {return p << 1;}
		
		private int right(int p) {return (p << 1) + 1;}
		private int indOf(int i) {return (int) (Math.pow(2, Math.ceil((Math.log(n)/Math.log(2)))) - 1 + i);};
		
		public SegmentTree(boolean[] data, int len) {
			this.data = data;
			n = len;
			//System.out.println("Data : " + Arrays.toString(data) + " : "+n);
			/*
			for(int i = 0; i < n; i++) {
				System.out.println(Arrays.toString(data[i]));
			}
			*/
			st = new int[4*n];
			
			build(1, 0, n-1);
		}
		
		private void build(int p, int L, int R) {
			//System.out.println(p+" : "+L+" : "+R);
			if(L == R) {
				if(data[L])
					st[p] = 1;
				else
					st[p] = 0;
			}
			else {
				int brk = (L + R) / 2;
				build(left(p), L, brk);
				build(right(p), brk + 1, R);
				
				//System.out.println(p+" : "+L+" : "+R+" : : "+mx);
				st[p] = st[left(p)] + st[right(p)];
			}
		}
		private int update(int p, int L, int R, List<IntegerTriple> ql) {
			int old = st[p];
			//no diff
			/*boolean chng = false;
			
			for(IntegerTriple ip : ql) {
				if (!(ip.a >  R || ip.b <  L)) {chng = true; break;}	
			}
			if(!chng)
				return 0;
			*/
			if(L == R) {
				for(IntegerTriple it : ql) {
					/*if (it.a >  R || it.b <  L)
						continue;
						*/
					switch (it.c) {
					case 1:
						data[L] = !data[L];
						break;
					case 2:
						data[L] = false;
						break;
					case 3:
						data[L] = true;
						break;
					default:
						break;
					}
				}
				
				if(data[L])
					st[p] = 1;
				else
					st[p] = 0;
			}
			else {
				int brk = (L + R) / 2;
				List<IntegerTriple> qll = new ArrayList<>();
				List<IntegerTriple> qlr = new ArrayList<>();
				
				for(IntegerTriple it : ql) {
					if(!(it.a > brk || it.b < L))
						qll.add(it);
					if(!(it.a > R || it.b < (brk+1)))
						qlr.add(it);
				}
			
				
				int d1 = qll.size() > 0 ? update(left(p), L, brk, qll) : 0;
			
				int d2 = qlr.size() > 0 ? update(right(p), brk + 1, R, qlr) : 0;
				
				//System.out.println(p+" : "+L+" : "+R+" : : "+mx);
				st[p] = old + d1 + d2;
			}
			return st[p] - old;
		}
		
		public void inverse(int s, int e) {
			nl.add(new IntegerTriple(s, e, 1));
			//rebuild(s, e);
		}
		public void barb(int s, int e) {
			nl.add(new IntegerTriple(s, e, 2));
			//rebuild(s, e);
		}
		public void bucca(int s, int e) {
			nl.add(new IntegerTriple(s, e, 3));
			//rebuild(s, e);
		}
		private int rmq(int p, int L, int R, int i, int j) {
			if (i >  R || j <  L) return -1;
			if (L >= i && R <= j) return st[p];
			int brk = (L+R)/2;
		    int p1 = rmq(left(p), L, brk, i, j);
		    int p2 = rmq(right(p), brk + 1, R, i, j);
			int ret = 0;
			if(p1 > 0) {
				ret += p1;
			}
			if(p2 > 0) {
				ret += p2;
			}
			
			//System.out.println(p+" : "+L+" : "+R+" : : "+mx+" : "+p1+" : "+p2);
			return ret;
		}
		
		public int find(int s, int e) {
			if(nl.size() > 0) {
				update(1, 0, n-1, nl);
				nl.clear();
			}

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
