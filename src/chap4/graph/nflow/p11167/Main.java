package chap4.graph.nflow.p11167;

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
        throw e;
        // return;
      }


    }
    // out.flush();
    // out.close();
  }

  static int casen = 1;
  static StringBuffer sb = new StringBuffer();
  static Scanner scn = new Scanner(System.in);

  static int process() throws Exception {
    int n = in.readInt();
    if (n == 0)
      return 0;
    int m = in.readInt();
    boolean[] arr = new boolean[50001];

    Map<Integer, IntegerTriple> range = new HashMap<Integer, Main.IntegerTriple>();
    for (int i = 0; i < n; i++) {
      int v = in.readInt(), a = in.readInt(), b = in.readInt();
      arr[a] = true;
      arr[b] = true;
      range.put(i, new IntegerTriple(a, b, v));
    }
    int k = 0;
    Map<Integer, Integer> timeMap = new HashMap<Integer, Integer>();
    List<Integer> indMap = new ArrayList<Integer>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i]) {
        indMap.add(i);
        timeMap.put(i, k++);
      }
    }

    int len = k + n + 2;

    int s = k, t = k + 1, base = k + 2;

    int[][] mp = new int[len][len];
    for (int i = 0; i < n; i++) {
      mp[s][base + i] = range.get(i).c;
      int a = range.get(i).a, b = range.get(i).b;
      int stIndex = timeMap.get(a);
      while (indMap.get(stIndex) != b) {
        mp[base + i][stIndex] = indMap.get(stIndex + 1) - indMap.get(stIndex);
        stIndex++;
      }
    }
    for (int i = 0; i < (k - 1); i++) {
      int diff = indMap.get(i + 1) - indMap.get(i);
      mp[i][t] = diff * m;
    }
    //System.out.println(k);
    int ret = dinic(mp, s, t);
    int tot = ret;
    while (ret != 0) {
      //System.out.println("ret : "+ret);
      ret = dinic(mp, s, t);
      tot += ret;
    }
    //System.out.println(tot);
    //for (int i = 0; i < mp.length; i++) {
      //System.out.println(Arrays.toString(mp[i]));
    //}
    boolean success = true;
    for(int i = 0; i < n; i++) {
      if(mp[s][base + i] > 0) {
        success = false;
        break;
      }
    }
    int[] time = new int[50001];
    Arrays.fill(time, m);
    System.out.printf("Case %d: %s\n", casen++, success ? "Yes" : "No");
    if(success) {
      for(int i = 0; i < n; i++) {
        int ii = base + i;
        String str = "";
        int cnts = 0;
        List<Integer> pos = new ArrayList<Integer>();
        for(int j = 0; j < (k-1); j++) {
          if(mp[j][ii] > 0) {
            int st = indMap.get(j), en = indMap.get(j + 1);
            int qt = mp[j][ii];
            int v = st;
            for(; v < en && qt > 0; v++) {
              if(time[v] > 0) {
                time[v]--;
                qt--;
              }
            }
            if(pos.size() == 0 || pos.get(pos.size() - 1) != v - mp[j][ii]) {
              cnts++;
              pos.add(v - mp[j][ii]);
              pos.add(v);
            }
            else {
              pos.remove(pos.size() - 1);
              pos.add(v);
            }
          }
        }
        for(int j = 0; j < pos.size(); j+= 2) {
          str += " ("+pos.get(j) +","+pos.get(j + 1)+")";
        }
        str = cnts +""+str;
        System.out.println(str);
      }
    }

    return 1;
  }



  private static int dinic(int[][] mp, int s, int t) {
    int[] vis = new int[mp.length];
    Arrays.fill(vis, -1);
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(s);
    vis[s] = 0;
    int flow = 0;
    int lev = 0;
    Map<Integer, Set<Integer>> prev = new HashMap<Integer, Set<Integer>>();
    for(int i = 0; i < mp.length; i++)
      prev.put(i, new HashSet<Integer>());
    while (q.size() > 0) {
      int disc = 0;
       //System.out.println(q.size());
      Queue<Integer> tq = new LinkedList<Integer>();
      
      boolean tReached = false;
      lev++;
      while (q.size() > 0) {
        int a = q.remove();
        for (int b = 0; b < mp.length; b++) {
          if (mp[a][b] > 0) {
            if (b == t) {
              tReached = true;
            }
            if (vis[b] == -1) {
              vis[b] = lev;
              disc++;

            }
            if(!tq.contains(b)) {
              tq.add(b);
            }
            if(vis[b] == lev) {
              prev.get(b).add(a);
            }

          }
        }
      }
      q = tq;
      if (tReached) {
        List<Integer> path = new ArrayList<Integer>();
        path.add(t);
        return dfs(prev, mp, t, s, path);
      }
      if (disc == 0) {
        break;
      }
    }
    return 0;

  }

  private static int dfs(Map<Integer, Set<Integer>> prev, int[][] mp, int curr, int t, List<Integer> path) {
    int flow = 0;
    if(curr == t) {
      if(path.size() > 1) {
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < path.size(); i++) {
          int a = path.get(i-1), b = path.get(i);
          min = Math.min(mp[b][a], min);
        }
        //System.out.println(path +" : "+min);

        if(min > 0) {
          for(int i = 1; i < path.size(); i++) {
            int a = path.get(i-1), b = path.get(i);
            mp[b][a] -= min;
            mp[a][b] += min;
          }
          flow += min;
        }
      }
    }
    else {
      for(int b : prev.get(curr)) {
        if(mp[b][curr] > 0) {
          path.add(b);
          flow += dfs(prev, mp, b, t, path);
          path.remove(path.size() - 1);
        }
      }
      
    }
    return flow;
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
