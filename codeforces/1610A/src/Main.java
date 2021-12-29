import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    Scanner in;
    PrintWriter out;

    Main() {
        in = new Scanner(System.in);
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    void solve() {
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int n = in.nextInt();
            int m = in.nextInt();
            int ans;
            if (n == 1 || m == 1) {
                ans = 1;
            } else {
                ans = 2;
            }
            out.append(String.format("%d\n", ans));
        }
    }
}