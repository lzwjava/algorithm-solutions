import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        String[] keyboard = new String[3];
        keyboard[0] = "qwertyuiop[]\\";
        keyboard[1] = "asdfghjkl;'";
        keyboard[2] = "zxcvbnm,./";

        String s = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isAlphabetic(ch)) {
                ch = Character.toLowerCase(ch);
            }
            if (ch == ' ') {
                sb.append(ch);
            } else {
                for (int j = 0; j < keyboard.length; j++) {
                    int p = keyboard[j].indexOf(ch);
                    if (p == -1) {
                        continue;
                    } else {
                        assert (p >= 2);
                        sb.append(keyboard[j].charAt(p - 2));
                    }
                }
            }
        }
        System.out.println(sb.toString());
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        new Main().solve();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
