import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int[] cnts = new int[20];
            for (int i = l; i <= r; i++) {
                String s = Integer.toBinaryString(i);
                int len = s.length();
                for (int j = 0; j < len; j++) {
                    char c = s.charAt(len - 1 - j);
                    if (c == '1') {
                        cnts[j]++;
                    }
                }
            }
            Arrays.sort(cnts);
            int last = cnts[cnts.length - 1];
            int n = r - l + 1;
            out.append(String.format("%d\n", n - last));
        }
    }

}