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
    int maxd;

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
        int p = 0;
        for (int i = 0; i < list.size(); i++) {
            p = p * 10 + list.get(i);
        }
        return p;
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
            if (leadingZero(cur, x, lens)) {
                continue;
            }
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

    boolean permutation(List<Integer> template, List<Integer> changed, int[] lens, int d, int cur, int n) {
        if (d >= maxd) {
            boolean ok = judge(changed, lens);
            if (ok) {
                if (ans == null) {
                    ans = changed;
                    return true;
                }
            }
            return false;
        }
        if (cur == n) {
            return false;
        }
        for (int i = -1; i <= 9; i++) {
            if (leadingZero(cur, i, lens)) {
                continue;
            }
            if (template.get(cur) == i) {
                boolean ok = permutation(template, changed, lens, d, cur + 1, n);
                if (ok) {
                    return true;
                }
            } else {
                changed.set(cur, i);
                boolean ok = permutation(template, changed, lens, d + 1, cur + 1, n);
                if (ok) {
                    return true;
                }
                changed.set(cur, template.get(cur));
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
            for (int i = 0; i <= total; i++) {
                maxd = i;
                List<Integer> changed = new ArrayList<>(list);
                boolean ok = permutation(list, changed, lens, 0, 0, list.size());
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