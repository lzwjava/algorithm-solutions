import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
   
    void work() {
        Scanner sc = new Scanner(System.in);
        int caseNum = 1;
        while (sc.hasNextLine()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                list.add(i);
            }
            for (int i = 0; i < m; i++) {
                int command = sc.nextInt();
                if (command == 4) {
                    LinkedList<Integer> reversed = new LinkedList<>();
                    while (!list.isEmpty()) {
                        Integer head = list.removeLast();
                        reversed.add(head);
                    }
                    list = reversed;
                } else {
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    int xIndex = list.indexOf(x);
                    int yIndex = list.indexOf(y);
                    if (command == 1) {
                        list.add(yIndex, Integer.valueOf(x));
                        list.remove(xIndex);
                    } else if (command == 2) {
                        list.add(yIndex + 1, Integer.valueOf(x));
                        list.remove(xIndex);
                    } else {
                        list.remove(yIndex);
                        list.add(yIndex, Integer.valueOf(x));

                        list.remove(xIndex);
                        list.add(xIndex, Integer.valueOf(y));
                    }
                }
            }
            Iterator<Integer> iterator = list.iterator();
            int i = 1;
            long sum = 0;
            while (iterator.hasNext()) {
                if (i % 2 == 1) {
                    sum += iterator.next();
                } else {
                    iterator.next();
                }
                i++;
            }
            System.out.print(String.format("Case %d: ", caseNum));
            System.out.println(sum);
            caseNum++;
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
