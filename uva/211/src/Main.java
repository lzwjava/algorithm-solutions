import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    Scanner in;
    PrintWriter out;

    Main() {
        in = new Scanner(System.in);
        out = new PrintWriter(System.out);
    }

    int[][] grid;
    int[][] nums;
    Bone[] bones;

    class Bone {
        int id;
        int a, b;

        Bone(int id, int a, int b) {
            this.id = id;
            this.a = a;
            this.b = b;
        }
    }

    // top to bottom, left to right
    int[] dx = new int[]{1, 0};
    int[] dy = new int[]{0, 1};

    void dfs(int cur) {
        if (cur == 56) {
            return;
        }
        int x = cur / 8;
        int y = cur % 8;
        if (nums[x][y] != 0) {
            dfs(cur + 1);
        } else {
            for (int i = 0; i < bones.length; i++) {
                Bone bn = bones[i];
                for (int d = 0; d < 2; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx >= 7 || ny >= 8) {
                        continue;
                    }
                    for (int k = 0; k < 2; k++) {
                        int p, q;
                        if (k == 0) {
                            p = bn.a;
                            q = bn.b;
                        } else {
                            p = bn.b;
                            q = bn.a;
                        }
                        if (p == grid[x][y] && q == grid[nx][ny]) {
                            
                        }
                    }
                }
            }
        }
    }

    void solve() throws IOException {
        int p = 1;
        bones = new Bone[28];
        for (int i = 0; i < 6; i++) {
            for (int j = i; j < 6; j++) {
                Bone bone = new Bone(p, i, j);
                bones[p++] = bone;
            }
        }
        while (in.hasNextInt()) {
            grid = new int[7][8];
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    grid[i][j] = in.nextInt();
                }
            }
            nums = new int[7][8];
            dfs(0);
        }
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
//        System.setOut(new PrintStream(new FileOutputStream("1.out")));
        Main m = new Main();
        m.solve();
        m.close();
    }
}