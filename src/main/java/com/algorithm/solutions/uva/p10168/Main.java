package com.algorithm.solutions.uva.p10168;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);        
    }
   
    void solve() throws IOException {
        ArrayList<Integer> primes = new ArrayList<>();
        for(int i=2;i<10000000;i++){
            int pi = (int)Math.sqrt(i);
            boolean ok = true;
            for(int j=0;j<primes.size();j++){
                int pj = primes.get(j);
                if (pj>pi) {
                    break;
                }
                if (i%pj ==0){
                    ok = false;
                    break;
                }
            }
            if (ok){
                primes.add(i);
            }
        }
        boolean[] isPrime = new boolean[10000000];
        for(int i=0;i<primes.size();i++){
            isPrime[primes.get(i)] = true;
        }

        while(true){
            String str = in.readLine();
            if (str==null){
                break;
            }
            int n = Integer.parseInt(str);
            if (n<8) {
                out.append("Impossible.\n");
            } else {
                int a,b,s;
                if (n%2==0){
                    a= 2;
                    b=2;
                } else {
                    a= 2;
                    b = 3;
                }
                s = n - (a+b);
                boolean found = false;
                for(int i=2;i<s;i++) {
                    if (isPrime[i] && isPrime[s-i]){
                        out.append(String.format("%d %d %d %d\n", a, b, i, s-i));
                        found = true;
                        break;
                    }
                }
                if (!found){
                    out.append("Impossible.\n");
                }
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
