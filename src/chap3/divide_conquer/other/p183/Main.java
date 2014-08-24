package chap3.divide_conquer.other.p183;


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
		String type = in.readString();
		if(type.trim().compareTo("#") == 0)
			return 0;
		
		int n = in.readInt(), m = in.readInt();
		char[] arr = in.readString().trim().toCharArray();
		int ind = 0;
		if(type.compareTo("B") == 0) {
			int[][] mp = new int[n][m];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(ind == arr.length) {
						arr = in.readString().trim().toCharArray();
						ind = 0;
					}
					mp[i][j] = arr[ind] - '0';
					ind++;
				}
			}
			int[][] sum = new int[n + 1][m + 1];
			for(int i = n-1; i>=0; i--) {
				for(int j =m-1; j>= 0; j--) {
					sum[i][j] = sum[i][j+1] + sum[i+1][j] - sum[i+1][j+1] + mp[i][j];
				}
			}
			/*
			for(int i = 0; i <= n; i++){
				//System.out.println(Arrays.toString(sum[i]));
			}*/
			sb.append(String.format("D%4d%4d\n", n, m));
			String res = divide(mp, sum, 0, 0, n, m);
			String print = null;
			while(res.length() > 50) {
				sb.append(res.substring(0, 50));
				sb.append("\n");
				res = res.substring(50);
			}
			sb.append(res);
		}
		else if(type.compareTo("D") == 0) {
			int[][] mp = new int[n][m];
			cmd = arr;
			conquer(mp, 0, 0, n, m, 0);
			sb.append(String.format("B%4d%4d\n", n, m));
			int len = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(len == 50) {
						len = 0;
						sb.append("\n");
					}
					sb.append(mp[i][j]);
					len++;
				}
			}
		}

		sb.append("\n");
		return 1;
	}
	static char[] cmd = null;
	static int conquer(int[][] mp, int x, int y, int n, int m, int ind) {
		//System.out.println(x+" : "+y+" : "+n+" : "+m);
		if(ind == cmd.length) {
			ind = 0;
			cmd = in.readString().trim().toCharArray();
		}
		if(cmd[ind] == 'D') {
			ind++;
			if(n == 1) {
				int lm = (int) Math.ceil((double)m/2);
				ind = conquer(mp, x, y, n, lm, ind);
				ind = conquer(mp, x, y + lm, n, m - lm, ind);
				return ind;
			}
			else if(m == 1){
				int tn = (int) Math.ceil((double)n/2);
				ind = conquer(mp, x, y, tn, m, ind);
				ind = conquer(mp, x + tn, y, n - tn, m, ind);
				return ind;
			}
			else {
				int lm = (int) Math.ceil((double)m/2);
				int tn = (int) Math.ceil((double)n/2);
				ind = conquer(mp, x, y, tn, lm, ind);
				ind = conquer(mp, x, y+lm, tn, m-lm, ind);
				ind = conquer(mp, x+tn, y, n-tn, lm, ind);
				ind = conquer(mp, x+tn, y+lm, n-tn, m-lm, ind);
				return ind;
			}
		}
		else {
			int val = cmd[ind] - '0';
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					mp[x+i][y+j] = val;
				}
			}
			return ind + 1;
		}
	}
	static String divide(int[][] mp, int[][] sum, int x, int y, int n, int m) {
		int cnt = sum[x][y] - sum[x+n][y] - sum[x][y+m] + sum[x + n][y + m];
		//System.out.println(x+" : "+y+" : "+n+" : "+m+" : "+cnt);

		if(cnt == 0 || cnt == (n*m)) {
			return Integer.toString(cnt > 0 ? 1 : 0);
		}
		if(m ==1 && n == 1) {
			return Integer.toString(mp[x][y]);
		}
		else if(n == 1) {
			int lm = (int) Math.ceil((double)m/2);
			return "D"+divide(mp, sum, x, y, n, lm)+divide(mp, sum, x, y + lm, n, m-lm);
		}
		else if(m == 1) {
			int tn = (int) Math.ceil((double)n/2);
			return "D"+divide(mp, sum, x, y, tn, m)+divide(mp, sum, x + tn, y , n-tn, m);
		}
		else {
			int lm = (int) Math.ceil((double)m/2);
			int tn = (int) Math.ceil((double)n/2);
			return "D"+divide(mp, sum, x, y, tn, lm)+divide(mp, sum, x, y+lm, tn, m-lm)+divide(mp, sum, x+tn, y, n-tn, lm)+divide(mp, sum, x+tn, y+lm, n-tn, m-lm);
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
