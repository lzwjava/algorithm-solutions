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

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve1();
        m.close();
    }

    Random random = new Random();

    int cal1(int n, String a, String b) {
        int cnt = 0;
        while (!a.equals(b) && cnt < 1000) {
            cnt++;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (a.charAt(i) == '1') {
                    list.add(i);
                }
            }
            int j = random.nextInt(list.size());
            int pj = list.get(j);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                char c = a.charAt(i);
                if (i == pj) {
                    sb.append(c);
                } else {
                    if (c == '1') {
                        sb.append('0');
                    } else {
                        sb.append('1');
                    }
                }
            }
            a = sb.toString();
        }
        return cnt;
    }

    void solve1() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            String a = in.readLine();
            String b = in.readLine();
            int c = Integer.MAX_VALUE;
            for (int i = 0; i < 100; i++) {
                c = Math.min(c, cal1(n, a, b));
            }
            if (c == 1000) {
                out.append("-1\n");
            } else {
                out.append(String.format("%d\n", c));
            }
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            String a = in.readLine();
            String b = in.readLine();
            int ans;
            if (a.equals(b)) {
                ans = 0;
            } else if (a.indexOf('1') == -1 && b.indexOf('1') != -1) {
                ans = -1;
            } else if (a.indexOf('1') != 1 && b.indexOf('1') == -1) {
                ans = -1;
            } else {
                int ac1 = 0;
                for (int i = 0; i < n; i++) {
                    if (a.charAt(i) == '1') {
                        ac1++;
                    }
                }
                int ac0 = n - ac1;
                int minc = Integer.min(ac0, ac1 - 1) + 1;
                int maxc = Integer.max(ac0, ac1 - 1) + 1;
                int bc1 = 0;
                for (int i = 0; i < n; i++) {
                    if (b.charAt(i) == '1') {
                        bc1++;
                    }
                }
                if (bc1 >= minc && bc1 <= maxc) {
                    int diff = 0;
                    int same = 0;
                    StringBuilder sb = new StringBuilder();
                    boolean sameZero = true;
                    List<Character> diffList = new ArrayList<>();
                    List<Character> sameList = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        if (a.charAt(i) == b.charAt(i)) {
                            sb.append('-');
                            same++;
                            if (a.charAt(i) != '0') {
                                sameZero = false;
                            }
                            sameList.add(a.charAt(i));
                        } else {
                            sb.append('x');
                            diffList.add(a.charAt(i));
                            diff++;
                        }
                    }
                    if (same <= diff) {
                        int s0 = 0;
                        int s1 = 0;
                        for (char c : sameList) {
                            if (c == '1') {
                                s1++;
                            } else {
                                s0++;
                            }
                        }
                        if (Math.abs(s0 - s1) <= 1) {
                            if (sameZero) {
                                ans = same + 1;
                            } else {
                                ans = same;
                            }
                        } else {
                            ans = -1;
                        }
                    } else {
                        int s0 = 0;
                        int s1 = 0;
                        for (char c : diffList) {
                            if (c == '1') {
                                s1++;
                            } else {
                                s0++;
                            }
                        }
                        if (Math.abs(s0 - s1) <= 1) {
                            ans = diff;
                        } else {
                            ans = -1;
                        }
                    }
//                    int min = Math.min(same, diff);
//                    if (min == same && sameZero) {
//                        ans = min + 1;
//                    } else {
//                        ans = min;
//                    }
//                    out.append(String.format("%s\n", a));
//                    out.append(String.format("%s\n", b));
//                    out.append(sb.toString());
//                    out.append('\n');
                } else {
                    ans = -1;
                }
            }
            out.append(String.format("%d\n", ans));
        }
    }

}