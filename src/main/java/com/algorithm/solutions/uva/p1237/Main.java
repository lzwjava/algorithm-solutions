package com.algorithm.solutions.uva.p1237;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Maker {
        String name;
        int low;
        int high;

        Maker(String name, int low, int high) {
            this.name = name;
            this.low = low;
            this.high = high;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int d = Integer.parseInt(in.readLine());
            Maker[] makers = new Maker[d];
            for (int i = 0; i < d; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                String name = st.nextToken();
                int low = Integer.parseInt(st.nextToken());
                int high = Integer.parseInt(st.nextToken());
                makers[i] = new Maker(name, low, high);
            }
            int q = Integer.parseInt(in.readLine());

            while (q > 0) {
                int p = Integer.parseInt(in.readLine());
                int c = 0;
                int ci = 0;
                for (int i = 0; i < d; i++) {
                    Maker m = makers[i];
                    if (p >= m.low && p <= m.high) {
                        c++;
                        if (c == 1) {
                            ci = i;
                        }
                        if (c >= 2) {
                            break;
                        }
                    }
                }
                if (c == 1) {
                    out.append(makers[ci].name);
                } else {
                    out.append("UNDETERMINED");
                }
                out.append('\n');
                q--;
            }
            t--;
            if (t != 0) {
                out.append('\n');
            }
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
