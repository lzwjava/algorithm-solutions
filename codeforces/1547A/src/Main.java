import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            in.readLine();
            StringTokenizer st = new StringTokenizer(in.readLine());
            int xa = Integer.parseInt(st.nextToken()) - 1;
            int ya = Integer.parseInt(st.nextToken()) - 1;
            st = new StringTokenizer(in.readLine());
            int xb = Integer.parseInt(st.nextToken()) - 1;
            int yb = Integer.parseInt(st.nextToken()) - 1;
            st = new StringTokenizer(in.readLine());
            int xf = Integer.parseInt(st.nextToken()) - 1;
            int yf = Integer.parseInt(st.nextToken()) - 1;
            
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}