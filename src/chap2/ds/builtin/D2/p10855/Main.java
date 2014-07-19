package chap2.ds.builtin.D2.p10855;

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
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;

public class Main {
	/**
	 * @param args
	 */
	static InputReader in 		= new InputReader(System.in);
    static OutputWriter out	=	new OutputWriter(System.out);
	public static void main(String[] args) {

		while(true) {
            if(process()==0) {
            	break;
            }
            
		}
		out.flush();
		out.close();
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
	
	static int process() {
		int[] arr = IOUtils.readIntArray(in, 2); 
		if(arr[0] == 0 && arr[1] == 0)
			return 0;
		int n = arr[0], m = arr[1];
		String[] a = new String[n];
		String[] aa = new String[n];
		String[] b = new String[m];
		for(int i = 0; i < n; i++) {
			a[i] = in.readString().trim();
			
			char[] t = a[i].toCharArray();
			for(int j = 0; j < n; j++) {
				if(aa[j] == null) 
					aa[j] = "";
				aa[j] = t[j]+aa[j];
			}
		}
		for(int i = 0; i < m; i++) {
			b[i] = in.readString().trim();
		}
		int[] cnts = {0, 0, 0, 0};
		cnts[0] = occurs(a, b, m, n);
		//System.out.println("case1");
		cnts[3] = occurs(aa, b, m, n);
		//System.out.println("case2");
		for(int i = 0; i < (n/2); i++) {
			int j = n - 1 - i;
			a[i] = new StringBuilder(a[i]).reverse().toString();
			a[j] = new StringBuilder(a[j]).reverse().toString();
			String t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
		if(n % 2 == 1) {
			a[(n)/2] = new StringBuilder(a[(n)/2]).reverse().toString();
		}
		for(int i = 0; i < (n/2); i++) {
			int j = n - 1 - i;
			aa[i] = new StringBuilder(aa[i]).reverse().toString();
			aa[j] = new StringBuilder(aa[j]).reverse().toString();
			String t = aa[i];
			aa[i] = aa[j];
			aa[j] = t;
		}
		if(n % 2 == 1) {
			aa[(n)/2] = new StringBuilder(aa[(n)/2]).reverse().toString();
		}
		cnts[2] = occurs(a, b, m, n);
		cnts[1] = occurs(aa, b, m, n);
		
		String str = "";
		for(int val : cnts) {
			str+=" "+val;
		}
		System.out.println(str.trim());
		return 1;
	}
	static int occurs(String[] a, String[] b, int m, int n) {
		int cnt = 0;
		for(int i = 0; i <= (n-m); i++) {
			int j = -1;
			
			while((j = a[i].indexOf(b[0], j+1)) != -1) {
				//System.out.println(i+" : "+j);
				boolean match = true;
				for(int k = 1; k <m; k++) {
					if(a[i + k].indexOf(b[k], j) != j) {
						match = false;
						break;
					}
				}
				if(match) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	public static String threePlaces(int val) {
		return ("   " + val).substring(Integer.toString(val).length());
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
