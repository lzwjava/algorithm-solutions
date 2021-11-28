import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // left, top, right, bottom
    static final int dx[] = { 0, -1, 0, 1 };
    static final int dy[] = { -1, 0, 1, 0 };

    // horse, left bottom, left top, top left, top right, right top, right bottom, 
    // bottom right bottom left
    static final int hdx[] = { 1, -1, -2, -2, -1, 1, 2, 2};
    static final int hdy[] = { -2, -2, -1, 1, 2, 2, 1, -1};

    class Piece {
        char ch;
        int x;
        int y;

        Piece() {

        }

        Piece(char ch, int x, int y) {
            this.ch = ch;
            this.x = x;
            this.y = y;
        }

        Piece(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void modify() {
            x--;
            y--;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() != Piece.class) {
                return false;
            }
            Piece p = (Piece) obj;
            return p.x == this.x && p.y == this.y;
        }
    }
    
    int blocksInY(Piece[] pieces, Piece red, Piece black) {
        assert (black.y == red.y);
        assert (black.x != red.x);
        Piece smallPiece, bigPiece;
        if (black.x < red.x) {
            smallPiece = black;
            bigPiece = red;
        } else {
            smallPiece = red;
            bigPiece = black;
        }
        
        assert (black.x < red.x);
        int cnt = 0;
        for (int i = 0; i < pieces.length; i++) {
            Piece p = pieces[i];
            if (p.y == black.y && p.x > smallPiece.x && p.x < bigPiece.x) {
                // block
                cnt++;
            }
        }
        return cnt;
    }

    boolean isBlockedInY(Piece[] pieces, Piece red, Piece black) {
        return blocksInY(pieces, red, black) > 0;
    }

    boolean isBlockedInX(Piece[] pieces, Piece red, Piece black) {
        return blocksInX(pieces, red, black) > 0;
    }
    
    int blocksInX(Piece[] pieces, Piece red, Piece black) {
        assert (red.x == black.x);
        assert (red.y != black.y);
        Piece smallPiece, bigPiece;
        if (red.y < black.y) {
            smallPiece = red;
            bigPiece = black;
        } else {
            smallPiece = black;
            bigPiece = red;
        }
        int cnt = 0;
        for (int i = 0; i < pieces.length; i++) {
            Piece p = pieces[i];
            if (p.x == black.x && p.y > smallPiece.y && p.y < bigPiece.y) {
                // block
                cnt++;
            }
        }
        return cnt;
    }
    
    Piece[] cleanPieces(Piece[] pieces, Piece red) {
        int n = pieces.length - 1;
        Piece[] pieces1 = new Piece[n];
        int p = 0;
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].equals(red)) {
                continue;
            } else {
                pieces1[p] = pieces[i];
                p++;
            }
        }
        return pieces1;
    }

    Piece[] addPiece(Piece[] pieces, Piece black) {
        int n = pieces.length + 1;
        Piece[] pieces1 = Arrays.copyOf(pieces, n);
        pieces1[n - 1] = black;
        return pieces1;
    }
    
    boolean exists(Piece[] pieces, Piece target) {
        for (int i = 0; i < pieces.length; i++) {
            if (target.equals(pieces[i])) {
                return true;
            }
        }
        return false;
    }

    boolean canKill(Piece[] pieces, Piece red, Piece black) {
        // int n = pieces1.length;
        if (red.ch == 'G') {
            if (black.y != red.y) {
                return false;
            }
            assert (black.x < red.x);
            return !isBlockedInY(pieces, red, black);
        } else if (red.ch == 'R') {
            if (red.x != black.x && red.y != black.y) {
                return false;
            }
            if (red.x == black.x) {
                return !isBlockedInX(pieces, red, black);
            } else {
                // y equal
                return !isBlockedInY(pieces, red, black);
            }
        } else if (red.ch == 'H') {
            Piece[] allPieces = addPiece(pieces, black);
            for (int i = 0; i < 4; i++) {
                int nx = red.x + dx[i];
                int ny = red.y + dy[i];
                Piece np = new Piece();
                np.x = nx;
                np.y = ny;
                if (exists(allPieces, np)) {
                    // hobbling
                    continue;
                }
                for (int j = 0; j < 2; j++) {
                    int hnx = red.x + hdx[i * 2 + j];
                    int hny = red.y + hdy[i * 2 + j];
                    Piece hnp = new Piece(hnx, hny);
                    if (hnp.equals(black)) {
                        return true;
                    }
                }
            }
            return false;
        } else if (red.ch == 'C') {
            if (red.x != black.x && red.y != black.y) {
                return false;
            }
            if (red.x == black.x) {
                return blocksInX(pieces, red, black) == 1;
            } else {
                // y equal
                return blocksInY(pieces, red, black) == 1;
            }
        }
        return false;
    }
    
    boolean checkDelivered(Piece[] pieces, Piece blackGenernal) {
        for (int i = 0; i < pieces.length; i++) {
            Piece p = pieces[i];
            Piece pieces1[] = cleanPieces(pieces, p);
            if (canKill(pieces1, p, blackGenernal)) {
                return true;
            }
        }
        return false;
    }

    void work() {
        Scanner sc = new Scanner(System.in);
        for (;;) {
            int n = sc.nextInt();
            Piece blackGernal = new Piece();
            blackGernal.x = sc.nextInt();
            blackGernal.y = sc.nextInt();

            if (n == 0 && blackGernal.x == 0 && blackGernal.y == 0) {
                break;
            }
            blackGernal.modify();
            Piece[] pieces = new Piece[n];
            for (int i = 0; i < n; i++) {
                Piece p = new Piece();
                pieces[i] = p;
                String type = sc.next();
                p.ch = type.charAt(0);
                p.x = sc.nextInt();
                p.y = sc.nextInt();
                p.modify();
            }
            assert (checkDelivered(pieces, blackGernal));
            boolean ok = false;
            for (int i = 0; i < 4; i++) {
                int nx = blackGernal.x + dx[i];
                int ny = blackGernal.y + dy[i];
                if (nx >= 0 && nx <= 2 && ny >= 3 && ny <= 5) {
                    // in palace
                    Piece nextBlack = new Piece(nx, ny);
                    if (exists(pieces, nextBlack)) {
                        // eat
                        Piece remainPieces[] = cleanPieces(pieces, nextBlack);
                        if (!checkDelivered(remainPieces, nextBlack)) {
                            ok = true;
                            break;
                        }
                        continue;
                    }
                    if (!checkDelivered(pieces, nextBlack)) {
                        ok = true;
                        break;
                    }
                } else {
                    continue;
                }
            }
            if (ok) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("3.in");
            outStream = new PrintStream("3.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
