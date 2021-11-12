import java.io.*;
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
        int idx;
        double v;
        int dist;
        ArrayList<Integer> path;

        State() {
            this.path = new ArrayList<Integer>();
        }

        State(int idx, double v, int dist) {
            this.idx = idx;
            this.v = v;
            this.dist = dist;
            this.path = new ArrayList<Integer>();
        }
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            int n = Integer.parseInt(line.trim());
            double[][] table = new double[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for (int j = 0; j < n; j++) {
                    double v;
                    if (i == j) {
                        v = 1.0;
                    } else {
                        v = Double.parseDouble(st.nextToken());
                    }
                    table[i][j] = v;
                }
            }
            int minDist = Integer.MAX_VALUE;
            ArrayList<Integer> minPath = null;
            double maxValue = 0;
            for (int i = 0; i < n; i++) {
                ArrayBlockingQueue<State> queue = new ArrayBlockingQueue<State>(n * n * 1000);
                State start = new State(i, 1.0, 0);
                start.path.add(i);
                queue.add(start);
                boolean found = false;
                int dist = Integer.MAX_VALUE;
                ArrayList<Integer> path = null;
                while (!queue.isEmpty()) {
                    State st = queue.poll();
                    if (st.dist >= n - 1) {
                        continue;
                    }
                    for (int j = 0; j < n; j++) {
                        if (j != st.idx) {
                            double nv = st.v * table[st.idx][j];
                            State newState = new State(j, nv, st.dist + 1);
                            newState.path.addAll(st.path);
                            newState.path.add(j);
                            queue.add(newState);
                            double fv = nv * table[j][i];
                            if (fv > 1) {
                                if (st.dist + 1 < dist || (st.dist + 1 == dist && fv > maxValue)) {
                                    found = true;
                                    dist = st.dist + 1;
                                    path = newState.path;
                                    path.add(i);
                                    maxValue = fv;
                                }
                            }
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (found) {
                    if (minDist > dist) {
                        minDist = dist;
                        minPath = path;
                    }
                }
            }
            if (minDist == Integer.MAX_VALUE) {
                out.append("no arbitrage sequence exists\n");
            } else {
                for (int i = 0; i < minPath.size(); i++) {
                    if (i != 0) {
                        out.append(' ');
                    }
                    out.append(String.format("%d", minPath.get(i) + 1));
                }
                out.append('\n');
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
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
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
