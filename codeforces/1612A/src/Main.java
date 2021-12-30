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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int dist(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    Point parsePoint(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        return new Point(x, y);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            Point a = parsePoint(in.readLine());
            Point b = parsePoint(in.readLine());
        }
    }
}