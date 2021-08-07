import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    char numToChar(int i) {
        switch (i) {
            case 0:
                return 'B';
            case 1:
                return 'G';
            case 2:
                return 'C';
        }
        return ' ';
    }

    String numToColor(int i, int j, int k) {
        StringBuilder sb = new StringBuilder();
        sb.append(numToChar(i));
        sb.append(numToChar(j));
        sb.append(numToChar(k));
        return sb.toString();
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int nums[][] = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    nums[i][j] = sc.nextInt();
                }
            }
            int minMove = Integer.MAX_VALUE;
            String minColor = "";
            // B 0 G 1 C 2
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j) {
                        continue;
                    }
                    int k = 3 - i - j;
                    // System.out.println(i+" "+ j+" "+k);
                    int move = 0;

                    for (int y = 0; y < 3; y++) {
                        if (y != i) {
                            move += nums[0][y];
                        }
                    }

                    for (int y = 0; y < 3; y++) {
                        if (y != j) {
                            move += nums[1][y];
                        }
                    }

                    for (int y = 0; y < 3; y++) {
                        if (y != k) {
                            move += nums[2][y];
                        }
                    }

                    if (move < minMove) {
                        String color = numToColor(i, j, k);
                        minMove = move;
                        minColor = color;
                    } else if (move == minMove) {
                        String color = numToColor(i, j, k);
                        if (color.compareTo(minColor) < 0) {
                            minColor = color;
                        }
                    }
                }
            }
            System.out.println(String.format("%s %d", minColor, minMove));
        }
        sc.close();
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

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
