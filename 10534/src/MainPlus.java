import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int n;
    int[] nums;
    int max;

    void permutation(int cur, ArrayList<Integer> parts) {
        if (cur == n) {
            if (validFinally(parts)) {
                int pn = parts.size();
                if (pn > max) {
                    max = pn;
                }
            }
            return;
        }
        parts.add(nums[cur]);
        if (valid(cur, parts)) {
            permutation(cur + 1, parts);
        }
        parts.remove(parts.size() - 1);

        permutation(cur + 1, parts);
    }

    boolean validFinally(ArrayList<Integer> parts) {
        int pn = parts.size();
        if (pn % 2 == 0) {
            return false;
        }
        int sn = (pn - 1) / 2;
        for (int i = 0; i < sn; i++) {
            if (Integer.compare(parts.get(i), parts.get(i + 1)) >= 0) {
                return false;
            }
        }
        for (int i = sn; i < pn - 1; i++) {
            if (Integer.compare(parts.get(i), parts.get(i + 1)) <= 0) {
                return false;
            }
        }
        return true;
    }

    boolean valid(int cur, ArrayList<Integer> parts) {
        int pn = parts.size();
        int[] comps = new int[pn - 1];
        for (int i = 0; i < pn - 1; i++) {
            comps[i] = Integer.compare(parts.get(i), parts.get(i + 1));
        }
        int i;
        for (i = 0; i < pn - 1; i++) {
            if (comps[i] >= 0) {
                break;
            }
        }

        int mid = i;

        boolean ok = true;
        for (; i < pn - 1; i++) {
            if (comps[i] <= 0) {
                ok = false;
                break;
            }
        }
        if (!ok) {
            return false;
        }

        // i: pn -1

        int sn = mid;
        int rn = i - mid;

        if (rn > sn) {
            return false;
        }

        return true;
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            n = Integer.parseInt(line);
            nums = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            ArrayList<Integer> parts = new ArrayList<>();
            max = 0;
            permutation(0, parts);
            out.append(String.format("%d\n", max));
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
            inStream = new FileInputStream("3.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        MainPlus main = new MainPlus();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
