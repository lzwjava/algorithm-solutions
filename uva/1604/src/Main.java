import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    enum Dir {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    class Cube {
        char top;
        char front;

        Cube(char top, char front) {
            this.top = top;
            this.front = front;
        }

        char left() {
            String s = "BWR";
            char[] chs = new char[]{top, front};
            for (char c : s.toCharArray()) {
                if (!Arrays.asList(chs).contains(c)) {
                    return c;
                }
            }
            return ' ';
        }

        Cube turn(Dir d) {
            char ntop, nfront;
            if (d == Dir.RIGHT) {
                ntop = left();
                nfront = front;
            } else if (d == Dir.LEFT) {
                ntop = left();
                nfront = front;
            } else if (d == Dir.TOP) {
                ntop = front;
                nfront = top;
            } else {
                ntop = front;
                nfront = top;
            }
            return new Cube(ntop, nfront);
        }

        @Override
        public String toString() {
            return String.format("%c%c", top, front);
        }
    }

    char[][] tops(Cube[][] cubes) {
        char[][] ts = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Cube c = cubes[i][j];
                char top;
                if (c != null) {
                    top = c.top;
                } else {
                    top = 'E';
                }
                ts[i][j] = top;
            }
        }
        return ts;
    }

    String toString(Cube[][] cubes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String s;
                Cube c = cubes[i][j];
                if (c == null) {
                    s = "EE";
                } else {
                    s = c.toString();
                }
                sb.append(String.format("%s,", s));
            }
        }
        return sb.toString();
    }

    // top, bottom, left, right
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};

    Set<String> set;
    char[][] grid;
    int ans;

    int differ(Cube[][] cubes) {
        char[][] tops = tops(cubes);
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tops[i][j] != grid[i][j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    void dfs(Cube[][] cubes, int dist, int x, int y, int px, int py) {
        int diff = differ(cubes);
        if (diff == 0) {
            if (dist < ans) {
                ans = dist;
            }
            return;
        }
        if (dist + diff - 1 > ans) {
            return;
        }
        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3 || (nx == px && ny == py)) {
                continue;
            }
            Dir dir = Dir.values()[d];
            Cube ocube = cubes[nx][ny];
            Cube ncube = changeCube(cubes, dir, nx, ny);
            cubes[nx][ny] = null;
            cubes[x][y] = ncube;
//            String nsStr = toString(cubes);
            dfs(cubes, dist + 1, nx, ny, x, y);
//            if (!set.contains(nsStr)) {
//                set.add(nsStr);
//            }
            cubes[nx][ny] = ocube;
            cubes[x][y] = null;
        }
    }

    void solve() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (x == 0 && y == 0) {
                break;
            }
            x--;
            y--;
            int t = x;
            x = y;
            y = t;
            grid = new char[3][3];
            for (int i = 0; i < 3; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < 3; j++) {
                    grid[i][j] = st.nextToken().charAt(0);
                }
            }
            Cube[][] cubes = new Cube[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Cube c = new Cube('W', 'R');
                    if (i == x && j == y) {
                        c = null;
                    }
                    cubes[i][j] = c;
                }
            }
            set = new HashSet<>();
            set.add(toString(cubes));
            ans = 31;
            dfs(cubes, 0, x, y, -1, -1);
            if (ans == 31) {
                out.append("-1\n");
            } else {
                out.append(String.format("%d\n", ans));
            }
        }
    }

    Cube changeCube(Cube[][] cubes, Dir d, int nx, int ny) {
        Cube c;
        if (d == Dir.LEFT) {
            c = cubes[nx][ny].turn(Dir.RIGHT);
        } else if (d == Dir.RIGHT) {
            c = cubes[nx][ny].turn(Dir.LEFT);
        } else if (d == Dir.TOP) {
            c = cubes[nx][ny].turn(Dir.BOTTOM);
        } else {
            c = cubes[nx][ny].turn(Dir.TOP);
        }
        return c;
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}