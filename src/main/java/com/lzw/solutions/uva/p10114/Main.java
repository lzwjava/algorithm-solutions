package com.lzw.solutions.uva.p10114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Depreciation {
        int monthNumber;
        double percentage;

        Depreciation(int monthNumber, double percentage) {
            this.monthNumber = monthNumber;
            this.percentage = percentage;
        }
    }

    double percentage(Depreciation[] depreciations, int m) {
        int i;
        for (i = 0; i < depreciations.length; i++) {
            Depreciation d = depreciations[i];
            if (d.monthNumber > m) {
                break;
            }
        }
        return depreciations[i - 1].percentage;
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int m = Integer.parseInt(st.nextToken());
            double downPayment = Double.parseDouble(st.nextToken());
            double loan = Double.parseDouble(st.nextToken());
            int records = Integer.parseInt(st.nextToken());
            if (m < 0) {
                break;
            }
            Depreciation[] depreciations = new Depreciation[records];
            for (int i = 0; i < records; i++) {
                st = new StringTokenizer(in.readLine());
                int number = Integer.parseInt(st.nextToken());
                double percentage = Double.parseDouble(st.nextToken());
                Depreciation depreciation = new Depreciation(number, percentage);
                depreciations[i] = depreciation;
            }
            int i;
            double carWorth = loan + downPayment;
            double payment = loan / m;
            for (i = 0; ; i++) {
                double percentage = percentage(depreciations, i);
                carWorth = carWorth * (1 - percentage);
                if (i > 0) {
                    loan -= payment;
                }
                if (carWorth > loan) {
                    break;
                }
            }
            if (i == 1) {
                out.append(String.format("%d month\n", i));
            } else {
                out.append(String.format("%d months\n", i));
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
