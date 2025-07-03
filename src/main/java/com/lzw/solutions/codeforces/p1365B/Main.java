package com.lzw.solutions.codeforces.p1365B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
//        m.test();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
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

    void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    boolean check(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    void print(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                out.append(' ');
            }
            out.append(String.format("%2d", a[i]));
        }
        out.append('\n');
    }

    int hash(int[] a) {
        return Arrays.hashCode(a);
    }

    boolean cal(int[] a, int[] b) {
        int n = a.length;
        Random random = new Random();
        int cnt = 0;
        Set<Integer> set = new HashSet<>();
        set.add(hash(a));
        print(a);
        print(b);
        while (cnt < 1000) {
            int i = random.nextInt(n);
            int j = random.nextInt(n);
            if (a[i] > a[j] && b[i] != b[j]) {
                swap(a, i, j);
                swap(b, i, j);
            }
            int h = hash(a);
            if (set.contains(h)) {
                continue;
            }
            set.add(h);
            print(a);
            print(b);
            if (check(a)) {
                break;
            }
            cnt++;
        }
        return check(a);
    }

    boolean cal1(int[] a, int[] b) {
        int n = a.length;
        if (check(a)) {
            return true;
        }
        int[] c = new int[2];
        for (int i = 0; i < n; i++) {
            c[b[i]]++;
        }
        return c[0] > 0 && c[1] > 0;
    }

    void test() {
        Random random = new Random();
        while (true) {
            int n = random.nextInt(10) + 1;
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt(10);
                b[i] = random.nextInt(2);
            }
            boolean ok = cal(a, b);
            boolean ok1 = cal1(a, b);
            assert ok == ok1;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = parseArray(in.readLine());
            int[] b = parseArray(in.readLine());
            boolean ok = cal1(a, b);
            if (ok) {
                out.append("Yes\n");
            } else {
                out.append("No\n");
            }
        }
    }
}