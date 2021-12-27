import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            long a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());
            long c = Integer.parseInt(st.nextToken());
            long d = Integer.parseInt(st.nextToken());
            long sleep = 0;
            long time = 0;
            sleep += b;
            time += b;
            if (sleep < a) {
                if (c - d > 0) {
                    long p = a - sleep;
                    long rest = c - d;
                    long q;
                    if (p % rest == 0) {
                        q = p / rest;
                    } else {
                        q = p / rest + 1;
                    }
                    time += q * c;
                } else {
                    time = -1;
                }
            }
            out.append(String.format("%d\n", time));
        }
    }

}