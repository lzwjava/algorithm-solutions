import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    String convert(String str) {
        if (str.length() > 7) {
            int len = str.length();
            String str1 = str.substring(0, len - 7);
            String str2 = str.substring(len - 7);
            return convert(str1) + " kuti" + convert(str2);
        }
        int n = Integer.parseInt(str);
        int lakh = n / 100000;
        n %= 100000;

        int hajar = n / 1000;
        n %= 1000;

        int shata = n / 100;
        n %= 100;

        StringBuilder sb = new StringBuilder();
        if (lakh != 0) {
            sb.append(String.format(" %d lakh", lakh));
        }
        if (hajar != 0) {
            sb.append(String.format(" %d hajar", hajar));
        }
        if (shata != 0) {
            sb.append(String.format(" %d shata", shata));
        }
        if (n != 0) {
            sb.append(String.format(" %d", n));
        }
        return sb.toString();
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            String ans;
            if (line.equals("0")) {
                ans = " 0";
            } else {
                ans = convert(line);
            }
            out.append(String.format("%4d.%s\n", caseNum, ans));
            caseNum++;
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
