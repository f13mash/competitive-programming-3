package chap2.ds.builtin.collections.p10258;

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

		readLine();
		while(cases-- > 0) {
			String line  = readLine();
			Map<Integer, Contestant> mp = new HashMap<>();
			while(line != null && line.trim().length() != 0) {
				String[] splts = line.trim().replaceAll("\\s+", " ").split(" ");
				int id = Integer.parseInt(splts[0]);
				int prob = Integer.parseInt(splts[1]);
				int time = Integer.parseInt(splts[2]);
				char code = splts[3].charAt(0);
				if(true || code == 'I' || code == 'C') {
					Contestant con = null;
					if(!mp.containsKey(id)) {
						con = new Contestant(id);
						mp.put(id, con);
					}
					else {
						con = mp.get(id);
					}
					con.addProblem(prob, code, time);
				}
				line = readLine();
			}
			
			List<Contestant> ll = new ArrayList<>(mp.values());
			Collections.sort(ll, new Comparator<Contestant>() {

				@Override
				public int compare(Contestant o1, Contestant o2) {
					if(o1.solved.size() != o2.solved.size()) {
						return - o1.solved.size() + o2.solved.size();
					}
					if(o1.extra != o2.extra)
						return o1.extra - o2.extra;
					
					return o1.id - o2.id;
				}
			});
			for(Contestant c : ll) {
				System.out.println(c.id+" "+c.solved.size()+" "+c.extra);
			}
			if(cases > 0)
				System.out.println();
		}
		return 0;
	}
	
	static class Contestant {
		Set<Integer> solved = new HashSet<>();
		int[] penalty = new int[11];
		int extra = 0;
		int id;
		public Contestant(int id) {
			this.id = id;
		}
		public void addProblem(int prob, char code, int time) {
			boolean done = solved.contains(prob);
			switch (code) {
			case 'C':
				if(!done) {
					solved.add(prob);
					extra += penalty[prob] + time;
				}
				break;
			case 'I':
				if(!done) {
					penalty[prob] += 20;
				}
				break;
			default:
				break;
			}
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
