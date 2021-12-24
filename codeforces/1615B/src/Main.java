import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
//        m.test();
        m.close();
    }

    void test() {
        setup();
        while (true) {
            int l = (int) (Math.random() * 200000 + 1);
            int r = (int) (Math.random() * 200000 + 1);
            if (l > r) {
                continue;
            }
            int a1 = cal1(l, r);
            int a2 = cal2(l, r);
            assert (a1 == a2);
        }
    }

    int maxn = 200001;

    int[][] cnts = new int[maxn][20];

    int cal1(int l, int r) {
        int[] cnt = new int[20];
        for (int i = 0; i < 20; i++) {
            cnt[i] = cnts[r][i] - cnts[l - 1][i];
        }
        Arrays.sort(cnt);
        int last = cnt[cnt.length - 1];
        int n = r - l + 1;
        int ans = n - last;
        return ans;
    }

    void setup() {
        int[] cnt = new int[20];
        for (int i = 1; i < maxn; i++) {
            String s = Integer.toBinaryString(i);
            int len = s.length();
            for (int j = 0; j < len; j++) {
                char c = s.charAt(len - 1 - j);
                if (c == '1') {
                    cnt[j]++;
                }
            }
            cnts[i] = cnt.clone();
        }
    }

    int cal2(int l, int r) {
        int[] cnts = new int[20];
        for (int i = l; i <= r; i++) {
            String s = Integer.toBinaryString(i);
            int len = s.length();
            for (int j = len - 1; j >= 0; j--) {
                char c = s.charAt(len - 1 - j);
                if (c == '1') {
                    cnts[j]++;
                }
            }
        }
        Arrays.sort(cnts);
        int last = cnts[cnts.length - 1];
        int n = r - l + 1;
        int ans = n - last;
        return ans;
    }

    void solve() throws IOException {
        setup();
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int ans = cal1(l, r);
            out.append(String.format("%d\n", ans));
        }
    }

}