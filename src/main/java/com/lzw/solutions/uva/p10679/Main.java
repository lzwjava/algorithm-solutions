package com.lzw.solutions.uva.p10679;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int[] computePrefix(String p) {
        int m = p.length();
        int[] prefix = new int[m + 1];
        prefix[0] = 0;
        int k = 0;
        for (int i = 1; i < m; i++) {
            while (k > 0 && p.charAt(k) != p.charAt(i)) {
                k = prefix[k - 1];
            }
            if (p.charAt(k) == p.charAt(i)) {
                k++;
            }
            prefix[i] = k;
        }
        return prefix;
    }

    boolean kmp(String txt, String ptrn) {
        int n = txt.length();
        int m = ptrn.length();
        int[] prefix = computePrefix(ptrn);
        int q = 0;
        for (int i = 0; i < n; i++) {
            while (q > 0 && ptrn.charAt(q) != txt.charAt(i)) {
                q = prefix[q - 1];
            }
            if (ptrn.charAt(q) == txt.charAt(i)) {
                q++;
            }
            if (q == m) {
                return true;
            }
        }
        return false;
    }

    void solve() throws IOException {
        int k = Integer.parseInt(in.readLine());
        while (k > 0) {
            String s = in.readLine();
            int q = Integer.parseInt(in.readLine());
            while (q > 0) {
                String a = in.readLine();
                if (kmp(s, a)) {
                    out.append("y\n");
                } else {
                    out.append("n\n");
                }
                q--;
            }
            k--;
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
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("2.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();
    }
}
