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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] array = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            int max = 0;
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(st.nextToken());
                if (array[i] > max) {
                    max = array[i];
                }
            }
            String binaryString = Integer.toBinaryString(max);
            int len = binaryString.length();
            int ans = (1 << len) - 1;
            System.out.println(ans);
        }
    }
}