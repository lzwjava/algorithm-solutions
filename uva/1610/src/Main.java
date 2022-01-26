import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

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
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            String[] names = new String[n];
            for (int i = 0; i < n; i++) {
                names[i] = in.readLine();
            }
            Arrays.sort(names);
            int half = n / 2;
            String a = names[half - 1];
            String b = names[half];
            StringBuilder ans = new StringBuilder();
            int an = a.length();
            int bn = b.length();
            int mn = Integer.min(an, bn);
            int i;
            for (i = 0; i < mn; i++) {
                char ai = a.charAt(i);
                char bi = b.charAt(i);
                if (ai != bi) {
                    break;
                } else {
                    ans.append(ai);
                }
            }
            if (i == mn) {

            } else {
                char ai = a.charAt(i);
                char nai = (char) (ai + 1);
                ans.append(nai);
            }
            out.append(String.format("%s\n", ans));
        }
    }
}