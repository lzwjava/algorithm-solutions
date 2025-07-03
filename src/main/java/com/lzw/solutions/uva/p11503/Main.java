package com.lzw.solutions.uva.p11503;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Friend {
        int a;
        int b;

        Friend() {}

        Friend(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    Integer findOrCreateId(HashMap<String, Integer> ids, String name) {
        Integer id = ids.get(name);
        if (id == null) {
            Integer nid = ids.size();
            ids.put(name, nid);
            return nid;
        } else {
            return id;
        }
    }

    int getGroup(int[] groups, int x) {
        if (groups[x] == x) {
            return x;
        } else {
            return getGroup(groups, groups[x]);
        }
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        while (n > 0) {
            int f = Integer.parseInt(in.readLine());
            HashMap<String, Integer> ids = new HashMap<>();
            Friend[] friends = new Friend[f];
            for (int i = 0; i < f; i++) {
                String line = in.readLine();
                StringTokenizer st = new StringTokenizer(line);
                String a = st.nextToken();
                String b = st.nextToken();
                Integer aid = findOrCreateId(ids, a);
                Integer bid = findOrCreateId(ids, b);
                Friend friend = new Friend(aid, bid);
                friends[i] = friend;
            }
            int len = ids.size();
            int[] groups = new int[len];
            int[] rank = new int[len];
            int[] sizes = new int[len];
            for (int i = 0; i < len; i++) {
                groups[i] = i;
                rank[i] = 0;
                sizes[i] = 1;
            }
            for (int i = 0; i < f; i++) {
                Friend friend = friends[i];
                int ga = getGroup(groups, friend.a);
                int gb = getGroup(groups, friend.b);
                if (ga != gb) {
                    int ra = rank[ga];
                    int rb = rank[gb];
                    if (ra < rb) {
                        groups[ga] = gb;
                        sizes[gb] += sizes[ga];
                    } else {
                        groups[gb] = ga;
                        sizes[ga] += sizes[gb];
                        if (ra == rb) {
                            rank[ga]++;
                        }
                    }
                }
                int g = getGroup(groups, friend.a);
                out.append(String.format("%d\n", sizes[g]));
            }
            n--;
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
