import java.io.*;
import java.util.Arrays;
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
        int[] wheels;
        int dist;

        State(int[] wheels, int dist) {
            this.wheels = wheels;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Arrays.equals(wheels, state.wheels);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(wheels);
        }
    }

    private int getKey(int[] wheels) {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            sum += wheels[4 - 1 - i] * (int) Math.pow(10, i);
        }
        return sum;
    }

    private boolean isForbidden(int[][] forbids, int[] wheels) {
        for (int[] forbid : forbids) {
            if (Arrays.equals(wheels, forbid)) {
                return true;
            }
        }
        return false;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int[] initials = new int[4];
            int[] targets = new int[4];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < 4; i++) {
                initials[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < 4; i++) {
                targets[i] = Integer.parseInt(st.nextToken());
            }
            int fn = Integer.parseInt(in.readLine());
            int[][] forbids = new int[fn][4];
            for (int i = 0; i < fn; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < 4; j++) {
                    forbids[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            State targetState = new State(targets, 0);

            boolean[] vis = new boolean[10000];

            ArrayBlockingQueue<State> queue = new ArrayBlockingQueue<State>(10000);
            queue.add(new State(initials, 0));
            vis[getKey(initials)] = true;
            boolean found = false;
            int ans = 0;
            while (!queue.isEmpty()) {
                State state = queue.poll();
                for (int i = 0; i < 4; i++) {
                    for (int j = -9; j <= 9; j++) {
                        if (j == 0) {
                            continue;
                        }
                        int[] wheels = state.wheels.clone();
                        wheels[i] = (wheels[i] + j + 10) % 10;
                        int key = getKey(wheels);
                        if (!vis[key] && !isForbidden(forbids, wheels)) {
                            vis[key] = true;
                            State newState = new State(wheels, state.dist + 1);
                            if (newState.equals(targetState)) {
                                found = true;
                                ans = newState.dist;
                                break;
                            }
                            queue.add(newState);
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }

            out.append(String.format("%d\n", ans));

            t--;
            if (t != 0) {
                in.readLine();
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
