import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Maker {
        String name;
        int low;
        int high;

        Maker(String name, int low, int high) {
            this.name = name;
            this.low = low;
            this.high = high;
        }
    }

    String getName(ArrayList<Integer>[] counts, Maker[] makers, int index) {
        String ans;
        if (counts[index].size() > 1) {
            ans = "UNDETERMINED";
        } else if (counts[index].size() == 1) {
            ans = makers[counts[index].get(0)].name;
        } else {
            ans = "UNDETERMINED";
        }
        return ans;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int d = Integer.parseInt(in.readLine());
            Maker[] makers = new Maker[d];
            for (int i = 0; i < d; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                String name = st.nextToken();
                int low = Integer.parseInt(st.nextToken());
                int high = Integer.parseInt(st.nextToken());
                makers[i] = new Maker(name, low, high);
            }
            int q = Integer.parseInt(in.readLine());
            ArrayList<Integer> qs = new ArrayList<Integer>();
            while (q > 0) {
                qs.add(Integer.parseInt(in.readLine()));
                q--;
            }
            HashSet<Integer> xs = new HashSet<Integer>();
            for (Maker maker : makers) {
                xs.add(maker.low);
                xs.add(maker.high);
            }
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.addAll(xs);
            Collections.sort(list);
            int size = list.size();
            ArrayList<Integer>[] counts = new ArrayList[size - 1];
            for (int i = 0; i < counts.length; i++) {
                counts[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < d; i++) {
                Maker maker = makers[i];
                int lowIndex = Collections.binarySearch(list, maker.low);
                int highIndex = Collections.binarySearch(list, maker.high);
                for (int j = lowIndex; j < highIndex; j++) {
                    counts[j].add(i);
                }
            }
            for (Integer qe : qs) {
                String ans = "";
                int index = Collections.binarySearch(list, qe);
                if (index >= 0) {
                    int c = 0;
                    if (index - 1 >= 0) {
                        c += counts[index - 1].size();
                    }
                    if (index < size - 1) {
                        c += counts[index].size();
                    }
                    if (c == 1) {
                        String name;
                        if (index - 1 >= 0 && counts[index - 1].size() > 0) {
                            name = makers[counts[index - 1].get(0)].name;
                        } else {
                            name = makers[counts[index].get(0)].name;
                        }
                        ans = name;
                    } else {
                        ans = "UNDETERMINED";
                    }
//                    ans = getName(counts, makers, index);
                } else {
                    int ri = -(index + 1);
                    if (ri == size || ri == 0) {
                        ans = "UNDETERMINED";
                    } else {
                        int ci = ri - 1;
                        ans = getName(counts, makers, ci);
                    }
                }
//                if (ans.equals("Morgan")) {
//                    System.out.println();
//                }
                out.append(String.format("%s\n", ans));
            }
            t--;
            if (t != 0) {
                out.append('\n');
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
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
