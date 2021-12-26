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
            String b = in.readLine();
            int[] bs = new int[n];
            for (int i = 0; i < n; i++) {
                bs[i] = b.charAt(i) - '0';
            }
            int[] as = new int[n];
            for (int i = 0; i < n; i++) {
                int v = bs[i];
                int lv;
                if (i == 0) {
                    lv = -1;
                } else {
                    lv = as[i - 1];
                }
                List<Integer> list = new ArrayList<>();
                for (int j = 0; j < 2; j++) {
                    if (v + j == lv) {
                        continue;
                    } else {
                        list.add(j);
                    }
                }
                int fv = list.get(list.size() - 1);
                as[i] = fv;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(String.format("%d", as[i]));
            }
            out.append(String.format("%s\n", sb));
        }
    }

}