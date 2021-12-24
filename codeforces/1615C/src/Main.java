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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            String a = in.readLine();
            String b = in.readLine();
            int ans;
            if (a.equals(b)) {
                ans = 0;
            } else if (a.indexOf('1') == -1 && b.indexOf('1') != -1) {
                ans = -1;
            } else {
                int diff = 0;
                int same = 0;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (a.charAt(i) == b.charAt(i)) {
                        sb.append('-');
                        same++;
                    } else {
                        sb.append('x');
                        diff++;
                    }
                }
                int min = Math.min(same, diff);
//                out.append(String.format("%s\n", a));
//                out.append(String.format("%s\n", b));
//                out.append(sb.toString());
                ans = min;
            }
            out.append(String.format("%d\n", ans));
        }
    }

}