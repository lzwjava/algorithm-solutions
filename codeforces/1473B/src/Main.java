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

    class Factor {
        String s;
        int c;

        Factor(String s, int c) {
            this.s = s;
            this.c = c;
        }
    }

    void solve() throws IOException {
        int q = Integer.parseInt(in.readLine());
        while (q > 0) {
            q--;
            String s = in.readLine();
            String t = in.readLine();
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}