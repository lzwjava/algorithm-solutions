import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    int d(int n) {
        int sum = n;
        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
   
    void solve() throws IOException {
        int maxn = 1000000;
        boolean[] generated = new boolean[maxn+1];
        for (int i = 1; i <= maxn; i++) {
            int x = i;
            while (x <= maxn) {
                int g = d(x);
                if (g <= maxn) {
                    if (generated[g]) {
                        break;
                    }
                    generated[g] = true;
                }
                x = g;
            }
        }
        for (int i = 1; i <= maxn; i++) {
            if (!generated[i]) {
                out.append(String.format("%d\n", i));
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
