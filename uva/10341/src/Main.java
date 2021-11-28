import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;
    int p, q, r, s, t, u;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    double cal(double x) {
        return p * Math.pow(Math.E, -x) + q * Math.sin(x) + r * Math.cos(x) + s * Math.tan(x) + t * Math.pow(x, 2)+u;
    }
   
    void solve() throws IOException {
        while (true) {
            String str = in.readLine();
            if (str == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(str);
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            u = Integer.parseInt(st.nextToken());

            double up = 1;
            double low = 0;

            double mid = 0;
            // f(0) =1, f(1)=0
            while (up - low >= 1e-9) {
                mid = (up + low) / 2;
                double mv = cal(mid);
                if (mv < 0) {
                    up = mid;
                } else {
                    low = mid;
                }
            }
            mid = (up + low) / 2;
            if (Math.abs(cal(mid)) <=1e-4) {
                out.append(String.format("%.4f\n", mid));
            } else {
                out.append("No solution\n");                
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
