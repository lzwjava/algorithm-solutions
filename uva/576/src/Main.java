import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    boolean vowel(char ch) {
        return "aeiouy".indexOf(ch) >= 0;
    }

    int syllables(String word) {
        char lastCh = ' ';
        int cnt = 0;
        word = word + "s";
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (i > 0) {
                boolean lastVowel = vowel(lastCh);
                if (!vowel(ch) && lastVowel) {
                    cnt++;
                }
            }
            lastCh = ch;
        }
        return cnt;
    }

    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            if (s.equals("e/o/i")) {
                break;
            }
            if (s.isEmpty()) {
                continue;
            }
            String[] lines = s.split("/");
            int[] nums = new int[]{5, 7, 5};
            boolean ok = true;
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                String[] words = line.split("\\s+");
                int cnt = 0;
                for (String word : words) {
                    cnt += syllables(word);
                }
                if (nums[i] != cnt) {
                    out.append(String.format("%d\n", i + 1));
                    ok = false;
                    break;
                }
            }
            if (ok) {
                out.append(String.format("Y\n"));
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
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
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
