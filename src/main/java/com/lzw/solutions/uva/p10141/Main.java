package com.lzw.solutions.uva.p10141;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Proposal {
        String name;
        double d;
        int r;
        String[] requirements;
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            if (n == 0 && p == 0) {
                break;
            }
            String[] requirements = new String[n];
            for (int i = 0; i < n; i++) {
                requirements[i] = in.readLine();
            }
            Proposal[] proposals = new Proposal[p];
            for (int i = 0; i < p; i++) {
                String name = in.readLine();
                line = in.readLine();
                st = new StringTokenizer(line);
                double d = Double.parseDouble(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                String[] prs = new String[r];
                for (int j = 0; j < r; j++) {
                    prs[j] = in.readLine();
                }
                Proposal proposal = new Proposal();
                proposal.name = name;
                proposal.d = d;
                proposal.r = r;
                proposal.requirements = prs;
                proposals[i] = proposal;
            }
            int highestMet = 0;
            double highestD = -1;
            String highestName = "";
            for (int i = 0; i < p; i++) {
                Proposal proposal = proposals[i];
                if (proposal.r > highestMet
                        || (proposal.r == highestMet && (proposal.d < highestD || highestD == -1))) {
                    highestMet = proposal.r;
                    highestD = proposal.d;
                    highestName = proposal.name;
                }
            }
            if (caseNum != 1) {
                out.append('\n');
            }
            out.append(String.format("RFP #%d\n", caseNum));
            out.append(String.format("%s\n", highestName));
            caseNum++;
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
            outStream = new PrintStream("1.out");
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
