import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    String convertToBinaryString(String address) {
        StringBuilder sb = new StringBuilder();
        String parts[] = address.split("\\.");
        for (int i = 0; i < parts.length; i++) {
            Integer num = Integer.parseInt(parts[i]);
            String s = Integer.toBinaryString(num);
            while (s.length() < 8) {
                s = "0" + s;
            }
            sb.append(s);
        }
        return sb.toString();
    }

    int lengthOfLongestCommonSubstring(String[] strings, int n) {
        int i = 0;
        for (; i < n; i++) {
            char ch = strings[0].charAt(i);
            boolean ok = true;
            for (int j = 1; j < strings.length; j++) {
                if (strings[j].charAt(i) != ch) {
                    ok = false;
                    break;
                }
            }
            if (!ok) {
                break;
            }
        }
        return i;
    }
    
    String convertToAddress(String binary) {
        StringBuilder address = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String sub = binary.substring(i * 8, (i + 1) * 8);
            int num = Integer.parseInt(sub, 2);
            if (i != 0) {
                address.append(".");
            }
            address.append(Integer.toString(num));
        }
        return address.toString();
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int m = sc.nextInt();
            String addresses[] = new String[m];
            String binaryAddresses[] = new String[m];
            for (int i = 0; i < m; i++) {
                addresses[i] = sc.next();
                binaryAddresses[i] = convertToBinaryString(addresses[i]);
            }
            int len = lengthOfLongestCommonSubstring(binaryAddresses, 32);
            int rest = 32 - len;
            
            StringBuilder mask = new StringBuilder();
            for (int i = 0; i < len; i++) {
                mask.append("1");
            }
            for (int i = 0; i < rest; i++) {
                mask.append("0");
            }

            StringBuilder networkAddress = new StringBuilder();
            networkAddress.append(binaryAddresses[0].substring(0, len));
            for (int i = 0; i < rest; i++) {
                networkAddress.append("0");
            }

            System.out.println(convertToAddress(networkAddress.toString()));
            System.out.println(convertToAddress(mask.toString()));
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
