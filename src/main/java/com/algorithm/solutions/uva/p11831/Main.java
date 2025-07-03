package com.algorithm.solutions.uva.p11831;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int readInt() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    String dir = "NLSO";
    int[] dx = new int[]{-1, 0, 1, 0};
    int[] dy = new int[]{0, 1, 0, -1};

    char turnRight(char d) {
        int i = dir.indexOf(d);
        int len = dir.length();
        return dir.charAt((i + 1) % len);
    }

    char turnLeft(char d) {
        int i = dir.indexOf(d);
        int len = dir.length();
        return dir.charAt((i - 1 + len) % len);
    }

    int sticker = 0;

    void dfs(int sx, int sy, char d, char[] op) {
        for (int i = 0; i < op.length; i++) {
            char ch = op[i];
            if (ch == 'D') {
                char nd = turnRight(d);
                d = nd;
            } else if (ch == 'E') {
                d = turnLeft(d);
            } else if (ch == 'F') {
                int j = dir.indexOf(d);
                int nx = sx + dx[j];
                int ny = sy + dy[j];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    char g = grid[nx][ny];
                    if (g == '*' || g == '.') {
                        if (g == '*') {
                            sticker++;
                            grid[nx][ny] = '.';
                        }
                        sx = nx;
                        sy = ny;
                    }
                }
            }
        }
    }

    int n, m, s;
    char[][] grid;

    void solve() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0 && s == 0) {
                break;
            }
            grid = new char[n][m];
            int sx = 0, sy = 0;
            char d = ' ';
            for (int i = 0; i < n; i++) {
                String line = in.readLine();
                for (int j = 0; j < m; j++) {
                    char c = line.charAt(j);
                    grid[i][j] = c;
                    if (dir.indexOf(c) >= 0) {
                        sx = i;
                        sy = j;
                        d = c;
                    }
                }
            }
            grid[sx][sy] = '.';
            String ins = in.readLine();
            char[] op = new char[s];
            for (int i = 0; i < s; i++) {
                op[i] = ins.charAt(i);
            }
            sticker = 0;
            dfs(sx, sy, d, op);
            out.append(String.format("%d\n", sticker));
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
            inStream = new FileInputStream("2.in");
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
