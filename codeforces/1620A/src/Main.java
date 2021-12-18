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

    void solve1() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            String s = in.readLine();
            int n = s.length();
            int[] a = new int[n];
            a[0] = 0;
            t--;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            String s = in.readLine();
            int n = s.length();
            int[] a = new int[n];
            boolean[] vis = new boolean[n];
            int p = 1;
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    vis[i] = true;
                    char c = s.charAt(i);
                    if (c == 'E') {
                        a[i] = p;
                        if (i + 1 < n) {
                            a[i + 1] = p;
                        }
                        int j = i;
                        while (j + 1 < n && s.charAt(j + 1) == 'E' && !vis[j + 1]) {
                            vis[j + 1] = true;
                            a[j + 1] = p;
                            if (j + 2 < n) {
                                a[j + 2] = p;
                            }
                            j++;
                        }

                        j = i;
                        while (true) {
                            int k = (j - 1 + n) % n;
                            if (s.charAt(k) == 'E' && !vis[k]) {
                                vis[k] = true;
                                a[k] = p;
                                j = k;
                            } else {
                                break;
                            }
                        }
                        p++;
                    }
                }
            }
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                int ni = (i + 1) % n;
                if (a[i] != 0 && a[ni] != 0) {
                    char ch = s.charAt(i);
                    if (ch == 'E' && a[i] != a[ni]) {
                        ok = false;
                        break;
                    } else if (ch == 'N' && a[i] == a[ni]) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}