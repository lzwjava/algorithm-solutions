import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        HashMap<String,Integer> map = new HashMap<String, Integer>(){
            {
                put("A", 2);
                put("B", 2);
                put("C", 2);
                put("D", 3);
                put("E", 3);
                put("F", 3);
                put("G", 4);
                put("H", 4);
                put("I", 4);
                put("J", 5);
                put("K", 5);
                put("L", 5);
                put("M", 6);
                put("N", 6);
                put("O", 6);
                put("P", 7);
                put("Q", 7);
                put("R", 7);
                put("S", 7);
                put("T", 8);
                put("U", 8);
                put("V", 8);
                put("W", 9);
                put("X", 9);
                put("Y", 9);
                put("Z", 9);  
            }
        };
        while (sc.hasNext()) {
            String s = sc.next();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (!Character.isAlphabetic(ch)) {
                    sb.append(ch);
                } else {
                    Integer v = map.get(ch + "");
                    sb.append(v);
                }
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

        new Main().solve();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
