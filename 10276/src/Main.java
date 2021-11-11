import java.io.*;
import java.util.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean isSquare(int x) {
        int s = (int) Math.round(Math.sqrt(x));
        return s * s == x;
    }

    String getKey(ArrayList<Integer>[] list) {
        ArrayList<Integer>[] clist = new ArrayList[list.length];
        for (int i = 0; i < list.length; i++) {
            clist[i] = new ArrayList<>();
            clist[i].addAll(list[i]);
        }
        Arrays.sort(clist, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                int s1 = o1.size();
                int s2 = o2.size();
                if (s1 != s2) {
                    return Integer.compare(s2, s1);
                }
                for (int i = 0; i < s1; i++) {
                    int a1 = o1.get(i);
                    int a2 = o2.get(i);
                    if (a1 != a2) {
                        return Integer.compare(a1, a2);
                    }
                }
                return 0;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < clist.length; i++) {
            for (int j = 0; j < clist[i].size(); j++) {
                sb.append(String.format("%d,", clist[i].get(j)));
            }
        }
        return sb.toString();
    }

    void permutation(ArrayList<Integer>[] list, Set<String> vis, int n, int x) {
        for (int i = 0; i < n; i++) {
            int size = list[i].size();
            if (size == 0 || (size > 0 && isSquare(list[i].get(size - 1) + x))) {
                list[i].add(x);
                String key = getKey(list);
                if (!vis.contains(key)) {
                    if (x > max) {
                        max = x;
                    }
                    vis.add(key);
                    permutation(list, vis, n, x + 1);
                }
                list[i].remove(Integer.valueOf(x));
            }
        }
    }

    int max;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            ArrayList<Integer>[] list = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                list[i] = new ArrayList<>();
            }
            Set<String> vis = new HashSet<>();
            max = 0;
            permutation(list, vis, n, 1);
            out.append(String.format("%d\n", max));
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
