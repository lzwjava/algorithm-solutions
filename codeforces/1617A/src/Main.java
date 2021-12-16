import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

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
    
    static void swap(StringBuilder sb, int l, int r) {
        char temp = sb.charAt(l);
        sb.setCharAt(l, sb.charAt(r));
        sb.setCharAt(r, temp);
    }

    static void reverse(StringBuilder sb, int l, int r) {
        while (l < r) {
            swap(sb, l, r);
            l++;
            r--;
        }
    }

    static int binarySearch(StringBuilder sb, int l, int r, char val) {
        int index = -1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (sb.charAt(mid) <= val) {
                r = mid - 1;
            } else {
                l = mid + 1;
                if (index == -1 || sb.charAt(index) >= sb.charAt(mid))
                    index = mid;
            }
        }
        return index;
    }

    static boolean nextPermutation(StringBuilder sb) {
        int len = sb.length();
        int i = len - 2;

        while (i >= 0 && sb.charAt(i) >= sb.charAt(i + 1))
            i--;

        if (i < 0)
            return false;
        else {
            int index = binarySearch(sb, i + 1, len - 1, sb.charAt(i));
            swap(sb, i, index);
            reverse(sb, i + 1, len - 1);
            return true;
        }
    }


    boolean isSubSequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        return isSubSequence(str1, str2, m, n);
    }

    boolean isSubSequence(String str1, String str2,
                          int m, int n) {
        if (m == 0)
            return true;
        if (n == 0)
            return false;
        if (str1.charAt(m - 1) == str2.charAt(n - 1))
            return isSubSequence(str1, str2, m - 1, n - 1);
        return isSubSequence(str1, str2, m, n - 1);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            String a = in.readLine();
            String b = in.readLine();
            char[] chs = a.toCharArray();
            Arrays.sort(chs);
            while (true) {
                String ns = new String(chs);
                if (!isSubSequence(b, ns)) {
                    out.append(String.format("%s\n", ns));
                    break;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(chs);
                nextPermutation(sb);
                chs = sb.toString().toCharArray();
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}