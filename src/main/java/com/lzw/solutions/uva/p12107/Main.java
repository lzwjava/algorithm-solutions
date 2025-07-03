package com.lzw.solutions.uva.p12107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
    int[] ans;
    int maxd;

    boolean judge(int[] template, int[] lens) {
        cnt = 0;
        int twoLen = 0;
        for (int i = 0; i < 2; i++) {
            twoLen += lens[i];
        }
        int[] orders = new int[twoLen];
        int p = 0;
        orders[p++] = lens[0] - 1;
        orders[p++] = lens[0] + lens[1] - 1;
        for (int i = 0; i < lens[0] - 1; i++) {
            orders[p++] = i;
        }
        for (int i = 0; i < lens[1] - 1; i++) {
            orders[p++] = lens[0] + i;
        }
        int[] nums = new int[template.length];
        judge(template, nums, lens, orders, 0, twoLen);
        return cnt == 1;
    }

    int genNum(int[] list) {
        int p = 0;
        for (int i = 0; i < list.length; i++) {
            p = p * 10 + list[i];
        }
        return p;
    }

    void judge(int[] template, int[] nums, int[] lens, int[] orders, int cur, int n) {
        if (cnt > 1) {
            return;
        }
        if (cur == 2) {
            int x = 1;
            for (int i = 0; i < cur; i++) {
                int pos = orders[i];
                x *= nums[pos];
            }
            int digit = x % 10;
            int tdigit = template[template.length - 1];
            if (tdigit != -1 && digit != tdigit) {
                return;
            }
        }
        if (cur == n) {
            int[] as = new int[3];
            int p = 0;
            for (int i = 0; i < 2; i++) {
                int len = lens[i];
                int[] subList = Arrays.copyOfRange(nums, p, p + len);
                int v = genNum(subList);
                as[i] = v;
                p += len;
            }
            int[] lastList = Arrays.copyOfRange(template, p, template.length);
            as[2] = as[0] * as[1];
            if (match(lastList, as[2])) {
                cnt++;
            }
            return;
        }
        int pos = orders[cur];
        int v = template[pos];
        List<Integer> ps = new ArrayList<>();
        if (v == -1) {
            for (int i = 0; i <= 9; i++) {
                ps.add(i);
            }
        } else {
            ps.add(v);
        }
        for (int x : ps) {
            if (leadingZero(pos, x, lens)) {
                continue;
            }
            nums[pos] = x;
            judge(template, nums, lens, orders, cur + 1, n);
            nums[pos] = template[pos];
        }
    }

    boolean match(int[] lastList, int a) {
        String s = String.format("%d", a);
        if (s.length() != lastList.length) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int digit = ch - '0';
            int lastDigit = lastList[i];
            if (lastDigit != -1 && lastDigit != digit) {
                return false;
            }
        }
        return true;
    }

    boolean permutation(int[] template, int[] changed, int[] lens, int d, int cur, int n) {
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
            if (template[cur] == i) {
                boolean ok = permutation(template, changed, lens, d, cur + 1, n);
                if (ok) {
                    return true;
                }
            } else {
                changed[cur] = i;
                boolean ok = permutation(template, changed, lens, d + 1, cur + 1, n);
                if (ok) {
                    return true;
                }
                changed[cur] = template[cur];
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
            int m = 0;
            for (int i = 0; i < 3; i++) {
                m += strs.get(i).length();
            }
            int[] list = new int[m];
            int[] lens = new int[3];
            int p = 0;
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
                    list[p++] = v;
                }
            }
            int total = 0;
            for (int i = 0; i < 3; i++) {
                total += lens[i];
            }
            ans = null;
            for (int i = 0; i <= total; i++) {
                maxd = i;
                int[] changed = list.clone();
                boolean ok = permutation(list, changed, lens, 0, 0, list.length);
                if (ok) {
                    String gen = genStr(ans, lens);
                    out.append(String.format("Case %d: %s\n", caseNum, gen));
                    break;
                }
            }
            caseNum++;
        }
    }

    String genStr(int[] changed, int[] lens) {
        StringBuilder sb = new StringBuilder();
        int p = 0;
        for (int i = 0; i < lens.length; i++) {
            if (i != 0) {
                sb.append(' ');
            }
            int[] subList = Arrays.copyOfRange(changed, p, p + lens[i]);
            String part = genPartStr(subList);
            sb.append(part);
            p += lens[i];
        }
        return sb.toString();
    }

    String genPartStr(int[] sub) {
        StringBuilder sb = new StringBuilder();
        for (int x : sub) {
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
