package com.lzw.solutions.uva.p1602;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;
    int grid[][];
    int n;
    int w;
    int h;
    ArrayList<State> states;

    class State {
        int grid[][];

        State(int grid[][]) {
            this.grid = grid.clone();
            for (int i = 0; i < this.grid.length; i++) {
                this.grid[i] = this.grid[i].clone();
            }
        }

        int[][] strip(int grid[][]) {
            int nh = grid.length;
            int nw = grid[0].length;

            int h1, h2;
            for (h1 = 0; h1 < nh; h1++) {
                boolean found = false;
                for (int i = 0; i < nw; i++) {
                    if (grid[h1][i] != 0) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            for (h2 = nh - 1; h2 >= 0; h2--) {
                boolean found = false;
                for (int i = 0; i < nw; i++) {
                    if (grid[h2][i] != 0) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }

            int w1, w2;
            for (w1 = 0; w1 < nw; w1++) {
                boolean found = false;
                for (int i = 0; i < nh; i++) {
                    if (grid[i][w1] != 0) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            for (w2 = nw - 1; w2 >= 0; w2--) {
                boolean found = false;
                for (int i = 0; i < nh; i++) {
                    if (grid[i][w2] != 0) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            int hlen = h2 - h1 + 1;
            int wlen = w2 - w1 + 1;
            int ngrid[][] = new int[hlen][wlen];
            for (int i = 0; i < hlen; i++) {
                for (int j = 0; j < wlen; j++) {
                    ngrid[i][j] = grid[h1 + i][w1 + j];
                }
            }
            return ngrid;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof State)) {
                return false;
            }
            State o = (State) obj;

            int grid1[][] = strip(grid);
            int grid2[][] = strip(o.grid);
            if (grid1.length != grid2.length || grid1[0].length != grid2[0].length) {
                return false;
            }
            int nh = grid1.length;
            int nw = grid1[0].length;
            for (int i = 0; i < nh; i++) {
                for (int j = 0; j < nw; j++) {
                    if (grid1[i][j] != grid2[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int[][] deepCopy(int grid[][]) {
        int ngrid[][] = grid.clone();
        for (int i = 0; i < grid.length; i++) {
            ngrid[i] = grid[i].clone();
        }
        return ngrid;
    }

    int[][] horizontalFlip(int grid[][]) {
        int nh = grid.length;
        int nw = grid[0].length;
        int[][] ngrid = new int[nh][nw];
        for (int i = 0; i < nh; i++) {
            for (int j = 0; j < nw; j++) {
                ngrid[i][j] = grid[i][nw - 1 - j];
            }
        }
        return ngrid;
    }

    int[][] verticalFlip(int grid[][]) {
        int nh = grid.length;
        int nw = grid[0].length;
        int[][] ngrid = new int[nh][nw];
        for (int i = 0; i < nh; i++) {
            for (int j = 0; j < nw; j++) {
                ngrid[i][j] = grid[nh - 1 - i][j];
            }
        }
        return ngrid;
    }

    int[][] rotate180(int grid[][]) {
        // 180
        int[][] ngrid = verticalFlip(grid);
        return horizontalFlip(ngrid);
    }

    int[][] rotateMinus180(int grid[][]) {
        int[][] ngrid = horizontalFlip(grid);
        return verticalFlip(ngrid);
    }

    int[][] rotate90(int grid[][]) {
        int nh = grid.length;
        int nw = grid[0].length;
        // 0,0 -> 0, 2
        int[][] ngrid = new int[nw][nh];
        for (int i = 0; i < nw; i++) {
            for (int j = 0; j < nh; j++) {
                ngrid[i][j] = grid[j][i];
            }
        }
        return horizontalFlip(ngrid);
    }

    void printGrid(int grid[][]) {
        int nh = grid.length;
        int nw = grid[0].length;
        for (int i = 0; i < nh; i++) {
            for (int j = 0; j < nw; j++) {
                out.append(String.valueOf(grid[i][j]));
            }
            out.append('\n');
        }
        out.append('\n');
    }

    // left top right bottom
    int dx[] = {0, -1, 0, 1};
    int dy[] = {-1, 0, 1, 0};

    void dfs(int grid[][], boolean vis[][], int i, int j) {
        vis[i][j] = true;
        int nh = grid.length;
        int nw = grid[0].length;
        for (int d = 0; d < 4; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];
            if (nx >= 0 && nx < nh && ny >= 0 && ny < nw && grid[nx][ny] == 1 && !vis[nx][ny]) {
                dfs(grid, vis, nx, ny);
            }
        }
    }

    int calCount(int grid[][]) {
        boolean vis[][] = new boolean[h][w];
        int count = 0;
        int nh = grid.length;
        int nw = grid[0].length;
        for (int i = 0; i < nh; i++) {
            for (int j = 0; j < nw; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    dfs(grid, vis, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    boolean isConnected(int grid[][]) {
        int count = calCount(grid);
        return count == 1;
    }

    boolean inside(int x, int y) {
        if (x >= 0 && x < h && y >= 0 && y < w) {
            return true;
        } else {
            return false;
        }
    }

    void permutaion(int pos, int polyominoes) {
        if (pos == w * h) {
            if (polyominoes != n) {
                return;
            }
            if (!isConnected(grid)) {
                return;
            }
            // must be n
            State st = new State(grid);
            if (states.contains(st)) {
                return;
            }
            int[][] grid1 = horizontalFlip(grid);
            if (states.contains(new State(grid1))) {
                return;
            }
            int[][] grid2 = verticalFlip(grid);
            if (states.contains(new State(grid2))) {
                return;
            }
            int[][] grid3 = deepCopy(grid);
            int[][] grid4 = deepCopy(grid1);
            int[][] grid5 = deepCopy(grid2);
            for (int i = 0; i < 3; i++) {
                grid3 = rotate90(grid3);
                if (states.contains(new State(grid3))) {
                    return;
                }

                grid4 = rotate90(grid4);
                if (states.contains(new State(grid4))) {
                    return;
                }

                grid5 = rotate90(grid5);
                if (states.contains(new State(grid5))) {
                    return;
                }
            }
            // printGrid(grid);
            states.add(st);
            return;
        }
        int x = pos / w;
        int y = pos % w;
        if (y == 0 && x > 0) {
            int ngrid[][] = new int[x][w];
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < w; j++) {
                    ngrid[i][j] = grid[i][j];
                }
            }
            int count = calCount(ngrid);
            if (count >= 2) {
                return;
            }
        }
        if (polyominoes < n) {
            grid[x][y] = 1;
            permutaion(pos + 1, polyominoes + 1);

            grid[x][y] = 0;
            permutaion(pos + 1, polyominoes);
        } else if (polyominoes == n) {
            grid[x][y] = 0;
            permutaion(pos + 1, polyominoes);
        }
    }

    void solve() throws IOException {
        // h = 3;
        // w = 2;
        // int grid[][] = {
        //     {1, 2},
        //     {3, 4},
        //     {5, 6}
        // };
        // int ngrid[][] = rotate180(grid);
        // printGrid(ngrid);
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            grid = new int[h][w];
            states = new ArrayList<>();
            permutaion(0, 0);
            out.append(String.format("%d\n", states.size()));
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
            inStream = new FileInputStream("2.in");
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
