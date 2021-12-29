import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
//        m.solve();
        m.test();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    String cal(String s) {
        int n = s.length();
        String ans = "";
        for (int i = 0; i < n; i++) {
            String s1 = s.substring(0, i + 1);
            if (!ans.equals("")) {
                if (ans.compareTo(s1) < 0) {
                    break;
                }
            }
            String s2 = new StringBuilder(s1).reverse().toString();
            String ns = String.format("%s%s", s1, s2);
            if (ans.equals("")) {
                ans = ns;
            } else {
                if (ns.compareTo(ans) < 0) {
                    ans = ns;
                }
            }
        }
        return ans;
    }

    class Seg {
        char c;
        int p;

        Seg(char c, int p) {
            this.c = c;
            this.p = p;
        }
    }

    String cal1(String s) {
        int n = s.length();
        char lastC = ' ';
        int lastP = 0;
        List<Seg> list = new ArrayList<>();
        List<Integer> ps = new ArrayList<>();
        String ns = String.format("%s.", s);
        char minC = ' ';
        for (int i = 0; i < ns.length(); i++) {
            char c = ns.charAt(i);
            if (c != lastC) {
                if (lastC != ' ') {
                    ps.add(i);
                    list.add(new Seg(lastC, lastP));
                }
                lastC = c;
                lastP = 1;
            } else {
                lastP++;
            }
            if (i < n) {
                if (minC == ' ') {
                    minC = c;
                } else {
                    if (Character.compare(c, minC) < 0) {
                        minC = c;
                    }
                }
            }
        }
        int ln = list.size();
        String ans = "";
        for (int i = 0; i < ln; i++) {
            Seg seg = list.get(i);
            if (i > 0 && seg.c != minC) {
                continue;
            }
            int pos;
            if (i == 0) {
                pos = 0;
            } else {
                Seg leftSeg = list.get(i - 1);
                if (Integer.compare(leftSeg.c, seg.c) < 0) {
                    pos = ps.get(i - 1);
                } else {
                    if (i == ln - 1) {
                        pos = n - 1;
                    } else {
                        pos = ps.get(i) - 1;
                    }
                }
            }
            String s1 = s.substring(0, pos + 1);
            if (!ans.equals("")) {
                if (ans.compareTo(s1) < 0) {
                    break;
                }
            }
            String s2 = new StringBuilder(s1).reverse().toString();
            String tns = String.format("%s%s", s1, s2);
            if (ans.equals("")) {
                ans = tns;
            } else {
                if (tns.compareTo(ans) < 0) {
                    ans = tns;
                }
            }
        }
        return ans;
    }

    String randomString(int len) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            char c = (char) (random.nextInt(5) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }

    void test() {
        while (true) {
            String s = randomString(5);
//            s = "eadcedcdca";
            String ans = cal(s);
            String ans1 = cal1(s);
            assert (ans.equals(ans1));
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            String ans = cal1(s);
            out.append(String.format("%s\n", ans));
        }
    }
}