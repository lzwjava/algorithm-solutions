package com.lzw.solutions.uva.p10281;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int timeToSec(String time) {
        String[] strs = time.split(":");
        int h = Integer.parseInt(strs[0]);
        int m = Integer.parseInt(strs[1]);
        int s = Integer.parseInt(strs[2]);
        return h * 60 * 60 + m * 60 + s;
    }

    void solve() throws IOException {
        double dist = 0;
        // km / s
        int lastSec = 0;
        double lastSpeed = 0;
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            String time = st.nextToken();
            boolean query = true;
            int speed = 0;
            if (st.hasMoreTokens()) {
                query = false;
                speed = Integer.parseInt(st.nextToken());
            }
            int sec = timeToSec(time);
            if (query) {
                double td = dist + (sec - lastSec) * lastSpeed;
                out.append(String.format("%s %.2f km\n", time, td));
            } else {
                dist += (sec - lastSec) * lastSpeed;
                lastSpeed = speed * (1.0 / 60 / 60);
                lastSec = sec;
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
