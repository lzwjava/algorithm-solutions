import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    BufferedReader in;
    PrintWriter out;

    static final int MAXN = (int) (1e6);
    private ArrayList<HashMap<Character, Integer>> to;
    private int[] link;
    private int[] len;
    private boolean[] finalState;
    private int last;
    private int sz;

    private void initialize() {
        link = new int[MAXN];
        len = new int[MAXN];
        finalState = new boolean[MAXN];

        last = 0;
        sz = 1;

        to = new ArrayList<>();

        for (int i = 0; i < MAXN; i++)
            to.add(new HashMap<>());
    }

    private void addCharacter(char c, boolean mark) {
        int prev = last;
        int curr = (last = sz++);

        if (mark)
            finalState[curr] = true;

        len[curr] = len[prev] + 1;
        for (; to.get(prev).get(c) == null; prev = link[prev])
            to.get(prev).put(c, curr);

        if (to.get(prev).get(c) == curr) {
            link[curr] = 0;
            return;
        }

        int next = to.get(prev).get(c);
        if (len[next] == len[prev] + 1) {
            link[curr] = next;
            return;
        }

        int next2 = sz++;

        if (mark)
            finalState[next2] = true;

        to.get(next2).putAll(to.get(next));

        link[next2] = link[next];
        len[next2] = len[curr] + 1;

        link[curr] = link[next] = next2;

        for (; to.get(prev).get(c) == next; prev = link[prev])
            to.get(prev).put(c, next2);
    }

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            initialize();
            int n = Integer.parseInt(s);
            String[] strs = new String[n];
            for (int i = 0; i < n; i++) {
                strs[i] = in.readLine();
            }
            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    addCharacter('$', false);
                }
                String str = strs[i];
                for (int j = 0; j < str.length(); j++) {
                    addCharacter(str.charAt(j), i == n - 1 && j == str.length() - 1);
                }
            }
            out.append('\n');
//            out.append(String.format("%d\n", sum.intValue()));
        }
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