import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Main {

    BufferedReader in;
    PrintWriter out;
    boolean graph[][];
    boolean vis[];
    int n;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);        
    }
   
    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        in.readLine();        
        while (t > 0) {
            String maxNode = in.readLine();
            ArrayList<String> edges = new ArrayList<>();
            HashSet<Character> nodes = new HashSet<>();
            nodes.add(maxNode.charAt(0));
            while (true) {
                String e = in.readLine();
                if (e == null || e.isEmpty()) {
                    break;
                }
                edges.add(e);
                char n1 = e.charAt(0);
                char n2 = e.charAt(1);
                nodes.add(n1);
                nodes.add(n2);
            }
            ArrayList<Character> nodeList = new ArrayList<>();
            nodeList.addAll(nodes);
            Collections.sort(nodeList);
            n = nodes.size();
            graph = new boolean[n][n];
            for (String e : edges) {
                char n1 = e.charAt(0);
                char n2 = e.charAt(1);
                int p1 = Collections.binarySearch(nodeList, n1);
                int p2 = Collections.binarySearch(nodeList, n2);
                graph[p1][p2] = graph[p2][p1] = true;
            }
            vis = new boolean[n];
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    vis[i] = true;
                    dfs(i);
                    count++;
                }
            }
            out.append(String.format("%d\n", count));            
            t--;
            if (t != 0) {
                out.append('\n');
            }
        }
    }

    private void dfs(int i) {
        for (int j = 0; j < n; j++) {
            if (graph[i][j] && !vis[j]) {
                vis[j] = true;
                dfs(j);
            }
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
