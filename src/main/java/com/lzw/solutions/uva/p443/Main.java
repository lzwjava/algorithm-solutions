package com.lzw.solutions.uva.p443;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        ArrayList<Long> nums = new ArrayList<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.add(1L);
        int[] primes = new int[] {2, 3, 5, 7};
        HashSet<Long> set = new HashSet<>();
        set.add(1L);
        while (!queue.isEmpty()) {
            long num = queue.poll();
            nums.add(num);
            if (nums.size() == 5842) {
                break;
            }
            for (int i = 0; i < primes.length; i++) {
                int p = primes[i];
                long newNum = num * p;
                if (!set.contains(newNum)) {
                    set.add(newNum);
                    queue.add(newNum);
                }
            }
        }
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            long num = nums.get(n - 1);
            long d = n % 100;
            long last = n % 10;
            String suffix;
            if (d == 11 || d == 12 || d == 13) {
                suffix = "th";
            } else if (last == 1) {
                suffix = "st";
            } else if (last == 2) {
                suffix = "nd";
            } else if (last == 3) {
                suffix = "rd";
            } else {
                suffix = "th";
            }
            out.append(String.format("The %d%s humble number is %d.\n", n, suffix, num));
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
