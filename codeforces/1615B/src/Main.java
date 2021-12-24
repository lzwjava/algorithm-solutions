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
        while (true) {
            int l = (int) (Math.random() * 200000 + 1);
            int r = (int) (Math.random() * 200000 + 1);
            if (l > r) {
                continue;
            }
//            l = 15505;
//            r = 148149;
            int a1 = cal1(l, r);
            int a2 = cal2(l, r);

            assert (a1 == a2);
        }
    }

    int cal1(int l, int r) {
        int[] cnts = new int[20];
        int rl = Integer.toBinaryString(l).length();
        int rm = Integer.toBinaryString(r).length();
        int left;
        int right;
        if (r / l < 10) {
            left = rm - 5;
            right = rm;
        } else {
            int mid = (rl + rm) / 2;
            left = mid - 6;
            right = mid;
        }
        for (int i = l; i <= r; i++) {
            String s = Integer.toBinaryString(i);
            int len = s.length();
            int li = Math.max(0, left);
            int ri = Math.min(len, right);
            for (int j = li; j < ri; j++) {
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