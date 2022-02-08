import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
//        m.solve();
        m.test();
        m.close();
    }

    String cal(int n, String[] names) {
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
        if (i < mn) {
            // NG, OA
            char ai = a.charAt(i);
            char bi = b.charAt(i);
            char nai = (char) (ai + 1);
            if (nai < bi) {
                ans.append(nai);
            } else {
                ans.append(ai);
                if (i + 1 < an) {
                    char anext = a.charAt(i + 1);
                    ans.append(anext);
                } else {
                    ans.append('A');
                }
            }
        }
        return ans.toString();
    }

    String randomeString(Random random, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = (char) (random.nextInt(26) + 'A');
            sb.append(ch);
        }
        return sb.toString();
    }

    void test() {
        Random random = new Random();
        while (true) {
            int n = (random.nextInt(5) + 1) * 2;
            String[] ns = new String[n];
            Set<String> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                ns[i] = randomeString(random, random.nextInt(10) + 1);
                set.add(ns[i]);
            }
            if (set.size() != n) {
                continue;
            }
            String ans = cal(n, ns);
            int c = 0;
            for (int i = 0; i < n; i++) {
                if (ns[i].compareTo(ans) <= 0) {
                    c++;
                }
            }
            assert c * 2 == n;
        }
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
            String ans = cal(n, names);
            out.append(String.format("%s\n", ans));
        }
    }
}