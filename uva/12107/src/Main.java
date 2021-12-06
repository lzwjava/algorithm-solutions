import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int cnt;
    List<Integer> ans;

    boolean judge(List<Integer> template, int[] lens) {
        cnt = 0;
        int twoLen = 0;
        for (int i = 0; i < 2; i++) {
            twoLen += lens[i];
        }
        List<Integer> nums = new ArrayList<>();
        judge(template, nums, lens, 0, twoLen);
        return cnt == 1;
    }

    int genNum(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int x : list) {
            sb.append(x);
        }
        String s = sb.toString();
        int v = Integer.parseInt(s);
        String vs = String.format("%d", v);
        if (vs.length() != s.length()) {
            return -1;
        } else {
            return v;
        }
    }

    void judge(List<Integer> template, List<Integer> nums, int[] lens, int cur, int n) {
        if (cnt > 1) {
            return;
        }
        if (cur == n) {
            int[] as = new int[3];
            int p = 0;
            for (int i = 0; i < 2; i++) {
                int len = lens[i];
                List<Integer> subList = nums.subList(p, p + len);
                int v = genNum(subList);
                if (v == -1) {
                    return;
                }
                as[i] = v;
                p += len;
            }
            List<Integer> lastList = template.subList(p, template.size());
            as[2] = as[0] * as[1];
            if (match(lastList, as[2])) {
                cnt++;
            }
            return;
        }
        int v = template.get(cur);
        List<Integer> ps = new ArrayList<>();
        if (v == -1) {
            for (int i = 0; i <= 9; i++) {
                ps.add(i);
            }
        } else {
            ps.add(v);
        }
        for (int x : ps) {
            nums.add(x);
            judge(template, nums, lens, cur + 1, n);
            nums.remove(nums.size() - 1);
        }
    }

    boolean match(List<Integer> lastList, int a) {
        String s = String.format("%d", a);
        if (s.length() != lastList.size()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int digit = ch - '0';
            int lastDigit = lastList.get(i);
            if (lastDigit != -1 && lastDigit != digit) {
                return false;
            }
        }
        return true;
    }

    boolean permutation(List<Integer> template, List<Integer> changed, List<Integer> orders,
                        int[] lens, int[] pos, int cur, int n) {
        if (cur == n) {
            boolean ok = judge(changed, lens);
            if (ok) {
                if (ans == null) {
                    ans = changed;
                    return true;
                }
            }
            return false;
        }
        int st;
        if (cur == 0) {
            st = 0;
        } else {
            st = pos[cur - 1] + 1;
        }
        for (int i = st; i < orders.size(); i++) {
            int o = orders.get(i);
            for (int j = -1; j <= 9; j++) {
                if (template.get(o) == j) {
                    continue;
                }
                if (leadingZero(o, j, lens)) {
                    continue;
                }
                pos[cur] = i;
                changed.set(o, j);
                boolean ok = permutation(template, changed, orders, lens, pos, cur + 1, n);
                if (ok) {
                    return true;
                }
                changed.set(o, template.get(o));
            }
        }
        return false;
    }

    boolean leadingZero(int pos, int value, int[] lens) {
        if (value != 0) {
            return false;
        }
        int p = 0;
        for (int i = 0; i < lens.length; i++) {
            if (pos == p) {
                return true;
            }
            p += lens[i];
        }
        return false;
    }

    List<Integer> getPermutationOrder(List<Integer> list) {
        List<Integer> orders = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i);
            if (x != -1) {
                orders.add(i);
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            int x = list.get(i);
            if (x == -1) {
                orders.add(i);
            }
        }
        return orders;
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            String token = st.nextToken();
            if (token.equals("0")) {
                break;
            }
            List<String> strs = new ArrayList<>();
            strs.add(token);
            for (int i = 0; i < 2; i++) {
                strs.add(st.nextToken());
            }
            List<Integer> list = new ArrayList<>();
            int[] lens = new int[3];
            for (int i = 0; i < strs.size(); i++) {
                String str = strs.get(i);
                lens[i] = str.length();
                for (int j = 0; j < str.length(); j++) {
                    char ch = str.charAt(j);
                    int v;
                    if (ch == '*') {
                        v = -1;
                    } else {
                        v = ch - '0';
                    }
                    list.add(v);
                }
            }
            int total = 0;
            for (int i = 0; i < 3; i++) {
                total += lens[i];
            }
            ans = null;
            List<Integer> orders = getPermutationOrder(list);
            for (int i = 0; i <= total; i++) {
                List<Integer> changed = new ArrayList<>(list);
                int[] pos = new int[i];
                boolean ok = permutation(list, changed, orders, lens, pos, 0, i);
                if (ok) {
                    String gen = genStr(ans, lens);
                    out.append(String.format("Case %d: %s\n", caseNum, gen));
                    break;
                }
            }
            caseNum++;
        }
    }

    String genStr(List<Integer> changed, int[] lens) {
        StringBuilder sb = new StringBuilder();
        int p = 0;
        for (int i = 0; i < lens.length; i++) {
            if (i != 0) {
                sb.append(' ');
            }
            List<Integer> subList = changed.subList(p, p + lens[i]);
            String part = genPartStr(subList);
            sb.append(part);
            p += lens[i];
        }
        return sb.toString();
    }

    String genPartStr(List<Integer> subList) {
        StringBuilder sb = new StringBuilder();
        for (int x : subList) {
            char c;
            if (x == -1) {
                c = '*';
            } else {
                c = (char) ('0' + x);
            }
            sb.append(c);
        }
        return sb.toString();
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