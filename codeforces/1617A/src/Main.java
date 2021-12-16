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

    boolean prevPermutation(char[] str) {

        // Find index of the last
        // element of the string
        int n = str.length - 1;

        // Find largest index i such
        // that str[i - 1] > str[i]
        int i = n;
        while (i > 0 && str[i - 1] <= str[i]) {
            i--;
        }

        // if string is sorted in
        // ascending order we're
        // at the last permutation
        if (i <= 0) {
            return false;
        }

        // Note - str[i..n] is sorted
        // in ascending order Find
        // rightmost element's index
        // that is less than str[i - 1]
        int j = i - 1;
        while (j + 1 <= n && str[j + 1] <= str[i - 1]) {
            j++;
        }

        // Swap character at i-1 with j
        swap(str, i - 1, j);

        // Reverse the substring [i..n]
        StringBuilder sb
            = new StringBuilder(String.valueOf(str));
        sb.reverse();
        str = sb.toString().toCharArray();

        return true;
    }

    static String swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return String.valueOf(ch);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            String a = in.readLine();
            String b = in.readLine();
            char[] chs = a.toCharArray();
            while (true) {
                prevPermutation(chs);
                String ns = new String(chs);
                if (!ns.contains(b)) {
                    out.append(String.format("%s\n", ns));
                    break;
                }
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