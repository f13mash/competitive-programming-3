package chap4.graph.toposort.p872;

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
				//throw e;
				return;
			}
            
            
		}
		//out.flush();
		//out.close();
	}
	
	static int casen = 1;
	static StringBuffer sb = new StringBuffer();
	static int process() throws Exception{
		int cases = Integer.parseInt(readLine().trim());
		while(cases-- > 0) {
			readLine();
			String[] splts = readLine().trim().split(" ");
			long[] mp = new long[26];
			Arrays.fill(mp, -1);
			
			char[] arr = new char[splts.length];
			int n = 0;
			for(String str : splts) {
				char c = str.charAt(0);
				if(c >= 'A' && c <= 'Z') {
					int v = c - 'A';
					mp[v] = 0;
					arr[n++] = c;
				}
			}
			String[] rel = readLine().trim().split(" ");
			for(String r : rel) {
				if(r.length() > 2) {
					int a = r.charAt(0) - 'A';
					int b = r.charAt(2) - 'A';
					if(mp[a] == -1 || mp[b] == -1) {
						throw new Exception("wrong data");
					}
					else {
						mp[b] |= (1L << a);
					}
				}
			}
			
			cnt = 0;
			topoSort(mp, arr, new char[n], 0, 0);
			if(cnt == 0)
				sb.append("NO\n");

			if(cases > 0) {
				sb.append("\n");
			}
		}
		return 0;
	}
	static int cnt = 0;
	static void topoSort(long[] mp, char[] arr, char[] ans, int curr, long state) {
		int n = ans.length;
		if(curr == ans.length) {
			cnt++;
			String str = "";
			for(char c : ans) {
				str+=c+" ";
			}
			//System.out.println(str);
			sb.append(str.trim()+"\n");
			return;
		}
		for(int i = 0; i < n; i++) {
			int v = arr[i] - 'A';
			if((state & (1L << v)) == 0 && (state & mp[v]) == mp[v]) {
				ans[curr] = arr[i];
				topoSort(mp, arr, ans, curr + 1, state | (1<<v));
			}
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
