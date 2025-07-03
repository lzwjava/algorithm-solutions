package com.lzw.solutions.uva.p12333;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    int ch[][] = new int[5000005][10];
    int val[] = new int[5000005];
    int cnt;
    
    String add(String a, String b) {
        if (a.length() > b.length()) {
            String tmp = a;
            a = b;
            b = tmp;
        }
        int alen = a.length();
        int blen = b.length();
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < blen; i++) {
            int sum = carry;
            if (alen - 1 - i >= 0) {
                char cha = a.charAt(alen - 1 - i);
                sum += (cha - '0');
            }
            char chb = b.charAt(blen - 1 - i);
            sum += (chb - '0');
            if (sum >= 10) {
                carry = 1;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            sb.append((char) ('0' + sum));
        }
        if (carry > 0) {
            sb.append("1");
        }
        String s = sb.reverse().toString();
        return s;
    }

    void insert(String str, int index) {
        int u = 0;
        for (int i = 0; i < str.length(); i++) {
            int k = str.charAt(i) - '0';
            if (ch[u][k] == 0) {
                val[cnt] = index;
                ch[u][k] = cnt;
                cnt++;
            }
            u = ch[u][k];
        }
    }

    int query(String str) {
        int u = 0;
        for (int i = 0; i < str.length(); i++) {
            int k = str.charAt(i) - '0';
            if (ch[u][k] == 0) {
                return -1;
            }
            u = ch[u][k];
        }
        return val[u];
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        cnt = 1;

        int t = sc.nextInt();
        String fb2 = "1";
        String fb1 = "1";
        insert(fb2, 0);
        insert(fb1, 1);
        int n = 2;
      
        for (; n < 100000; n++) {
            if (fb1.length() > 50) {
                fb1 = fb1.substring(0, fb1.length() - 1);
                fb2 = fb2.substring(0, fb2.length() - 1);
            }
            String fb = add(fb1, fb2);
            insert(fb, n);
        
            fb2 = fb1;
            fb1 = fb;            
        }
        int caseNum = 1;

        while (t > 0) {
            String str = sc.next();

            System.out.print(String.format("Case #%d: ", caseNum));
            int i = query(str);         
            if (i != -1) {
                System.out.println(i);
            } else {
                System.out.println("-1");
            }
            t--;
            caseNum++;
        }
        sc.close();
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

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
