package com.lzw.solutions.codeforces.p1388A;

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

    List<Integer> primes;
    int maxn = 200000;

    void calPrimes() {
        int smax = (int) Math.sqrt(Integer.MAX_VALUE);
        boolean[] isp = new boolean[maxn];
        Arrays.fill(isp, true);
        isp[0] = isp[1] = false;
        for (int i = 4; i < maxn; i += 2) {
            isp[i] = false;
        }
        primes = new ArrayList<>();
        primes.add(2);
        for (int i = 3; i < maxn; i += 2) {
            if (isp[i]) {
                primes.add(i);
                if (i > smax) {
                    continue;
                }
                for (int j = i * i; j < maxn; j += i) {
                    isp[j] = false;
                }
            }
        }
    }

    List<Integer> nearlys;

    void calNearlyPrimes() {
        nearlys = new ArrayList<>();
        int m = primes.size();
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                int pi = primes.get(i);
                int pj = primes.get(j);
                long product = (long) pi * pj;
                if (product < maxn) {
                    nearlys.add((int) product);
                } else {
                    break;
                }
            }
        }
        Collections.sort(nearlys);
    }

    void solve() throws IOException {
        calPrimes();
        calNearlyPrimes();
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            int m = nearlys.size();
            boolean ok = false;
            for (int i = 0; i < m; i++) {
                int ni = nearlys.get(i);
                if (ni >= n || ok) {
                    break;
                }
                for (int j = i + 1; j < m; j++) {
                    int nj = nearlys.get(j);
                    if (nj >= n || ni + nj >= n || ok) {
                        break;
                    }
                    for (int k = j + 1; k < m; k++) {
                        int nk = nearlys.get(k);
                        if (nk >= n || ni + nj + nk >= n) {
                            break;
                        }
                        int nl = n - ni - nj - nk;
                        if (nl > 0 && nl != ni && nl != nj && nl != nk) {
                            ok = true;
                            out.append("YES\n");
                            out.append(String.format("%d %d %d %d\n", ni, nj, nk, nl));
                            break;
                        }
                    }
                }
            }
            if (!ok) {
                out.append("NO\n");
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}