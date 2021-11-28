import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer st = new StringTokenizer(in.readLine());
            ArrayList<Long> ns = new ArrayList<Long>();
            for (int i = 0; i < n; i++) {
                long v = Long.parseLong(st.nextToken());
                ns.add(v);
            }
            Collections.sort(ns);
            int max = -1000;
            int cnt = 0;
            while (true) {
                int sum = 0;
                for (int k = 0; k < ns.size(); k++) {
                    sum += ns.get(k);
                }
                if (sum > max) {
                    max = sum;
                } else {
                    cnt++;
                    if (cnt > 100) {
                        break;
                    }
                }

                int len = ns.size();
                int even = 0;
                int eveni = -1;
                for (int i = 0; i < len; i++) {
                    if (ns.get(i) % 2 == 0) {
                        even++;
                        eveni = i;
                    }
                }
                int i = -1;
                if (even == 1) {
                    i = eveni;
                } else {
                    for (i = 0; i < len; i++) {
                        if (ns.get(i) % 2 == 0) {
                            break;
                        }
                    }
                }
                int j = -1;
                for (j = len - 1; j >= 0; j--) {
                    if (j != i) {
                        break;
                    }
                }
                if (i == -1 || j == -1) {
                    break;
                }
                long vi = ns.get(i);
                long vj = ns.get(j);
                long ai = vi / 2;
                long aj = vj * 2;
                ns.remove(vi);
                ns.remove(vj);
                ns.add(ai);
                ns.add(aj);
                Collections.sort(ns);
            }
            out.append(String.format("%d\n", max));
            t--;
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
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
