import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;


    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    void solve() throws IOException {
        int N = Integer.parseInt(in.readLine());
        int caseNum = 1;
        while (N > 0) {
            String str = in.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int graph[][] = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(graph[i], -1);
            }
            for (int i = 0; i < m; i++) {
                str = in.readLine();
                st = new StringTokenizer(str);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                graph[a][b] = graph[b][a] = w;
            }
            int dist[] = new int[n];
            for (int i = 0; i < n; i++) {
                dist[i] = Integer.MAX_VALUE / 2;
            }
            dist[s] = 0;
            boolean vis[] = new boolean[n];
            for (int i = 0; i < n; i++) {
                int minDist = Integer.MAX_VALUE;
                int x = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    if (!vis[j] && dist[j] < minDist) {
                        x = j;
                        minDist = dist[j];
                    }
                }
                vis[x] = true;
                for (int j = 0; j < n; j++) {
                    if (graph[x][j] != -1 && dist[j] > dist[x] + graph[x][j]) {
                        dist[j] = dist[x] + graph[x][j];
                    }
                }
            }
            if (dist[t] == Integer.MAX_VALUE / 2) {
                out.append(String.format("Case #%d: unreachable\n", caseNum));
            } else {
                out.append(String.format("Case #%d: %d\n", caseNum, dist[t]));
            }
            caseNum++;
            N--;
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
