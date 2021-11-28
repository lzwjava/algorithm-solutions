import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    Map<Integer, Result> map;

    Result dp(int m, int n, int t) {
        Result ans = map.get(t);
        if (ans != null) {
            return ans;
        }
        if (t < m && t < n) {
            ans = new Result(0, t);
        } else {
            Result r1 = null, r2 = null;
            if (t >= m) {
                r1 = dp(m, n, t - m).clone();
                r1.burger++;
            }
            if (t >= n) {
                r2 = dp(m, n, t - n).clone();
                r2.burger++;
            }
            if (r1 == null) {
                ans = r2;
            } else if (r2 == null) {
                return r1;
            } else {
                if (r1.beer == r2.beer) {
                    if (r1.burger >= r2.burger) {
                        ans = r1;
                    } else {
                        ans = r2;
                    }
                } else {
                    if (r1.beer < r2.beer) {
                        ans = r1;
                    } else {
                        ans = r2;
                    }
                }
            }
        }
        map.put(t, ans);
        return ans;
    }

    class Result implements Cloneable {
        int burger;
        int beer;

        Result(int burger, int beer) {
            this.burger = burger;
            this.beer = beer;
        }

        @Override
        protected Result clone() {
            return new Result(this.burger, this.beer);
        }
    }

    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            map = new HashMap<Integer, Result>();
            Result ans = dp(m, n, t);
            if (ans.beer != 0) {
                out.append(String.format("%d %d\n", ans.burger, ans.beer));
            } else {
                out.append(String.format("%d\n", ans.burger));
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
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("2.out");
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
