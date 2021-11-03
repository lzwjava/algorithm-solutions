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

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    char[] pieces = new char[]{'r', 'n', 'q', 'k'};

    int indexOfPiece(char ch) {
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    class Pos {
        int x;
        int y;
        char ch;

        Pos() {
        }

        Pos(char ch, int x, int y) {
            this.ch = ch;
            this.x = x;
            this.y = y;
        }
    }

    int[] dx = {-1, 0, 1, 0, -1, 1, -1, 1};
    int[] dy = {0, -1, 0, 1, -1, 1, 1, -1};

    int[] kx = {-1, -1, 1, 1, 2, 2, -2, -2};
    int[] ky = {-2, 2, -2, 2, -1, 1, -1, 1};

    boolean kill(Pos a, Pos b) {
        if (a.ch == 'r') {
            return a.x == b.x || a.y == b.y;
        } else if (a.ch == 'n') {
            for (int i = 0; i < 8; i++) {
                int nx = a.x + kx[i];
                int ny = a.y + ky[i];
                if (nx == b.x && ny == b.y) {
                    return true;
                }
            }
        } else if (a.ch == 'q') {
            return a.x == b.x || a.y == b.y || a.x + a.y == b.x + b.y || a.x - a.y == b.x - b.y;
        } else if (a.ch == 'k') {
            for (int i = 0; i < 8; i++) {
                int nx = a.x + dx[i];
                int ny = a.y + dy[i];
                if (nx == b.x && ny == b.y) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean check(ArrayList<Pos> positions, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (kill(positions.get(i), positions.get(j))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    int maxn;

    void permutation(ArrayList<Pos> positions, char piece, int cur, int m, int n) {
        if (cur > maxn) {
            maxn = cur;
        }
        int sx, sy;
        if (cur == 0) {
            sx = 0;
            sy = 0;
        } else {
            Pos p = positions.get(cur - 1);
//            int sum = p.x * m + p.y;
            sx = p.x;
            sy = p.y;
        }
        for (int x = sx; x < m; x++) {
            for (int y = sy; y < n; y++) {
                boolean ok = true;
                for (int i = 0; i < cur; i++) {
                    if (positions.get(i).x == x && positions.get(i).y == y) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    Pos pos = new Pos(piece, x, y);
                    positions.add(pos);
                    if (check(positions, cur + 1)) {
                        permutation(positions, piece, cur + 1, m, n);
                    }
                    positions.remove(pos);
                }
            }
        }
    }

    char[] removePiece(char ch) {
        char[] chs = new char[3];
        int p = 0;
        for (int i = 0; i < 4; i++) {
            if (pieces[i] != ch) {
                chs[p++] = pieces[i];
            }
        }
        return chs;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            char ch = st.nextToken().charAt(0);
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            maxn = 0;
            ArrayList<Pos> positions = new ArrayList<Pos>();

            permutation(positions, ch, 0, m, n);
            out.append(String.format("%d\n", maxn));
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
