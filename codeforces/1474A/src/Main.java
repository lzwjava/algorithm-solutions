import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            String b = in.readLine();
            StringBuilder a = new StringBuilder();
            for (int i = 0; i < n; i++) {
                char c = b.charAt(i);
                char lc;
                if (i == 0) {
                    lc = ' ';
                } else {
                    lc = a.charAt(i - 1);
                }
                if (c == '1') {
                    
                } else {

                }
            }
        }
    }

}