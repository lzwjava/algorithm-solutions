import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Edge {
        int u, v;

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    int n;
    ArrayList<Integer>[] adjNodes;

    void dfs(Set<Integer> nodes, int u) {
        nodes.add(u);
        for (int i = 0; i < adjNodes[u].size(); i++) {
            int v = adjNodes[u].get(i);
            if (!nodes.contains(v)) {
                dfs(nodes, v);
            }
        }
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            n = Integer.parseInt(line);
            adjNodes = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjNodes[i] = new ArrayList<Integer>();
            }
            ArrayList<Edge> edges = new ArrayList<Edge>();
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(st.nextToken());
                String ks = st.nextToken();
                int p1 = ks.indexOf('(');
                int p2 = ks.lastIndexOf(')');
                int k = Integer.parseInt(ks.substring(p1 + 1, p2));
                for (int j = 0; j < k; j++) {
                    int b = Integer.parseInt(st.nextToken());
                    adjNodes[a].add(b);
                    if (a < b) {
                        edges.add(new Edge(a, b));
                    }
                }
            }
            ArrayList<Edge> criticals = new ArrayList<Edge>();
            for (Edge edge : edges) {
                Set<Integer> nodes1 = new HashSet<Integer>();
                dfs(nodes1, edge.u);

                adjNodes[edge.u].remove((Integer) edge.v);
                adjNodes[edge.v].remove((Integer) edge.u);

                Set<Integer> nodes2 = new HashSet<Integer>();
                dfs(nodes2, edge.u);

                Set<Integer> nodes3 = new HashSet<Integer>();
                dfs(nodes3, edge.v);

                if (nodes2.size() + nodes3.size() == nodes1.size()) {
                    criticals.add(edge);
                }

                adjNodes[edge.u].add(edge.v);
                adjNodes[edge.v].add(edge.u);
            }

            out.append(String.format("%d critical links\n", criticals.size()));
            for (Edge e : criticals) {
                out.append(String.format("%d - %d\n", e.u, e.v));
            }
            out.append('\n');

            in.readLine();
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
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
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
