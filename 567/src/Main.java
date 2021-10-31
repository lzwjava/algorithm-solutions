import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    class State {
        int x;
        int dist;

        State(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }
    }
   
    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            int maxn = 20;
            ArrayList<Integer>[] adjNodes = new ArrayList[maxn];
            for (int i = 0; i < maxn; i++) {
                adjNodes[i] = new ArrayList<>();                
            }
            for (int i = 0; i < maxn-1; i++) {
                StringTokenizer st = new StringTokenizer(line);
                int x = Integer.parseInt(st.nextToken());
                for (int j = 0; j < x; j++) {
                    int y = Integer.parseInt(st.nextToken());
                    adjNodes[i].add(y - 1);
                    adjNodes[y - 1].add(i);
                }
                line = in.readLine();
            }
            int n = Integer.parseInt(line);

            out.append(String.format("Test Set #%d\n", caseNum));
            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(tokenizer.nextToken()) - 1;
                int b = Integer.parseInt(tokenizer.nextToken()) - 1;

                ArrayBlockingQueue<State> queue = new ArrayBlockingQueue<>(maxn * maxn * 2);                
                queue.add(new State(a, 0));
                boolean[] vis = new boolean[maxn];
                vis[a] = true;
                while (!queue.isEmpty()) {
                    State st = queue.poll();
                    if (st.x == b) {
                        out.append(String.format("%d to %d: %d\n", a + 1, b + 1, st.dist));
                        break;
                    }
                    for (Integer y : adjNodes[st.x]) {
                        if (!vis[y]) {
                            vis[y] = true;
                            queue.add(new State(y, st.dist + 1));
                        }
                    }
                }
            }
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
