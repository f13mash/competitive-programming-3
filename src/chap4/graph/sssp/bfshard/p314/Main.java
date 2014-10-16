package chap4.graph.sssp.bfshard.p314;

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
		int m = in.readInt(), n = in.readInt();
		if(m == 0 && n == 0)
			return 0;
		
		int[][] grid = new int[m][n];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				grid[i][j] = in.readInt();
			}
		}
		int a = in.readInt(), b = in.readInt(), c = in.readInt(), d = in.readInt();
		//c = 7; d = 1;
		int dir = 0;
		String s = in.readString().trim();
		switch (s) {
		case "north":
			dir = 0;
			break;
		case "east":
			dir = 1;
			break;
		case "south":
			dir = 2;
			break;
		case "west":
			dir = 3;
			break;
		default:
			break;
		}
		int ans = -1;
		if(a == c && b == d)
			ans = 0;
		if(m > 2 && n > 2 && ans == -1) {
			int lr = m + 1, lc = n + 1;
			boolean[][] path = new boolean[lr][lc];
			for(int i = 0; i < lr; i++) {
				for(int j = 0; j < lc-1; j++) {
					if((i + 1) < lr && (j + 1) < lc && i > 0 && j > 0 && grid[i-1][j-1] == 0 && grid[i-1][j] == 0 && grid[i][j-1] == 0 && grid[i][j] == 0)
							path[i][j] = true;
				}
			}
			/*System.out.println();
			for(int i = 0; i < lr; i++) {
				for(int j = 0; j < lc; j++) {
					System.out.print(path[i][j] ? 1 : 0);
				}
				System.out.println();
			}*/
			
			int[][][] dp = new int[lr][lc][4];
			for(int i = 0; i < lr; i++) {
				for(int j = 0; j < lc; j++) {
					Arrays.fill(dp[i][j], -1);
				}
			}
			Queue<IntegerTriple> q = new LinkedList<IntegerTriple>();
			if(path[a][b]) {
				dp[a][b][dir] = 0;	
				q.add(new IntegerTriple(a, b, dir));
			}
			int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
			while(q.size() > 0) {
				IntegerTriple trp = q.remove();
				for(int i = 0; i < 4; i++) {
					if(i == trp.c) {
						continue;
					}
					if(Math.abs(i - trp.c) != 2) {
						if(dp[trp.a][trp.b][i] > (dp[trp.a][trp.b][trp.c] + 1) || dp[trp.a][trp.b][i] == -1) {
							dp[trp.a][trp.b][i] = dp[trp.a][trp.b][trp.c] + 1;
							
							
						}
					}
					else {
						if(dp[trp.a][trp.b][i] > (dp[trp.a][trp.b][trp.c] + 2) || dp[trp.a][trp.b][i] == -1) {
							dp[trp.a][trp.b][i] = dp[trp.a][trp.b][trp.c] + 2;
							
						}
					}
					
				}
				for(int i = 0; i < 4; i++) {
					int cnt = 0;
					if(dp[trp.a][trp.b][i] == -1)
						continue;
					while(cnt++ < 3) {
						int na = trp.a + (moves[i][0]*cnt);
						int nb = trp.b + (moves[i][1]*cnt);
						if(na >= 0 && na <lr && nb >= 0 && nb <lc && path[na][nb]) {
							if(dp[na][nb][i] == -1 || dp[na][nb][i] > (dp[trp.a][trp.b][i] + 1)) {
								dp[na][nb][i] = (dp[trp.a][trp.b][i] + 1);
								q.add(new IntegerTriple(na, nb, i));
							}
						}
						else {
							break;
						}

					}
					
				}
			}
			for(int i = 0; i < 4; i++) {
				if(ans == -1 || (dp[c][d][i] != -1 && dp[c][d][i] < ans)) {
					ans = dp[c][d][i];
				}
			}
			
			
		}
		System.out.println(ans);
		
		return 1;
	}
	static class IntegerTriple {
		public int a = 0, b = 0, c = 0;

		public IntegerTriple(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
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