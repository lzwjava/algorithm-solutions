import java.io.*;

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

    String numToStr(int num) {
        if (num == 0) {
            return "Blank";
        } else {
            return String.valueOf(num);
        }
    }

    void solve() throws IOException {
        while (true) {
            int n = readInt();
            if (n == 0) {
                break;
            }
            int sheets = (int) Math.ceil(n * 1.0 / 4);
            int m = sheets * 2;
            int[][] nums = new int[m][2];
            int p = 1;
            int v = 1;
            for (int i = 0; i < m; i++) {
                nums[i][p] = v++;
                p = 1 - p;
                if (v == n + 1) {
                    break;
                }
            }
            if (v != n + 1) {
                for (int i = m - 1; i >= 0; i--) {
                    nums[i][p] = v++;
                    p = 1 - p;
                    if (v == n + 1) {
                        break;
                    }
                }
            }
            out.append(String.format("Printing order for %d pages:\n", n));
            for (int i = 0; i < m; i++) {
                String s;
                if (i % 2 == 0) {
                    s = "front";
                } else {
                    s = "back";
                }
                int sheet = i / 2 + 1;
                if (nums[i][0] != 0 || nums[i][1] != 0) {
                    out.append(String.format("Sheet %d, %s: %s, %s\n", sheet, s,
                        numToStr(nums[i][0]), numToStr(nums[i][1])));
                }
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
