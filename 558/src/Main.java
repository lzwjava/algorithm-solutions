import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;
    boolean connected[][];
    int g[][];
    boolean vis[];
    boolean gvis[];
    int n;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    boolean dfs(int i, int year, int start) {                
        for (int j = 0; j < n; j++) {
            if (connected[i][j] && (!vis[j] || (vis[j] && j == start))) {
                int nyear = year + g[i][j];
                if (j == start) {
                    if (nyear < 0) {
                        return true;
                    }                   
                } else {
                    vis[j] = true;
                    if (dfs(j, nyear, start)) {
                        return true;
                    }
                    vis[j] = false;
                }
            }
        }
        return false;
    }
   
    void solve() throws IOException {
        int c = Integer.parseInt(in.readLine());
        while (c > 0) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            connected = new boolean[n][n];
            g = new int[n][n];
            for (int i = 0; i < m; i++) {
                s = in.readLine();
                st = new StringTokenizer(s);
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                connected[x][y] = true;
                g[x][y] = t;
            }
            vis = new boolean[n];
            gvis = new boolean[n];
            boolean ok = false;
            for (int i = 0; i < n; i++) {
                if (gvis[i]) {
                    continue;
                }
                vis[i] = true;
                gvis[i] = true;
                boolean res = dfs(i, 0, i);
                vis[i] = false;
                if (res) {
                    ok = true;
                    break;
                }
            }
            if (ok) {
                out.append("possible\n");                
            } else {
                out.append("not possible\n");
            }         
            c--;
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
