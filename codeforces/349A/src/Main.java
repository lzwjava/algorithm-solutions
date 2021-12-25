import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    Map<Integer, Integer> map;

    int getBill(int bill) {
        Integer c = map.get(bill);
        if (c == null) {
            c = 0;
        }
        return c;
    }

    void increase(int bill) {
        int c = getBill(bill);
        c++;
        map.put(bill, c);
    }

    void decrese(int bill) {
        decrese(bill, 1);
    }

    void decrese(int bill, int d) {
        int c = getBill(bill);
        c -= d;
        map.put(bill, c);
    }

    void permutation(List<Integer> bills, List<Item> result, int[] cs, int cur, int m, int sum, int target) {
        if (sum > target) {
            return;
        }
        if (cur == m) {
            if (sum == target) {
                result.add(new Item(sum, cs.clone()));
            }
            return;
        }
        int bill = bills.get(cur);
        int c = map.get(bill);
        for (int i = 0; i <= c; i++) {
            int v = i * bill;
            cs[cur] = i;
            permutation(bills, result, cs, cur + 1, m, sum + v, target);
        }
    }

    class Item implements Comparable<Item> {
        int sum;
        int[] cs;

        Item(int sum, int[] cs) {
            this.sum = sum;
            this.cs = cs;
        }

        @Override
        public int compareTo(Item o) {
            if (sum != o.sum) {
                return Integer.compare(sum, o.sum);
            } else {
                int m = cs.length;
                for (int i = m - 1; i >= 0; i--) {
                    if (cs[i] != o.cs[i]) {
                        return Integer.compare(cs[i], o.cs[i]);
                    }
                }
            }
            return 0;
        }
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int ticket = 25;
        map = new HashMap<>();
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(st.nextToken());
            int change = v - ticket;
            if (change == 0) {
                increase(v);
            } else {
                int c = getBill(change);
                if (c > 0) {
                    decrese(change);
                    increase(v);
                } else {
                    if (change == 75) {
                        if (getBill(25) >= 1 && getBill(50) >= 1) {
                            decrese(25);
                            decrese(50);
                        } else if (getBill(25) >= 3) {
                            decrese(25, 3);
                        } else {
                            ok = false;
                            break;
                        }
                    } else if (change == 25) {
                        if (getBill(25) >= 1) {
                            decrese(25);
                        } else {
                            ok = false;
                            break;
                        }
                    }
                    increase(v);
                }
            }
        }
        if (ok) {
            out.append("YES\n");
        } else {
            out.append("NO\n");
        }
    }

}