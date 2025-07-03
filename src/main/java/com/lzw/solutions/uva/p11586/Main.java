package com.lzw.solutions.uva.p11586;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    String[] ps;
    int n;

    boolean connected(String a, String b) {
        char ac = a.charAt(1);
        char bc = b.charAt(0);
        return (ac == 'M' && bc == 'F') || (ac == 'F' && bc == 'M');
    }

    boolean loop;

    String reversed(String a) {
        return new StringBuilder(a).reverse().toString();
    }

    void dfs(String[] nps, boolean[] vis, int cur) {
        if (loop) {
            return;
        }
        if (cur == n) {
            if (cur - 1 != 0 && connected(nps[cur - 1], nps[0])) {
                loop = true;
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                for (int j = 0; j < 2; j++) {
                    String str = ps[i];
                    if (j == 1) {
                        str = reversed(str);
                    }
                    if (cur == 0 || connected(nps[cur - 1], str)) {
                        vis[i] = true;
                        nps[cur] = str;
                        dfs(nps, vis, cur + 1);
                        vis[i] = false;
                    }
                }
            }
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = st.countTokens();
            ps = new String[n];
            for (int i = 0; i < n; i++) {
                ps[i] = st.nextToken();
            }
            boolean[] vis = new boolean[n];
            loop = false;
            String[] nps = new String[n];
            dfs(nps, vis, 0);
            if (loop) {
                out.append("LOOP\n");
            } else {
                out.append("NO LOOP\n");
            }
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
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
