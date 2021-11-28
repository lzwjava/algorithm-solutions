import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Arr {
        int r, c;

        Arr(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    class Result {
        Arr arr;
        int cnt;

        Result(Arr arr, int cnt) {
            this.arr = arr;
            this.cnt = cnt;
        }
    }

    Result multiply(Arr a1, Arr a2) {
        assert (a1.c == a2.r);
        Arr a = new Arr(a1.r, a2.c);
        int cnt = a1.r * a1.c * a2.c;
        return new Result(a, cnt);
    }

    class Node {
        int v;
        Node left;
        Node right;

        Node() {
            this.v = -1;
        }

        Node(int v) {
            this.v = v;
        }
    }

    Result dp(Arr[] arrs, int x, int y, Node root) {
        if (x == y) {
            root.v = x;
            return new Result(arrs[x], 0);
        } else if (y - x == 1) {
            Arr a1 = arrs[x];
            Arr a2 = arrs[y];
            root.left = new Node(x);
            root.right = new Node(y);
            return multiply(a1, a2);
        }
        int min = Integer.MAX_VALUE;
        Arr minResult = null;
        for (int mid = x; mid < y; mid++) {
            Node left = new Node();
            Node right = new Node();
            Result r1 = dp(arrs, x, mid, left);
            Result r2 = dp(arrs, mid + 1, y, right);
            Result r = multiply(r1.arr, r2.arr);
            int cnt = r1.cnt + r2.cnt + r.cnt;
            if (cnt < min) {
                min = cnt;
                minResult = r.arr;
                root.left = left;
                root.right = right;
            }
        }
        return new Result(minResult, min);
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            Arr[] arrs = new Arr[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                arrs[i] = new Arr(r, c);
            }
            Node root = new Node();
            Result ans = dp(arrs, 0, arrs.length - 1, root);
            out.append(String.format("Case %d: ", caseNum));
            print(root);
            out.append('\n');
            caseNum++;
        }
    }

    void print(Node node) {
        if (node.left != null && node.right != null) {
            out.append("(");
            print(node.left);
            out.append(" x ");
            print(node.right);
            out.append(")");
        } else {
            out.append(String.format("A%d", node.v + 1));
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
