import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    class Edge {
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
    }

    int maxP;
    
    void dfs(int[][] graph, boolean vis[], int n, int s, int t, int p) {       
        if (s == t) {
            if (p > maxP) {
                maxP = p;
            }
            return;
        }
        if (p < maxP) {
            return;
        }
        for (int i = 0; i < n; i++) {
            if (graph[s][i] > 0 && !vis[i]) {
                vis[i] = true;
                int np = p;
                if (graph[s][i] < np) {
                    np = graph[s][i];
                }
                dfs(graph, vis, n, i, t, np);
                vis[i] = false;
            }
        }
    }
   
    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);            
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if (n==0 && r==0){
                break;
            }
            ArrayList<Edge> edges = new ArrayList<>();
            for (int i = 0; i < r; i++) {
                line = in.readLine();
                st = new StringTokenizer(line);
                Edge e = new Edge();
                e.c1 = Integer.parseInt(st.nextToken());
                e.c2 = Integer.parseInt(st.nextToken());
                e.p = Integer.parseInt(st.nextToken());
                edges.add(e);
            }
            line = in.readLine();
            st = new StringTokenizer(line);
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            
            int[] groups = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                groups[i] = i;
            }
                                
            
            int[][] graph = new int[n][n];
            for (Edge e : edges) {
                graph[e.c1 - 1][e.c2 - 1] = graph[e.c2 - 1][e.c1 - 1] = e.p;
            }
            boolean vis[] = new boolean[n];
            vis[s - 1] = true;
            maxP = 0;
            dfs(graph, vis, n, s - 1, d - 1, Integer.MAX_VALUE / 2);
            out.append(String.format("Scenario #%d\n", caseNum));
            double x = t*1.0 / (maxP - 1);
            int trip = (int)Math.ceil(x);
            out.append(String.format("Minimum Number of Trips = %d\n", trip));
            out.append('\n');
            caseNum++;
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
