import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    int[] parseArray(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = parseArray(in.readLine());
            boolean boring = false;
            for (int i = 0; i < n && !boring; i++) {
                for (int j = i; j < n; j++) {
                    Map<Integer, Integer> map = new HashMap<>();
                    for (int k = i; k <= j; k++) {
                        Integer cnt = map.get(k);
                        if (cnt == null) {
                            cnt = 0;
                        }
                        cnt++;
                        map.put(k, cnt);
                    }
                    boolean ok = false;
                    for (int key : map.keySet()) {
                        int cnt = map.get(key);
                        if (cnt == 1) {
                            ok = true;
                            break;
                        }
                    }
                    if (!ok) {
                        boring = true;
                        break;
                    }
                }
            }
            if (boring) {
                out.append("boring\n");
            } else {
                out.append("non-boring\n");
            }
        }
    }
}