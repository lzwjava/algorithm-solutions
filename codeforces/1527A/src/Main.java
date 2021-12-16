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
            int n = Integer.parseInt(in.readLine());
            String s = Integer.toBinaryString(n);
            int i;
            for (i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') {
                    break;
                }
            }
            int ans;
            if (i == s.length()) {
                ans = 0;
            } else {
                StringBuilder sb = new StringBuilder();
                for (int j = i; j < s.length(); j++) {
                    sb.append("1");
                }
                ans = Integer.parseInt(sb.toString(), 2);
            }
            out.append(String.format("%d\n", ans));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}