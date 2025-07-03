package com.lzw.solutions.uva.p12569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int n, m, s, t;
    int[] os;
    boolean[][] grid;
    Random random;
    int ans;
    List<Move> ansMoves;
    Map<Integer, Integer> map;

    class Move {
        int a, b;

        Move(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    void dfs(int[] st, int cur, int dist, List<Move> moves) {
        if (st[t] == 1) {
            if (dist < ans) {
                ans = dist;
                ansMoves = new ArrayList<>(moves);
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (st[i] != 0) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] && st[j] == 0) {
                        swap(st, i, j);
                        int key = key(st);
                        int d = dist(key);
                        if (d > dist + 1 && dist + 1 < ans) {
                            map.put(key, dist + 1);
                            moves.add(new Move(i, j));
                            dfs(st, cur + 1, dist + 1, moves);
                            moves.remove(moves.size() - 1);
                        }
                        swap(st, i, j);
                    }
                }
            }
        }
    }

    int key(int[] a) {
        return Arrays.hashCode(a);
    }

    int dist(int key) {
        Integer d = map.get(key);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    void swap(int[] a, int x, int y) {
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    void solve() throws IOException {
        int tt = Integer.parseInt(in.readLine());
        for (int p = 0; p < tt; p++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken()) - 1;
            t = Integer.parseInt(st.nextToken()) - 1;
            os = new int[m];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < m; i++) {
                os[i] = Integer.parseInt(st.nextToken()) - 1;
            }
            grid = new boolean[n][n];
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                grid[u][v] = grid[v][u] = true;
            }
            // 1: robot, 2: obstacle, 0: empty
            int[] state = new int[n];
            state[s] = 1;
            for (int i = 0; i < m; i++) {
                state[os[i]] = 2;
            }
            random = new Random();
            ansMoves = new ArrayList<>();
            ans = Integer.MAX_VALUE;
            map = new HashMap<>();
            map.put(key(state), 0);
            dfs(state, 0, 0, new ArrayList<>());
            if (ans == Integer.MAX_VALUE) {
                ans = -1;
            }
            out.append(String.format("Case %d: %d\n", p + 1, ans));
            for (Move m : ansMoves) {
                out.append(String.format("%d %d\n", m.a + 1, m.b + 1));
            }
            out.append('\n');
            out.flush();
        }
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        MainPlus m = new MainPlus();
        m.solve();
        m.close();
    }
}