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

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        int x3, y3, x4, y4;
        boolean ok;
        if (x1 == x2 && y1 == y2) {
            ok = false;
        } else if (x1 == x2 || y1 == y2) {
            ok = true;
            if (x1 == x2) {
                if (y1 > y2) {
                    int t = y1;
                    y1 = y2;
                    y2 = t;
                }
                int d = y2 - y1;
                x3 = x1 + d;
                y3 = y1;
                x4 = x1 + d;
                y4 = y1 + d;
            } else {
                if (x1 > x2) {
                    int t = x1;
                    x1 = x2;
                    x2 = t;
                }
                int d = x2 - x1;
                
            }
        } else {
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}