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
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int p = Integer.parseInt(in.readLine());
            boolean found = false;
            for (int a = 2; a < p && !found; a++) {
                for (int b = a + 1; b < p; b++) {
                    if (p % a == p % b) {
                        found = true;
                        out.append(String.format("%d %d\n", a, b));
                        break;
                    }
                }
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}