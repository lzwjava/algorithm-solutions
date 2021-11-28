import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    BufferedReader in;
    PrintWriter out;    
    int n;

    class Division implements Comparable<Division> {
        String numerator;
        String denominator;

        Division() {

        }

        Division(String numerator, String denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        @Override
        public int compareTo(Main.Division o) {
            return numerator.compareTo(o.numerator);
        }
        
    }
    
    ArrayList<Division> divisions;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    String numsToStr(int nums[], int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = end-1; i >=start; i--) {
            sb.append(nums[i]);
        }
        return sb.toString();
    }
    
    void permutation(int nums[], boolean vis[], int i) {
        if (i >= 1) {
            String str2 = numsToStr(nums, 0, i);
            int a2 = Integer.parseInt(str2);
            int a1 = n * a2;
            String str1 = String.format("%d", a1);
            int len = str1.length();
            int min = i < len ? i : len;
            boolean vis1[] = new boolean[10];
            for (int j = 0; j < min; j++) {            
                char ch = str1.charAt(len - 1 - j);
                int num = ch - '0';
                if (vis[num]) {
                    return;
                }
                if (vis1[num]) {
                    return;
                }
                vis1[num] = true;
            }
        }
        if (i == 5) {
            String str2 = numsToStr(nums, 0, i);
            int a2 = Integer.parseInt(str2);
            int a1 = n * a2;
            String str1 = String.format("%d", a1);        
            if (str1.length() > 5 || str1.length() < 4) {
                return;
            }
            if (str1.length() == 4) {
                str1 = "0" + str1;
            }
            int nnums[] = Arrays.copyOf(nums, 10);            
            for (int j = 0; j < str1.length(); j++) {
                char ch = str1.charAt(j);
                int num = ch - '0';
                nnums[5 + j] = num;
            }
            boolean vis1[] = new boolean[10];
            boolean ok = true;
            for (int j = 0; j < nnums.length; j++) {
                int nnum = nnums[j];
                if (vis1[nnum]) {
                    ok = false;
                    break;
                } else {
                    vis1[nnum] = true;
                }
            }
            if (ok) {
                Division d = new Division(str1, str2);
                divisions.add(d);
            }
            return;
        }
        for (int j = 0; j <= 9; j++) {
            if (!vis[j]) {
                vis[j] = true;
                nums[i] = j;
                permutation(nums, vis, i + 1);
                vis[j] = false;
            }            
        }
    }
   
    void solve() throws IOException {
        boolean first = true;
        while (true) {
            n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int nums[] = new int[5];
            boolean vis[] = new boolean[10];
            if (first) {
                first = false;
            } else {
                out.append('\n');
            }
            divisions = new ArrayList<>();
            permutation(nums, vis, 0);
            if (divisions.size() > 0) {
                Collections.sort(divisions);
                for (Division d : divisions) {
                    out.append(String.format("%s / %s = %d\n", d.numerator, d.denominator, n));                    
                }
            } else {
                out.append(String.format("There are no solutions for %d.\n", n));
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
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
