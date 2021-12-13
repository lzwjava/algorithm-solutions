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

    class Range implements Comparable<Range> {
        int a, b, id;

        Range(int a, int b, int id) {
            this.a = a;
            this.b = b;
            this.id = id;
        }

        int len() {
            return b - a + 1;
        }

        @Override
        public int compareTo(Range o) {
            return Integer.compare(len(), o.len());
        }
    }

    class Item implements Comparable<Item> {
        int x;
        int id;

        Item(int x, int id) {
            this.x = x;
            this.id = id;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(id, o.id);
        }
    }

    void print(Range[] rs, int x, int y, int z) {
        Item[] is = new Item[3];
        is[0] = new Item(x, rs[0].id);
        is[1] = new Item(y, rs[1].id);
        is[2] = new Item(z, rs[2].id);
        Arrays.sort(is);
        out.append(String.format("%d %d %d\n", is[0].x, is[1].x, is[2].x));
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Range[] rs = new Range[3];
            rs[0] = new Range(a, b, 0);
            rs[1] = new Range(b, c, 1);
            rs[2] = new Range(c, d, 2);
            Arrays.sort(rs);
            boolean found = false;
            for (int x = rs[0].a; x <= rs[0].b && !found; x++) {
                for (int y = rs[1].a; y <= rs[1].b && !found; y++) {
                    int u = Math.abs(x - y);
                    int v = x + y;
                    int m1 = Math.max(u + 1, rs[2].a);
                    int m2 = Math.min(v - 1, rs[2].b);
                    for (int z = m1; z <= m2; z++) {
                        found = true;
                        print(rs, x, y, z);
                        break;
                    }
                }
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