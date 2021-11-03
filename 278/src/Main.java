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

    boolean check(Pos[] positions, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (kill(positions[i], positions[j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private int total = 0;

    // r,n,q,k    
    void permutation(Pos[] positions, char[] ps, int cur) {
        if (cur == 4) {
            total++;
            return;
        }
        char piece = ps[cur - 1];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                boolean ok = true;
                for (int i = 0; i < cur; i++) {
                    if (positions[i].x == x && positions[i].y == y) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    positions[cur] = new Pos(piece, x, y);
                    if (check(positions, cur + 1)) {
                        permutation(positions, ps, cur + 1);
                    }
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
            total = 0;
            Pos[] positions = new Pos[4];
            positions[0] = new Pos(ch, m - 1, n - 1);

            char[] ps = removePiece(ch);
            permutation(positions, ps, 1);
            out.append(String.format("%d\n", total));
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
