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
    
    long fact(int x) {
        if (x == 0) {
            return 1;
        } else {
            return fact(x - 1) * x;
        }
    }
   
    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            int n = Integer.parseInt(s);
            // long f = fact(n);
            // System.out.println(f);
            if(n<0 && n%2==0){
                out.append("Underflow!\n");
            } else if (n < 0 && n % 2 != 0) {
                out.append("Overflow!\n");                
            } else {
                long c = 1;
                long max = 6227020800L;
                for (int i = 1; i <= n; i++) {
                    c *= i;
                    if (c > max) {
                        break;
                    }              
                }
                if (c > max) {
                    out.append("Overflow!\n");
                } else if (c < 10000) {
                    out.append("Underflow!\n");
                } else {
                    out.append(String.format("%d\n", c));
                }
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
