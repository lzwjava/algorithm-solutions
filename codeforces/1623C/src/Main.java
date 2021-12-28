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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    int[] parseArray(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    int min(int[] a) {
        int mh = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < mh) {
                mh = a[i];
            }
        }
        return mh;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] h = parseArray(in.readLine());
            int mh = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (h[i] < mh) {
                    mh = h[i];
                }
            }
            int curMin = 0;
            for (int i = 2; i < n; i++) {
                // a[i-2], a[i-1], a[i]
                // a[i-2]+2d, a[i-1]+d, a[i]-3d
                // a[i-2]+5d<=a[i],  a[i-1]+4d<=a[i]
                // d<= (a[i]-a[i-2])/5, d<= (a[i]-a[i-1])/4
                int md;
                if (i == n - 1) {
                    md = (h[i] - curMin) / 3;
                } else {
                    md = h[i] / 3;
                }
                if (md > 0) {
                    h[i - 2] += 2 * md;
                    h[i - 1] += md;
                    h[i] -= 3 * md;
                    int min = min(h);
                    if (min > mh) {
                        mh = min;
                    }
                }

                if (i == 2) {
                    curMin = h[0];
                } else {
                    if (h[i - 2] < curMin) {
                        curMin = h[i - 2];
                    }
                }
            }
            out.append(String.format("%d\n", mh));
        }
    }

}