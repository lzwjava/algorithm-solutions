// grok-4.1-thinking
package com.lzw.solutions.codeforces.p2189A;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            int minHL = Math.min(h, l);

            int cntRow = 0;
            int cntCol = 0;
            int cntBoth = 0;

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int x = Integer.parseInt(st.nextToken());
                if (x <= minHL) {
                    cntBoth++;
                    cntRow++;
                    cntCol++;
                } else if (x <= h) {
                    cntRow++;
                } else if (x <= l) {
                    cntCol++;
                }
            }

            int usable = cntRow + cntCol - cntBoth;
            int ans = usable / 2;
            ans = Math.min(ans, cntRow);
            ans = Math.min(ans, cntCol);

            out.println(ans);
        }

        out.close();
    }
}
