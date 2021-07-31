import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    enum Pos {
        HOME,
        END
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            LinkedList<Character> charList = new LinkedList<>();
            Pos pos = Pos.END;
            int home = 0;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == '[') {
                    pos = Pos.HOME;
                    home = 0;
                } else if (ch == ']') {
                    pos = Pos.END;
                    home = 0;
                } else {
                    if (pos == Pos.HOME) {
                        charList.add(home, ch);
                        home++;
                    } else {
                        charList.addLast(ch);
                    }
                }
            }
            Iterator<Character> iterator = charList.iterator();
            StringBuilder sb = new StringBuilder();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
            }
            System.out.println(sb.toString());
        }
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

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
