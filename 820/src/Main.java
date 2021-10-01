import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);        
    }

    class Edge implements Comparable<Edge>{
        int x;
        int y;
        int bandwidth;

        Edge(){            
        }

        Edge(int x, int y, int bandwidth){
            this.x = x;
            this.y = y;
            this.bandwidth = bandwidth;
        }

        @Override
        public int compareTo(Main.Edge o) {
            return bandwidth - o.bandwidth;
        }
    }

    int getGroup(int[] groups, int x){
        if (groups[x] == x){
            return x;
        } else {
            return getGroup(groups, groups[x]);
        }
    }

    int maxBandwidth;

    void dfs(int[][] graph, boolean vis[], int n, int s, int t, int bandwidth){
        if (s == t){
            if (bandwidth > maxBandwidth){
                maxBandwidth = bandwidth;
            }
            return;
        }
        for(int i=0;i<n;i++){
            if (graph[s][i] > 0 && !vis[i]){
                vis[i] = true;
                dfs(graph, vis, n, i, t, bandwidth + graph[s][i]);
                vis[i] = false;                
            }
        }
    }
   
    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            String str = in.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int s = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken())  -1;
            int c = Integer.parseInt(st.nextToken());            
            ArrayList<Edge> edges = new ArrayList<>();
            for(int i=0;i<c;i++){         
                st = new StringTokenizer(in.readLine());
                int x,y,b;
                x = Integer.parseInt(st.nextToken());                                                                                             
                y = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());                
                Edge e = new Edge(x-1, y-1,b);
                edges.add(e);
            }
            Collections.sort(edges);
            int[] groups = new int[n];
            for(int i=0;i<n;i++){
                groups[i] = i;
            }
            int i;            
            for(i=0;i<edges.size();i++){
                Edge e = edges.get(i);
                int gx = getGroup(groups, e.x);
                int gy = getGroup(groups, e.y);
                if (gx!=gy){
                    groups[gy] = gx;
                    int gs = getGroup(groups, s);
                    int gt = getGroup(groups, t);
                    if (gs == gt){
                        break;
                    }
                }
            }            
            int[][] graph = new int[n][n];
            for(int j=0;j<=i;j++)          {
                Edge e = edges.get(j);
                graph[e.x][e.y] = graph[e.y][e.x] = e.bandwidth;
            }
            boolean[] vis = new boolean[n];
            vis[s] = true;
            out.append(String.format("Network %d\n", caseNum));            
            maxBandwidth = 0;
            dfs(graph,vis, n, s, t, 0);                        
            out.append(String.format("The bandwidth is %d.\n", maxBandwidth));
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
