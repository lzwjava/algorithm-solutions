import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        // in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    class Edge implements Comparable<Edge> {
        int c1;
        int c2;
        int p;

        Edge() {
        }

        Edge(int c1, int c2, int p) {
            this.c1 = c1;
            this.c2 = c2;
            this.p = p;
        }

        @Override
        public int compareTo(Main.Edge o) {
            return o.p - p;
        }
    }
    
    int getGroup(int[] groups, int x) {
        if (groups[x] == x) {
            return x;
        } else {
            return getGroup(groups, groups[x]);
        }
    }
   
    void solve() throws IOException {
        Scanner sc = new Scanner(System.in);
        int caseNum = 1;
        while (true) {
            int n = sc.nextInt();
            int r = sc.nextInt();
            if (n == 0 && r == 0) {
                break;
            }
            ArrayList<Edge> edges = new ArrayList<>();
            for (int i = 0; i < r; i++) {
                Edge e = new Edge();
                e.c1 = sc.nextInt();
                e.c2 = sc.nextInt();
                e.p = sc.nextInt();
                edges.add(e);
            }
            int s = sc.nextInt();
            int d = sc.nextInt();
            int t = sc.nextInt();

            int[] groups = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                groups[i] = i;
            }

            Collections.sort(edges);

            out.append(String.format("Scenario #%d\n", caseNum));

            for (Edge e : edges) {
                int g1 = getGroup(groups, e.c1);
                int g2 = getGroup(groups, e.c2);
                if (g1 != g2) {
                    groups[g2] = g1;
                    if (getGroup(groups, s) == getGroup(groups, d)) {
                        double x = t * 1.0 / (e.p - 1);                        
                        int trip = (int)Math.ceil(x);
                        // int trip = (t + e.p - 1) / e.p;
                        out.append(String.format("Minimum Number of Trips = %d\n", trip));
                        out.append('\n');
                        break;
                    }
                }
            }
            caseNum++;
        }
        sc.close();
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
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
