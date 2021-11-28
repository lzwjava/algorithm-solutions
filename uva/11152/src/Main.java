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
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            double s = (a + b + c) * 1.0 / 2;
            double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
            double r = area * 2 * 1.0 / (a + b + c);
            double roses = Math.PI * r * r;
            double violets = area - roses;
            double cosA = (b * b + c * c - a * a) * 1.0 / (2 * b * c);
            double sinA = Math.sqrt(1 - cosA * cosA);
            double r2 = a * 1.0 / (sinA * 2);
            double sunflowers = Math.PI * r2 * r2 - area;
            out.append(String.format("%.4f %.4f %.4f\n", sunflowers, violets, roses));
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
