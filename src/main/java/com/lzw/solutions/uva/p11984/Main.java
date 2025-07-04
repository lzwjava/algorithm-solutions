package com.lzw.solutions.uva.p11984;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    double f2c(int df) {
        return df * 5.0 / 9;
    }

    void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int caseNum = 1;
        while (t > 0) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int C = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            double nc = C + f2c(d);
            System.out.println(String.format("Case %d: %.2f", caseNum, nc));
            caseNum++;
            t--;
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
