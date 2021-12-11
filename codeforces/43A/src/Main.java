import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String s = in.readLine();
            Integer c = map.get(s);
            if (c == null) {
                c = 0;
            }
            c++;
            map.put(s, c);
        }
        Set<String> teams = map.keySet();
        String ans = "";
        if (teams.size() == 1) {
            ans = (String) teams.toArray()[0];
        } else {
            int max = 0;
            for (String t : teams) {
                Integer c = map.get(t);
                if (c > max) {
                    max = c;
                    ans = t;
                }
            }
        }
        out.append(String.format("%s\n", ans));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}