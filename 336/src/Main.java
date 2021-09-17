import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Pair {
        int a;
        int b;

        Pair() {
        }

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    
    class Query {
        int node;
        int ttl;

        Query() {
        }

        Query(int node, int ttl) {
            this.node = node;
            this.ttl = ttl;
        }
    }
   
    void solve() throws IOException {
        char cbuf[] = new char[10000];
        StringBuilder sb = new StringBuilder();
        while (true) {
            int len = in.read(cbuf);
            if (len == -1) {
                break;
            }
            sb.append(cbuf, 0, len);
        }

        String content = sb.toString();
        StringTokenizer st = new StringTokenizer(content);

        int caseNum = 1;
        for (;;) {
            int nc = Integer.parseInt(st.nextToken());
            if (nc == 0) {
                break;
            }
            Pair[] pairs = new Pair[nc];
            for (int i = 0; i < nc; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                Pair p = new Pair(a, b);
                pairs[i] = p;
            }

            ArrayList<Query> qs = new ArrayList<>();
            while (true) {
                int node = Integer.parseInt(st.nextToken());
                int ttl = Integer.parseInt(st.nextToken());
                if (node == 0 && ttl == 0) {
                    break;
                }
                Query query = new Query(node, ttl);
                qs.add(query);
            }

            HashSet<Integer> nodeSet = new HashSet<>();
            for (int i = 0; i < pairs.length; i++) {
                nodeSet.add(pairs[i].a);
                nodeSet.add(pairs[i].b);
            }
            // for (int i = 0; i < qs.size(); i++) {
            //     nodeSet.add(qs.get(i).node);
            // }            
            ArrayList<Integer> nodes = new ArrayList<>();
            nodes.addAll(nodeSet);
            Collections.sort(nodes);

            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nodes.size(); i++) {
                map.put(nodes.get(i), i);
            }

            int n = nodes.size();

            boolean[][] graph = new boolean[n][n];
            for (int i = 0; i < pairs.length; i++) {
                Pair p = pairs[i];
                int ia = map.get(p.a);
                int ib = map.get(p.b);
                graph[ia][ib] = graph[ib][ia] = true;
            }

            for (Query q : qs) {
                Integer queryIndex = map.get(q.node);
                int count = 0;
                if (queryIndex != null) {
                    boolean vis[] = new boolean[n];
                    bfs(graph, queryIndex, n, vis, 0, q.ttl);
                    count = 0;
                    for (int i = 0; i < vis.length; i++) {
                        if (!vis[i]) {
                            count++;
                        }
                    }
                } else {
                    count = n;
                }

                out.append(String.format("Case %d: %d nodes not reachable from node %d with TTL = %d.\n", caseNum,
                        count, q.node, q.ttl));
                caseNum++;
            }
        }
    }
    
    class State {
        int node;
        int dist;

        State(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
    
    void bfs(boolean graph[][], int cur, int n, boolean vis[], int dist, int maxDist) {
        ArrayBlockingQueue<State> queue = new ArrayBlockingQueue<>(n);
        State st = new State(cur, 0);
        queue.add(st);
        vis[cur] = true;
        while (!queue.isEmpty()) {
            State curState = queue.poll();
            if (curState.dist >= maxDist) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                if (graph[curState.node][i] && !vis[i]) {                    
                    vis[i] = true;
                    if (curState.dist+1 >= maxDist){
                        continue;
                    } else {
                        State ns = new State(i, curState.dist + 1);
                        queue.add(ns);
                    }
                }
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
