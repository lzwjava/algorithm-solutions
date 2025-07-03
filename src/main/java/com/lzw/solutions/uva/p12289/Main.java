package com.lzw.solutions.uva.p12289;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    class MyString {
        String str;

        MyString(String str) {
            this.str = str;            
        }

        @Override
        public boolean equals(Object obj) {
            MyString b = (MyString) obj;
            if (b.str.length() != str.length()) {
                return false;
            }
            int count = 0;
            for (int i = 0; i < b.str.length(); i++) {
                char sch = b.str.charAt(i);
                char strch = str.charAt(i);
                if (sch != strch) {
                    count++;
                    if (count > 1) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n > 0) {
            String word = sc.next();
            ArrayList<MyString> list = new ArrayList<>();
            list.add(new MyString("one"));
            list.add(new MyString("two"));
            list.add(new MyString("three"));
            int index = list.indexOf(new MyString(word));
            System.out.println(index+1);
            n--;
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

        new Main().solve();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
