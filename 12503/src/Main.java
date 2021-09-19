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
   
    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            String[] instructions = new String[n];
            for (int i = 0; i < n; i++) {
                instructions[i] = in.readLine();
            }
            int p = 0;
            for (int i = 0; i < n; i++) {
                String it = instructions[i];
                if (it.equals("LEFT")) {
                    p--;
                } else if (it.equals("RIGHT")) {
                    p++;
                } else {
                    String[] strs = it.split("\\s+");
                    int index = Integer.parseInt(strs[strs.length - 1]);
                    String origin = instructions[index - 1];
                    if (origin.equals("LEFT")) {
                        p--;
                    } else if (origin.equals("RIGHT")) {
                        p++;
                    } else {
                        assert (false);
                    }
                    instructions[i] = origin;
                }
            }
            out.append(String.format("%d\n", p));
            t--;
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
