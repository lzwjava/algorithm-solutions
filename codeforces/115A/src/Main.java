import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    int[] rank;
    int[] parent;

    int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            return find(parent[x]);
        }
    }

    void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) {
            return;
        }
        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else {
            parent[py] = px;
            if (rank[px] == rank[py]) {
                rank[px]++;
            }
        }
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(in.readLine()) - 1;
        }
        rank = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = i;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}