package com.algorithm.solutions.uva.p10139;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean contains(HashMap<Integer, Integer> nmap, HashMap<Integer, Integer> mmap) {
        for (Integer key : mmap.keySet()) {
            Integer mc = mmap.get(key);
            Integer nc = nmap.get(key);
            if (nc == null) {
                nc = 0;
            }
            if (nc < mc) {
                return false;
            }
        }
        return true;
    }

    boolean fn(int n, int m) {
        if (m == 0) {
            return true;
        }
        if (n == 0) {
            if (m > 1) {
                return false;
            } else {
                return true;
            }
        }
        boolean is = isPrime(m);
        if (is) {
            if (n >= m) {
                return true;
            } else {
                return false;
            }
        } else {
            HashMap<Integer, Integer> mmap = resolve(m);
            HashMap<Integer, Integer> nmap = new HashMap<Integer, Integer>();
            for (Integer key : mmap.keySet()) {
                int count = 0;
                long v = 1;
                while (true) {
                    v *= key;
                    if (n >= v) {
                        count += n / v;
                    } else {
                        break;
                    }
                }
                nmap.put(key, count);
            }
            if (contains(nmap, mmap)) {
                return true;
            }
        }
        return false;
    }

    HashMap<Integer, Integer> resolve(int n) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sn = (int) Math.sqrt(n);
        for (int i = 0; i < primes.size(); i++) {
            int pi = primes.get(i);
            if (pi > sn) {
                break;
            }
            if (n % pi == 0) {
                Integer count = 0;
                while (n % pi == 0) {
                    n /= pi;
                    count++;
                }
                map.put(pi, count);
            }
            if (n == 1) {
                break;
            }
        }
        if (n != 1) {
            map.put(n, 1);
        }
        return map;
    }

    boolean isPrime(int m) {
        int sm = (int) Math.sqrt(m);
        for (int i = 0; i < primes.size(); i++) {
            int pi = primes.get(i);
            if (pi > sm) {
                break;
            }
            if (m % pi == 0) {
                return false;
            }
        }
        return true;
    }

    ArrayList<Integer> primes;

    void calPrimes() {
        primes = new ArrayList<Integer>();
        primes.add(2);
        for (int i = 3; i <= 46340; i += 2) {
            int si = (int) Math.sqrt(i);
            boolean ok = true;
            for (int j = 0; j < primes.size(); j++) {
                int pj = primes.get(j);
                if (pj > si) {
                    break;
                }
                if (i % pj == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                primes.add(i);
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
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            boolean ok = fn(n, m);
            if (ok) {
                out.append(String.format("%d divides %d!\n", m, n));
            } else {
                out.append(String.format("%d does not divide %d!\n", m, n));
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
