import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
        int n = Integer.parseInt(in.readLine());
        String a = in.readLine();
        String b = in.readLine();
        int s = 0;
        for (int i = 0; i < n; i++) {
            int ia = a.charAt(i) - '0';
            int ib = b.charAt(i) - '0';
            int c;
            if (ia >= ib) {
                c = Integer.min(ia - ib, 10 - ia + ib);
            } else {
                c = Integer.min(ib - ia, 10 - ib + ia);
            }
            s += c;
        }
        out.append(String.format("%d\n", s));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}