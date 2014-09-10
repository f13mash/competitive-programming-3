package chap4.graph.articulation.p10765;

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
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	static int process() {
		int n = in.readInt(), m = in.readInt();
		while(!(n == 0 && m == 0)){
			List<Edge>[] el = new ArrayList[n];
			for(int i = 0; i < n; i++) {
				el[i] = new ArrayList<>();
			}
			
			int a = in.readInt(), b = in.readInt();
			while(a != -1 && b != -1) {
				boolean f = false;
				for(Edge e : el[a]) {
					f = e.equals(a, b);
					if(f)
						break;
				}
				if(f)
					continue;
				Edge e = new Edge(a, b);
				el[a].add(e);
				el[b].add(e);
				a = in.readInt();
				b = in.readInt();
			}
			
			int[] dfs_num = new int[n];
			int[] dfs_low = new int[n];
			int[] vis = new int[n];
			cnt = 0;
			Arrays.fill(dfs_num, -1);
			Arrays.fill(dfs_low, -1);
			for(int i = 0; i < n; i++) {
				if(dfs_num[i] == -1) {
					dfs(el, dfs_num, dfs_low, i, vis);
				}
			}
			IntegerPair[] res = new IntegerPair[n];
			for(int i = 0; i < n; i++) {
				/*boolean[] mp = new boolean[cnt];
				int ans = 0;
				for(Edge e : el[i]) {
					int j = e.other(i);
					if(!mp[dfs_low[j]]) {
						mp[dfs_low[j]] = true;
					}
					else {
					}
				}
				*/
				
				res[i] = new IntegerPair(i, vis[i]);
			}
			
			Arrays.sort(res, new Comparator<IntegerPair>() {

				@Override
				public int compare(IntegerPair o1, IntegerPair o2) {
					if(o1.b != o2.b)
						return o2.b - o1.b;
					return o1.a - o2.a;
				}
			});
			for(int i = 0; i < m; i++) {
				System.out.println(res[i].a+" "+res[i].b);
			}
			System.out.println();
			//System.out.println(Arrays.toString(vis));
			//System.out.println(Arrays.toString(dfs_num));
			//System.out.println(Arrays.toString(dfs_low));

			casen++;
			n = in.readInt(); m = in.readInt();
		}
		return 0;
	}
	static int cnt = 0;
	static void dfs(List<Edge>[] el, int[] dfs_num, int[] dfs_low, int u, int[] vis) {
		dfs_num[u] = dfs_low[u] = cnt++;
		for(Edge e : el[u]) {
			int v = e.other(u);
			if(e.visited)
				continue;
			e.visit();
			if(dfs_num[v] == -1) {
				vis[u]++;
				vis[v]++;
				dfs(el, dfs_num, dfs_low, v, vis);
				
				if(dfs_low[v] < dfs_num[u]) {
					//vis[v]--;
					vis[u]--;
				}
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
			}
			else {
				dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
			}
		}
	}
	
	public static class Edge {
		boolean visited = false;
		int a = - 1, b = -1;
		public Edge(int a, int b) {
			if(a > b) {
				int t = a;
				a = b;
				b = t;
			}
			this.a = a;
			this.b = b;
		}
		public int other(int a) {
			if(this.a == a)
				return this.b;
			if(this.b == a)
				return this.a;
			return -1;
		}
		public void visit() {
			visited = true;
		}
		public boolean isVisited() {
			return visited;
		}
		public boolean equals(int a, int b) {
			if(this.a == a && this.b == b) 
				return true;
			if(this.a == b && this.b == a) 
				return true;
			return false;
		}
		
	}
	
	static class IntegerPair {
		int a;
		int b;
		public IntegerPair(int a, int b) {
			this.a = a;
			this.b = b;
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
