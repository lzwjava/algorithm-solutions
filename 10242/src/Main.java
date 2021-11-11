import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            double x1, y1, x2, y2, x3, y3, x4, y4;
            x1 = Double.parseDouble(st.nextToken());
            y1 = Double.parseDouble(st.nextToken());
            x2 = Double.parseDouble(st.nextToken());
            y2 = Double.parseDouble(st.nextToken());
            x3 = Double.parseDouble(st.nextToken());
            y3 = Double.parseDouble(st.nextToken());
            x4 = Double.parseDouble(st.nextToken());
            y4 = Double.parseDouble(st.nextToken());
            double k1 = 0, b1 = 0, k2 = 0, b2 = 0;
            if (Double.compare(x1, x2) != 0) {
                k1 = (y2 - y1) / (x2 - x1);
                b1 = y1 - k1 * x1;
            }
            if (Double.compare(x3, x4) != 0) {
                k2 = (y4 - y3) / (x4 - x3);
                b2 = y3 - k2 * x3;
            }
            if (Double.compare(x1, x2) != 0 && Double.compare(x3, x4) != 0) {
                // k1, x4,y4
                double ub = y4 - k1 * x4;
                // k2, x1, y1
                double vb = y1 - k2 * x1;

                // k1*x+ub = k2*x+vb
                double x = (ub - vb) / (k2 - k1);
                double y = k1 * x + ub;
                out.append(String.format("%.3f %.3f\n", x, y));
            } else if (Double.compare(x1, x2) == 0 && Double.compare(x3, x4) != 0) {
                // x = x1
                double x = x4;
                // y = k2 * x + vb
                // x1, y1
                // y1 = k2 * x1 + vb
                double vb = y1 - k2 * x1;
                double y = k2 * x + vb;
                out.append(String.format("%.3f %.3f\n", x, y));
            } else if (Double.compare(x1, x2) != 0 && Double.compare(x3, x4) == 0) {
                // x = x1
                double x = x1;
                double ub = y4 - k1 * x4;
                double y = k1 * x + ub;
                out.append(String.format("%.3f %.3f\n", x, y));
            }
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
