package chap4.graph.stronglyconnected.p11838;

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
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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
		
		if(n == 0 && m == 0)
			return 0;
		
		Set<Integer>[] map = new Set[n];
		for(int i = 0; i < n; i++)
			map[i] = new HashSet<>();
		
		while(m-- > 0) {
			int a = in.readInt() - 1, b = in.readInt() - 1;
			int c = in.readInt();
			
			if(a == b)
				continue;
			
			map[a].add(b);
			if(c == 2)
				map[b].add(a);
						
		}
		int[] dfs_num = new int[n],
				dfs_low = new int[n];
		
		Arrays.fill(dfs_num, -1);
		Arrays.fill(dfs_low, -1);
		
		cnt = 0;
		scc = 0;
		boolean[] vis = new boolean[n];
		Stack<Integer> st = new Stack<>();
		for(int i = 0; i < n; i++) 
			if(dfs_num[i] == -1) {
				//recursive version passes all the test case. Unable to figure out the reason for failure during non-recursive version
				tarjanRec(map, i, dfs_num, dfs_low, st, vis);
				//tarjan(map, i, dfs_num, dfs_low, st, vis);
				//System.out.println(st);
			}
		
		
		System.out.println(scc > 1 ? 0 : 1);
		//System.out.println(scc);
		
		return 1;
	}

	static int cnt = 0;
	static int scc = 0;
	static void tarjanRec(Set<Integer>[] map, int u, int[] dfs_num, int[] dfs_low, Stack<Integer> st, boolean[] vis) {
		dfs_num[u] = dfs_low[u] = cnt++;
		st.push(u);
		vis[u] = true;
		
		for(int v : map[u]) {
			if(dfs_num[v] == -1) {
				dfs_num[v] = dfs_low[v] = cnt++;
				tarjanRec(map, v, dfs_num, dfs_low, st, vis);
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
			}
			else {
				if(vis[v]) {
					dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
				}
			}
		}
		
		if(dfs_num[u] == dfs_low[u]) {
			int v = st.pop();
			vis[v] = false;
			while(v != u) {
				v = st.pop();
				vis[v] = false;
			}
			scc++;
		}
	}
	
	
	static void tarjan(Set<Integer>[] map, int start, int[] dfs_num, int[] dfs_low, Stack<Integer> st, boolean[] vis) {
		//for a faster checking.
		Stack<Integer> process = new Stack<>();
		
		dfs_num[start] = dfs_low[start] = cnt++;
		process.push(start);
		vis[start] = true;
		
		while(process.size() > 0) {
			int u = process.pop();
			st.add(u);
			//System.out.println("st 1 : "+ u);
			for(int v : map[u]) {
				if(dfs_num[v] == -1) {
					dfs_num[v] = dfs_low[v] = cnt++;
					process.push(v);
					vis[v] = true;
				}
				else {
					if(vis[v]) {
						dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
					}
				}
			}
		}
		
		boolean ans = true;
		
		while(st.size() > 0) {  
			int u = st.pop();
			for(int v : map[u]) {
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
			}
			if(dfs_num[u] == dfs_low[u]) {
				scc++;
			}
			vis[u] = false;
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
