import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    class Book {
        String title;
        String author;

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Book)) {
                return false;
            }
            Book b = (Book) obj;
            if (title.equals(b.title) && author.equals(b.author)) {
                return true;
            } else {
                return false;
            }
        }
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();
        HashMap<String, Book> cachedBooks = new HashMap<>();
        while (true) {
            String record = sc.nextLine();
            if (record.equals("END")) {
                break;
            }
            int begin = record.indexOf("\"");
            int end = record.lastIndexOf("\"");
            String title = record.substring(begin + 1, end);
            String author = record.substring(end +"\" by".length()).trim();
            Book b = new Book();
            b.title = title;
            b.author = author;
            books.add(b);
            cachedBooks.put(title, b);
        }
        ArrayList<Book> shelve = new ArrayList<>();
        shelve.addAll(books);

        ArrayList<Book> borrowed = new ArrayList<>();
        ArrayList<Book> returned = new ArrayList<>();        
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
                Book b = cachedBooks.get(title);
                if (command.equals("BORROW")) {
                    assert (shelve.contains(b));
                    shelve.remove(b);
                    borrowed.add(b);
                } else if (command.equals("RETURN")) {
                    assert (borrowed.contains(b));
                    returned.add(b);
                } else {
                    assert (false);
                }
            } else {
                String command = record;
                assert (command.equals("SHELVE"));
                returned.sort(new Comparator<Book>(){
                    @Override
                    public int compare(Main.Book o1, Main.Book o2) {
                        if (!o1.author.equals(o2.author)) {
                            return o1.author.compareTo(o2.author);
                        } else {
                            return o1.title.compareTo(o2.title);
                        }
                    }
                });            
                for (int i = 0; i < returned.size(); i++) {
                    Book b = returned.get(i);
                    int bIndex = books.indexOf(b);

                    int beforeGap = books.size();
                    Book beforeBook = null;
                    int beforeIndex = 0;

                    for (int j = 0; j < shelve.size(); j++) {
                        Book sb = shelve.get(i);
                        int origin = books.indexOf(sb);
                        if (origin < bIndex && bIndex - origin < beforeGap) {
                            beforeGap = bIndex - origin;
                            beforeIndex = origin;
                            beforeBook = sb;
                        }
                    }
                    if (beforeBook == null) {
                        System.out.println(String.format("Put \"%s\" first", b.title));
                        shelve.add(b);
                    } else {
                        String beforeTitle = books.get(beforeIndex).title;
                        System.out.println(String.format("Put \"%s\" after \"%s\"", b.title, beforeTitle));
                        shelve.add(beforeIndex, b);
                    }
                }
                System.out.println("END");
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
