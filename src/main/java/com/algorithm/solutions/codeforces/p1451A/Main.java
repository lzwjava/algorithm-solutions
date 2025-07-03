package com.algorithm.solutions.codeforces.p1451A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    List<Integer> list;

    void calPrimes() {
        int maxn = 31625;
        boolean[] prime = new boolean[maxn];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i = 4; i < maxn; i += 2) {
            prime[i] = false;
        }
        list = new ArrayList<>();
        list.add(2);
        for (int i = 3; i < maxn; i += 2) {
            if (prime[i]) {
                list.add(i);
                if (i * i > 0) {
                    for (int j = i * i; j < maxn; j += i) {
                        prime[j] = false;
                    }
                }
            }
        }
    }

    class Factor {
        int p, c;

        Factor(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    List<Factor> calFactors(int x) {
        List<Factor> fs = new ArrayList<>();
        int sx = (int) Math.sqrt(x);
        for (int p : list) {
            if (p > sx) {
                break;
            }
            if (x % p == 0) {
                int c = 0;
                while (x % p == 0) {
                    x /= p;
                    c++;
                }
                fs.add(new Factor(p, c));
            }
            if (x == 1) {
                break;
            }
        }
        if (x != 1) {
            fs.add(new Factor(x, 1));
        }
        return fs;
    }

    int dp(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        List<Factor> fs = calFactors(n);
        int fn = fs.size();
        if (fn == 1 && fs.get(0).p == n) {
            return dp(n - 1) + 1;
        } else {
            List<Integer> ps = new ArrayList<>();
            permutation(fs, ps, 0, fs.size(), 1);
            Collections.sort(ps);

            int maxp = ps.get(ps.size() - 2);
            int v1 = dp(n / maxp) + 1;
            if (n / maxp % 2 == 0) {
                return v1;
            }
            int v2 = dp(n - 1) + 1;
            return Integer.min(v1, v2);
        }
    }
    
    void permutation(List<Factor> fs, List<Integer> ps, int cur, int n, int product) {
        if (cur == n) {
            if (product != 1) {
                ps.add(product);
            }
            return;
        }
        Factor f = fs.get(cur);
        for (int i = 0; i <= f.c; i++) {
            permutation(fs, ps, cur + 1, n, product * (int) Math.pow(f.p, i));
        }
    }


    void solve() throws IOException {
        calPrimes();
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int ans = dp(n);
            out.append(String.format("%d\n", ans));
        }
    }

}