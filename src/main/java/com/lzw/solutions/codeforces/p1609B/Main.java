package com.lzw.solutions.codeforces.p1609B;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        String s = in.readLine();
        char[] chs = s.toCharArray();
        boolean[] abcs = new boolean[n];
        int cnt = 0;
        for (int j = 0; j <= n - 3; j++) {
            String sub = s.substring(j, j + 3);
            if (sub.equals("abc")) {
                abcs[j] = true;
                cnt++;
            }
        }
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(in.readLine());
            int index = Integer.parseInt(st.nextToken());
            char ch = st.nextToken().charAt(0);
            if (chs[index - 1] != ch) {
                int start = index - 1;
                start -= 2;
                if (start <= 0) {
                    start = 0;
                }
                int end = index - 1;
                chs[index - 1] = ch;
                for (int j = start; j <= end; j++) {
                    char[] nchs = Arrays.copyOfRange(chs, j, j + 3);
                    String ns = new String(nchs);
                    boolean nv = ns.equals("abc");
                    if (nv != abcs[j]) {
                        if (abcs[j]) {
                            cnt--;
                        } else {
                            cnt++;
                        }
                        abcs[j] = nv;
                    }
                }
            }
            out.append(String.format("%d\n", cnt));
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
