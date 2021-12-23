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

    void solve() throws IOException {
        int q = Integer.parseInt(in.readLine());
        while (q > 0) {
            q--;
            int a, b, n, S;
            StringTokenizer st = new StringTokenizer(in.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}