package com.lzw.solutions.uva.p10258;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Submission {
        int contestant;
        int problem;
        int time;
        char L;
    }

    class Contestant implements Comparable<Contestant> {
        int id;
        HashSet<Integer> solvedProblems;
        int penalty;
        HashMap<Integer, Integer> tryProblems; // id, times

        Contestant(int id) {
            this.id = id;
            penalty = 0;
            solvedProblems = new HashSet<>();
            tryProblems = new HashMap<>();
        }

        @Override
        public int compareTo(Main.Contestant o) {
            if (solvedProblems.size() != o.solvedProblems.size()) {
                return Integer.compare(o.solvedProblems.size(), solvedProblems.size());
            } else if (penalty != o.penalty) {
                return Integer.compare(penalty, o.penalty);
            } else {
                return Integer.compare(id, o.id);
            }
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        in.readLine();
        while (t > 0) {
            ArrayList<Submission> submissions = new ArrayList<>();
            while (true) {
                String line = in.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                StringTokenizer st = new StringTokenizer(line);
                Submission s = new Submission();
                s.contestant = Integer.parseInt(st.nextToken());
                s.problem = Integer.parseInt(st.nextToken());
                s.time = Integer.parseInt(st.nextToken());
                s.L = st.nextToken().charAt(0);
                submissions.add(s);
            }
            HashMap<Integer, Contestant> contestants = new HashMap<>();
            for (int i = 0; i < submissions.size(); i++) {
                Submission s = submissions.get(i);
                Contestant c = contestants.get(s.contestant);
                if (c == null) {
                    c = new Contestant(s.contestant);
                    contestants.put(s.contestant, c);
                }
                if (s.L == 'I' || s.L == 'C') {
                    if (s.L == 'I') {
                        Integer count = c.tryProblems.get(s.problem);
                        if (count == null) {
                            count = 0;
                        }
                        count++;
                        c.tryProblems.put(s.problem, count);
                    } else {
                        Integer count = c.tryProblems.get(s.problem);
                        if (count == null) {
                            if (c.solvedProblems.contains(s.problem)) {
                                continue;
                            } else {
                                c.solvedProblems.add(s.problem);
                                int penalty = s.time;
                                c.penalty += penalty;
                            }
                        } else {
                            if (c.solvedProblems.contains(s.problem)) {
                                continue;
                            } else {
                                c.tryProblems.remove(s.problem);
                                c.solvedProblems.add(s.problem);
                                int penalty = count * 20 + s.time;
                                c.penalty += penalty;
                            }
                        }
                    }
                }
            }
            ArrayList<Contestant> list = new ArrayList<>();
            list.addAll(contestants.values());
            Collections.sort(list);
            for (Contestant c : list) {
                out.append(String.format("%d %d %d\n", c.id, c.solvedProblems.size(), c.penalty));
            }
            t--;
            if (t != 0) {
                out.append('\n');
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
