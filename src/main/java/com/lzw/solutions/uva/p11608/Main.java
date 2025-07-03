package com.lzw.solutions.uva.p11608;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            int s = Integer.parseInt(in.readLine());
            if (s < 0) {
                break;
            }
            int[] cs = new int[12];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < 12; i++) {
                cs[i] = Integer.parseInt(st.nextToken());
            }
            int[] rs = new int[12];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < 12; i++) {
                rs[i] = Integer.parseInt(st.nextToken());
            }
            out.append(String.format("Case %d:\n", caseNum));
            for (int i = 0; i < 12; i++) {
//                if (i == 7) {
//                    System.out.println();
//                }
                if (s >= rs[i]) {
                    s -= rs[i];
                    out.append("No problem! :D\n");
                } else {
                    out.append("No problem. :(\n");
                }
                s += cs[i];
            }
            caseNum++;
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
