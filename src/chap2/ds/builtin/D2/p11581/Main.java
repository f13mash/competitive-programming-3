package chap2.ds.builtin.D2.p11581;

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
	static BufferedReader br=null;

	public static void main(String[] args) {
		br=new BufferedReader(new InputStreamReader(System.in));

		while(true) {
            if(process()==0) {
            	break;
            }
            
		}
		out.flush();
		out.close();
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
	
	static int process() {
		int cases = Integer.parseInt(readLine().trim());
		while(cases > 0) {
			readLine();
			int[][][] mp = new int[2][5][5];
			for(int i = 1; i <= 3; i++) {
				int num = Integer.parseInt(readLine().trim());
				for(int j = 1; j <= 3; j++) {
					mp[0][i][j] = (num >> (j-1)) & 1;
				}
			}
			int[] cnt = new int[1 << 10];
			Arrays.fill(cnt, -1);
			int curr = 0;
			int ans = 0;
			do {
				int hc = hash(mp[curr%2]);
				//System.out.println(Integer.toBinaryString(hc)+" : "+curr);
				if(cnt[hc] != -1) {
					ans = cnt[hc] - 1;
					break;
				}
				else {
					cnt[hc] = curr;
				}
				transform(mp[curr%2], mp[(curr+1)%2]);
				curr++;
			}while(true);
			cases--;
			System.out.println(ans);
		}
		//System.out.println("s : "+s+" : "+rel);
		return 0;
	}
	public static void transform(int[][] mp, int[][] cp) {
		for(int i = 1; i <= 3; i++) {
			for(int j = 1; j <= 3; j++) {
				cp[i][j] = mp[i-1][j] + mp[i+1][j] + mp[i][j+1] + mp[i][j-1];
				cp[i][j] %= 2;
			}
		}
	}
	public static int hash(int[][] mp) {
		int ret = 0;
		for(int i = 1; i <= 3; i++) {
			for(int j = 1; j <= 3; j++) {
				ret = ret << 1;
				ret |= mp[i][j];
			}
		}
		return ret;
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
