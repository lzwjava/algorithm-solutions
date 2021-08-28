import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int calLen(int x) {
        int len = 0;
        while (true) {
            if (x % 2 == 0) {
                x /= 2;
            } else {
                x = 3 * x + 1;
            }
            len++;
            if (x == 1) {
                break;
            }
        }
        return len;
    }
    
    class Query {
        int l;
        int h;
        
        Query() {
        }
        
        Query(int l, int h) {
            this.l = l;
            this.h = h;
        }
    }
   
    void solve() throws IOException {
        ArrayList<Query> qs = new ArrayList<>();
        int minL = Integer.MAX_VALUE;
        int maxH = 0;
        while (true) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            if (l == 0 && h == 0) {
                break;
            }
            Query q = new Query(l, h);
            qs.add(q);
            if (l < minL) {
                minL = l;
            }
            if (h > maxH) {
                maxH = h;
            }
         
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = minL; i <= maxH; i++) {
            int len = calLen(i);
            map.put(i, len);
        }
        for (Query q : qs) {
            int maxLen = 0;
            int maxI = 0;
            for (int i = q.l; i <= q.h; i++) {
                int ilen = map.get(i);
                if (ilen > maxLen) {
                    maxLen = ilen;
                    maxI = i;
                }
            }
            out.append(String.format("Between %d and %d, %d generates the longest sequence of %d values.\n",
             q.l, q.h, maxI , maxLen));
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();              
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
