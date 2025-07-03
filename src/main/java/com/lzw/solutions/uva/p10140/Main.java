package com.lzw.solutions.uva.p10140;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    ArrayList<Integer> list;

    boolean isPrime(int x) {
        if (x <= 1) {
            return false;
        }
        int sx = (int) Math.sqrt(x);
        for (Integer p : list) {
            if (p > sx) {
                break;
            }
            if (x % p == 0) {
                return false;
            }
        }
        return true;
    }

    class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        int dist() {
            return b - a;
        }
    }

    void calPrimes() {
        int maxn = 46340;
        boolean[] primes = new boolean[maxn];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i = 4; i < maxn; i += 2) {
            primes[i] = false;
        }
        int sm = (int) Math.sqrt(maxn);
        for (int i = 3; i <= sm; i += 2) {
            if (primes[i]) {
                for (int j = i * i; j < maxn; j += i) {
                    primes[j] = false;
                }
            }
        }
        list = new ArrayList<>();
        for (int i = 0; i < maxn; i++) {
            if (primes[i]) {
                list.add(i);
            }
        }
    }

    void solve() throws IOException {
        calPrimes();

        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int lastPrime = -1;
            Pair closest = null;
            int cnt = 0;
            int len = r - l + 1;
            while (len > 100000) {
                len /= 2;
            }
            for (int i = l; i <= r; i++) {
                if (isPrime(i)) {
                    if (lastPrime != -1) {
                        Pair p = new Pair(lastPrime, i);
                        if (closest == null || p.dist() < closest.dist()) {
                            closest = p;
                        } else {
                            cnt++;
                            if (cnt > len) {
                                break;
                            }
                        }
                    }
                    lastPrime = i;
                }
                if (i == 2147483647) {
                    break;
                }
            }
            int nextPrime = -1;
            Pair distant = null;
            cnt = 0;
            for (int i = r; i >= l; i--) {
                if (isPrime(i)) {
                    if (nextPrime != -1) {
                        Pair p = new Pair(i, nextPrime);
                        if (distant == null || p.dist() >= distant.dist()) {
                            distant = p;
                        } else {
                            cnt++;
                            if (cnt > len) {
                                break;
                            }
                        }
                    }
                    nextPrime = i;
                }
            }
            if (closest == null || distant == null) {
                out.append("There are no adjacent primes.\n");
            } else {
                out.append(String.format("%d,%d are closest, %d,%d are most distant.\n", closest.a, closest.b,
                    distant.a, distant.b));
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
            outStream = new PrintStream("2.out");
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
