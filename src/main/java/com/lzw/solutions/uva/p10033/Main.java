package com.lzw.solutions.uva.p10033;

import java.io.*;
import java.util.ArrayList;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        in.readLine();
        while (t > 0) {
            ArrayList<String> list = new ArrayList<String>();
            while (true) {
                String s = in.readLine();
                if (s == null || s.isEmpty()) {
                    break;
                }
                list.add(s);
            }
            int[] reg = new int[10];
            for (int i = 0; i < 10; i++) {
                reg[i] = 0;
            }
            int[] mem = new int[1000];
            for (int i = 0; i < list.size(); i++) {
                mem[i] = Integer.parseInt(list.get(i));
            }
            int p = 0;
            int ans = 0;
            boolean halt = false;
            while (!halt) {
                int a = mem[p] / 100;
                int b = mem[p] % 100 / 10;
                int c = mem[p] % 10;
                switch (a) {
                    case 0:
                        if (reg[c] == 0) {
                        } else {
                            p = reg[b];
                            ans++;
                            continue;
                        }
                        break;
                    case 1:
                        if (b == 0 && c == 0) {
                            halt = true;
                            ans++;
                            continue;
                        }
                        break;
                    case 2:
                        reg[b] = c;
                        break;
                    case 3:
                        reg[b] = (reg[b] + c) % 1000;
                        break;
                    case 4:
                        reg[b] = (reg[b] * c) % 1000;
                        break;
                    case 5:
                        reg[b] = reg[c];
                        break;
                    case 6:
                        reg[b] = (reg[b] + reg[c]) % 1000;
                        break;
                    case 7:
                        reg[b] = (reg[b] * reg[c]) % 1000;
                        break;
                    case 8:
                        reg[b] = mem[reg[c]];
                        break;
                    case 9:
                        mem[reg[c]] = reg[b];
                        break;
                }
                ans++;
                p++;
            }
            out.append(String.format("%d\n", ans));
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
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();
    }
}
