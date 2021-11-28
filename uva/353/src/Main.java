import java.io.*;
import java.util.HashSet;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean palindrome(String s) {
        int n = s.length();
        for (int i = 0; i <= n / 2; i++) {
            int j = n - 1 - i;
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            HashSet<String> set = new HashSet<String>();
            int n = line.length();
            for (int len = 1; len <= n; len++) {
                for (int i = 0; i <= n - len; i++) {
                    int j = i + len;
                    String s = line.substring(i, j);
                    set.add(s);
                }
            }
            int ans = 0;
            for (String s : set) {
                if (palindrome(s)) {
                    ans++;
                }
            }
            out.append(String.format("The string '%s' contains %d palindromes.\n", line, ans));
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
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
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
