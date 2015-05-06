package chap4.graph.nflow.var.p11380;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;


//Stuck with WA ;(
public class Main {
  /**
   * @param args
   */
  static InputReader in = new InputReader(System.in);
  // static OutputWriter out = new OutputWriter(System.out);
  static BufferedReader br = null;

  public static void main(String[] args) throws Exception {
    br = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      try {
        if (process() == 0) {
          System.out.print(sb);
          break;
        }
      } catch (Exception e) {
        // System.out.print(sb);
        //throw e;
         return;
      }


    }
    // out.flush();
    // out.close();
  }

  static int casen = 1;
  static StringBuffer sb = new StringBuffer();
  static Scanner scn = new Scanner(System.in);

  static int process() throws Exception {
    int x = in.readInt(), y = in.readInt(), p = in.readInt();
    int[][][] mp = new int[x + 2][y + 2][2];
    int cnt = 1;
    
    int[][] dat = new int[x + 2][y + 2];
    for(int i = 1; i <= x; i++) {
      char[] arr = in.readString().trim().toCharArray();
      for(int j = 1; j <= y; j++) {
        switch (arr[j-1]) {
          case '*':
            dat[i][j] = 1;
            mp[i][j][0] = cnt++;
            mp[i][j][1] = cnt++;
            break;
          case '.':
            dat[i][j] = 2;
            mp[i][j][0] = cnt++;
            mp[i][j][1] = cnt++;
            break;
          case '@':
            dat[i][j] = 3;
            mp[i][j][0] = cnt++;
            mp[i][j][1] = cnt++;
            break;
          case '#':
            dat[i][j] = 4;
            mp[i][j][0] = cnt++;
            mp[i][j][1] = cnt++;
            break;
          default:
            break;
        }
      }
    }
    int max = 100000000;
    int bh = 0;
    int src = cnt++, des = cnt++;
    int[][] mat = new int[cnt][cnt];
    int people = 0;
    for(int i = 1; i <= x; i++) {
      for(int j = 1; j <= y; j++) {
        int in = mp[i][j][0], out = mp[i][j][1];
        switch (dat[i][j]) {
          case 1:
            people++;
            mat[src][in] = 1;
            mat[in][src] = 1;
            mat[in][out] = 1;
            mat[out][in] = 1;
            mp[i][j][0] = 0;
            break;
          case 2:
            mat[in][out] = 1;
            mat[out][in] = 1;
            break;
          case 3:
            mat[in][out] = max;
            mat[out][in] = max;
            mat[out][des] = 0;
            mat[des][out] = 0;
            mp[i][j][1] = mp[i][j][0];
            break;
          case 4:
            mat[in][out] = max;
            mat[out][in] = max;
            mat[out][des] = p;
            mat[des][out] = p;
            mp[i][j][1] = mp[i][j][0];
            break;
          default:
            break;
        }
      }
    }
    for(int i = 1; i <= x; i++) {
      for(int j = 1; j <= y; j++) {
        if(dat[i][j] == 0)
          continue;
        addEdge(mat, dat, mp, i, j, i-1, j);
        addEdge(mat, dat, mp, i, j, i+1, j);
        addEdge(mat, dat, mp, i, j, i, j-1);
        addEdge(mat, dat, mp, i, j, i, j+1);
      }
    }
//    for(int i = 0; i < mat.length; i++) {
//      System.out.println(Arrays.toString(mat[i]));
//    }
    System.out.println(karpFlow(mat, src, des));
    
    return 1;
  }
  
  static int karpFlow(int[][] mp, int s, int t) {
    int flow = 0;
    int nodes = mp.length;
    while(true) {
      Queue<Integer> q = new LinkedList<Integer>();
      int[][] vis = new int[2][nodes];
      Arrays.fill(vis[0], -1);
      Arrays.fill(vis[1], Integer.MAX_VALUE);
      
      q.add(s);
      vis[0][s] = s;
      while(q.size() > 0) {
        int a = q.remove();
        int min = vis[1][a];
        if(a == t)
          break;
        for(int b = 0; b < nodes; b++) {
          if(mp[a][b] > 0 && vis[0][b] == -1) {
            q.add(b);
            vis[0][b] = a;
            vis[1][b] = Math.min(mp[a][b], min);
          }
        }
      }
      
      if(vis[0][t] == -1) {
        break;
      }
      flow += vis[1][t];
      int curr = t, min = vis[1][t];
      while(curr != s) {
        mp[vis[0][curr]][curr] -= min;
        mp[curr][vis[0][curr]] += min;
        curr = vis[0][curr];
      }
    }
    return flow;
  }
  
  

  private static void addEdge(int[][] mat, int[][] dat, int[][][] mp, int i, int j, int i1, int j1) {

    if(dat[i1][j1] == 0 || dat[i][j] == 0)
      return;
    if(mp[i1][j1][0] == 0)
      return;
    int max = 100000000;
    int s = mp[i][j][1], d = mp[i1][j1][0];
    switch (dat[i1][j1]) {
      case 2:
        mat[s][d] = max;
        break;
      case 3:
        mat[s][d] = max;
        break;
      case 4:
        mat[s][d] = max;
        break;
      default:
        break;
    }
  }



  public static class IntegerPair {
    int a;
    int b;

    public IntegerPair(int a, int b) {
      this.a = a;
      this.b = b;
    }
  }

  public static class IntegerTriple {
    int a;
    int b;
    int c;

    public IntegerTriple(int a, int b, int c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }
  }

  static String readLine() {
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
    for (int i = 0; i < str.length; i++) {
      if (str[i].compareTo("") != 0)
        ret[i] = Integer.parseInt(str[i].trim());
    }
    return ret;
  }

  static List<Integer> giveArrayList(String[] str) {
    List<Integer> ret = new ArrayList<>();
    for (int i = 0; i < str.length; i++) {
      if (str[i].compareTo("") != 0)
        ret.add(Integer.parseInt(str[i].trim()));
    }
    return ret;
  }

  static int findClosest(List<Integer> arr, int val, boolean comp, int s, int e) {
    // System.out.println(s+" : "+e+" : "+val+ " : "+Arrays.toString(arr));
    if (s == -1 || e == -1 || s > e)
      return -1;
    if (arr.size() == 0)
      return -1;
    if (comp && arr.get(s) > val) {
      return -1;
    }

    if (!comp && arr.get(e) < val) {
      return -1;
    }

    if (s == e) {
      return s;
    }

    if ((e - s) == 1) {
      if (comp) {
        if (arr.get(e) <= val) {
          return e;
        } else {
          return s;
        }
      } else {
        if (arr.get(s) >= val) {
          return s;
        } else {
          return e;
        }
      }
    }
    int mid = (s + e) / 2;
    if (comp) {
      if (arr.get(mid) > val) {
        return findClosest(arr, val, comp, s, mid);
      } else {
        return findClosest(arr, val, comp, mid, e);
      }
    } else {
      if (arr.get(mid) < val) {
        return findClosest(arr, val, comp, mid, e);
      } else {
        return findClosest(arr, val, comp, s, mid);
      }
    }
  }

  static int findClosest(int[] arr, int val, boolean comp, int s, int e) {
    // System.out.println(s+" : "+e+" : "+val+ " : "+Arrays.toString(arr));
    if (s == -1 || e == -1 || s > e)
      return -1;
    if (arr.length == 0)
      return -1;
    if (comp && arr[s] > val) {
      return -1;
    }

    if (!comp && arr[e] < val) {
      return -1;
    }

    if (s == e) {
      return s;
    }

    if ((e - s) == 1) {
      if (comp) {
        if (arr[e] <= val) {
          return e;
        } else {
          return s;
        }
      } else {
        if (arr[s] >= val) {
          return s;
        } else {
          return e;
        }
      }
    }
    int mid = (s + e) / 2;
    if (comp) {
      if (arr[mid] > val) {
        return findClosest(arr, val, comp, s, mid);
      } else {
        return findClosest(arr, val, comp, mid, e);
      }
    } else {
      if (arr[mid] < val) {
        return findClosest(arr, val, comp, mid, e);
      } else {
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

  public void print(Object... objects) {
    for (int i = 0; i < objects.length; i++) {
      if (i != 0)
        writer.print(' ');
      writer.print(objects[i]);
    }
  }

  public void printLine(Object... objects) {
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
    for (int i = 0; i < size; i++)
      this.arr[i] = i;
  }

  public void add(int a, int b) {
    while (arr[a] != a) {
      a = arr[a];
    }
    while (arr[b] != b) {
      b = arr[b];
    }
    if (a > b) {
      int t = a;
      a = b;
      b = t;
    }
    arr[b] = a;
  }

  public int rootOf(int a) {
    int ta = a;

    while (arr[a] != a)
      a = arr[a];

    return a;
  }

  public boolean areConnected(int a, int b) {
    return rootOf(a) == rootOf(b);
  }

  public boolean disconnect(int a, int b) {
    if (arr[a] == b) {
      arr[a] = a;
      return true;
    } else if (arr[b] == a) {
      arr[b] = b;
      return false;
    } else
      return false;
  }
}
