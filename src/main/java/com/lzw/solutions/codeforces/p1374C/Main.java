package com.lzw.solutions.codeforces.p1374C;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            String s = in.next();
            Stack<Character> stack = new Stack<>();
            for (char c : s.toCharArray()) {
                if (c == '(' || stack.size() == 0) {
                    stack.add(c);
                } else {
                    // )
                    char peek = stack.peek();
                    if (peek == '(') {
                        stack.pop();
                    } else {
                        stack.add(c);
                    }
                }
            }
            System.out.println(stack.size() / 2);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
