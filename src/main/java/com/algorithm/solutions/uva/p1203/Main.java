package com.algorithm.solutions.uva.p1203;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Query {
        int num;
        int period;

        Query(int qnum, int period) {
            this.num = qnum;
            this.period = period;
        }
    }

    class Job implements Comparable<Job> {
        int num;
        int time;
        int period;

        Job(int num, int time, int period) {
            this.num = num;
            this.time = time;
            this.period = period;
        }

        @Override
        public int compareTo(Job o) {
            if (time != o.time) {
                return Integer.compare(time, o.time);
            } else {
                return Integer.compare(num, o.num);
            }
        }
    }

    void solve() throws IOException {
        ArrayList<Query> queries = new ArrayList<Query>();
        while (true) {
            String s = in.readLine();
            if (s.equals("#")) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            int period = Integer.parseInt(st.nextToken());
            Query q = new Query(num, period);
            queries.add(q);
        }
        int k = Integer.parseInt(in.readLine());
        PriorityQueue<Job> queue = new PriorityQueue<>();
        for (Query q : queries) {
            queue.add(new Job(q.num, q.period, q.period));
        }
        while (!queue.isEmpty()) {
            Job job = queue.poll();
            if (k != 0) {
                out.append(String.format("%d\n", job.num));
                k--;
            } else {
                break;
            }
            queue.add(new Job(job.num, job.time + job.period, job.period));
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
