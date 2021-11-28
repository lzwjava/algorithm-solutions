import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Pair {
        int x;
        int y;

        Pair() {

        }
        
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
   
    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            int k = Integer.parseInt(line);
            ArrayList<Pair> pairs = new ArrayList<>();
            for (int y = k+1;y<=2*k; y++) {
                double x = 1.0 / (1.0 / k - 1.0 / y);
                int rx = (int) Math.round(x);
                long left = (long)rx * y;
                long right = (long)k * y + (long)k * rx;
                if (rx >= y && left == right) {
                    Pair p = new Pair(rx, y);
                    pairs.add(p);               
                }
            }
            out.append(String.format("%d\n", pairs.size()));
            for (Pair p : pairs) {
                out.append(String.format("1/%d = 1/%d + 1/%d\n", k, p.x, p.y));
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
