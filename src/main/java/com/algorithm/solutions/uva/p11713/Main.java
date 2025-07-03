package com.algorithm.solutions.uva.p11713;

import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean isVowel(char ch) {
        String str = "AEIOUaeiou";
        return str.indexOf(ch) != -1;
    }

    boolean check(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int len = a.length();
        for (int i = 0; i < len; i++) {
            char ach = a.charAt(i);
            char bch = b.charAt(i);
            if (isVowel(ach) && !isVowel(bch) || !isVowel(ach) && isVowel(bch)) {
                return false;
            }
            if (isVowel(ach)) {
                continue;
            } else {
                if (ach != bch) {
                    return false;
                }
            }
        }
        return true;
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        while (n > 0) {
            String a = in.readLine();
            String b = in.readLine();
            boolean ok = check(a, b);
            if (ok) {
                out.append(String.format("Yes\n"));
            } else {
                out.append(String.format("No\n"));
            }
            n--;
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
