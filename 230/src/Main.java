import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
   
    void work() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> titles = new ArrayList<>();        
        while (true) {
            String title = sc.nextLine();
            if (title.equals("END")) {
                break;
            }
            int begin = title.indexOf("\"");
            int end = title.lastIndexOf("\"");
            String name = title.substring(begin + 1, end);
            titles.add(name);
        }
        ArrayList<String> shelve = new ArrayList<>();
        shelve.addAll(titles);

        ArrayList<String> borrowed = new ArrayList<>();
        ArrayList<String> returned = new ArrayList<>();        
        while (true) {
            String record = sc.nextLine();
            if (record.equals("END")) {
                break;
            }
            int begin = record.indexOf("\"");
            int end = record.lastIndexOf("\"");            
            if (begin != -1) {
                String command = record.substring(0, begin).trim();
                String title = record.substring(begin + 1, end);
                if (command.equals("BORROW")) {
                    assert (shelve.contains(title));
                    shelve.remove(title);
                    borrowed.add(title);
                } else if (command.equals("RETURN")) {
                    assert (borrowed.contains(title));
                    returned.add(title);
                } else {
                    assert (false);
                }
            } else {
                String command = record;
                assert (command.equals("SHELVE"));
                returned.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int p1 = titles.indexOf(o1);
                        int p2 = titles.indexOf(o2);
                        return p1 - p2;
                    }                                        
                });
                for (int i = 0; i < returned.size(); i++) {
                    String title = returned.get(i);
                    int origin = titles.indexOf(title);
                    if (origin == 0) {
                        System.out.println(String.format("Put \"%s\" first", title));
                        shelve.add(title);
                    } else {
                        String beforeTitle = titles.get(origin - 1);
                        System.out.println(String.format("Put \"%s\" after \"%s\"", title, beforeTitle));
                        shelve.add(title);
                    }
                }
                returned.clear();
            }
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
