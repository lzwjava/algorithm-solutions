package com.lzw.solutions.uva.p11219;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Date {
        int year;
        int month;
        int day;

        Date(String str) {
            String strs[] = str.split("\\/");
            int day = Integer.parseInt(strs[0]);
            int month = Integer.parseInt(strs[1]);
            int year = Integer.parseInt(strs[2]);
            this.year = year;
            this.month = month;
            this.day = day;
        }

        boolean laterThan(Date a) {
            if (year > a.year) {
                return true;
            } else if (year < a.year) {
                return false;
            }
            if (month > a.month) {
                return true;
            } else if (month < a.month) {
                return false;
            }
            if (day > a.day) {
                return true;
            } else if (day < a.day) {
                return false;
            }
            // same
            return false;
        }

        boolean same(Date a) {
            return year == a.year && month == a.month && day == a.day;
        }

        boolean laterThanByMonth(Date a) {
            if (month > a.month) {
                return true;
            } else if (month < a.month) {
                return false;
            }
            if (day > a.day) {
                return true;
            } else if (day < a.day) {
                return false;
            }
            return true;
        }

        // a big
        int gapYears(Date a) {
            int years = a.year - year;
            boolean laterThan = a.laterThanByMonth(this);
            if (!laterThan) {
                years--;
            }
            return years;
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        int caseNum = 1;
        while (t > 0) {
            in.readLine();
            String a = in.readLine();
            String b = in.readLine();
            Date curDate = new Date(a);
            Date birthDate = new Date(b);
            out.append(String.format("Case #%d: ", caseNum));
            if (birthDate.laterThan(curDate)) {
                out.append("Invalid birth date\n");
            } else if (birthDate.same(curDate)) {
                out.append("0\n");
            } else {
                int years = birthDate.gapYears(curDate);
                if (years > 130) {
                    out.append("Check birth date\n");
                } else {
                    out.append(String.format("%d\n", years));
                }
            }
            caseNum++;
            t--;
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
    }
}
