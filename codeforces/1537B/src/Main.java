import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int left = j - 1;
            int right = m - 1;
            int top = i - 1;
            int bottom = n - i;
            int x1, y1, x2, y2;
            if (top >= bottom) {
                int x = 1;
                if (left >= right) {
                    int y = 1;
                    x1 = x;
                    y1 = y;
                    x2 = x;
                    y2 = j;
                } else {
                    int y = m;
                    x1 = x;
                    y1 = y;
                    x2 = i;
                    y2 = y;
                }
            } else {
                int x = n;
                if (left >= right) {
                    int y = 1;
                    x1 = x;
                    y1 = y;
                    x2 = x;
                    y2 = j;
                } else {
                    int y = m;
                    x1 = x;
                    y1 = y;
                    x2 = i;
                    y2 = y;
                }
            }
            out.append(String.format("%d %d %d %d\n", x1, y1, x2, y2));
        }
    }

}