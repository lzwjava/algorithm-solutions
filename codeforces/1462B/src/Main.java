import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
            String s = in.readLine();
            String a = "2020";
            int p = 0;
            StringBuilder sb = new StringBuilder();
            List<String> list = new ArrayList<>();
            boolean match = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (p < a.length() && c == a.charAt(p)) {
                    String ap = a.substring(p);
                    String ss = s.substring(i + 1);
                    if (ss.contains(ap) && !match) {
                        sb.append(c);
                        match = false;
                    } else {
                        p++;
                        if (sb.length() > 0) {
                            String s2 = sb.toString();
                            list.add(s2);
                            sb.setLength(0);
                        }
                        match = true;
                    }
                } else {
                    match = false;
                    sb.append(c);
                }
            }
            if (sb.length() > 0) {
                String s2 = sb.toString();
                list.add(s2);
                sb.setLength(0);
            }
            if (p == a.length() && list.size() <= 1) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
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