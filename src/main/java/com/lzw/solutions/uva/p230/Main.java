package com.lzw.solutions.uva.p230;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    class Book implements Comparable<Book> {
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

        @Override
        public int compareTo(Main.Book o) {
            Book b = (Book) o;
            if (!author.equals(b.author)) {
                return author.compareTo(b.author);
            } else {
                return title.compareTo(b.title);
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
            String author = record.substring(end + "\" by".length()).trim();
            Book b = new Book();
            b.title = title;
            b.author = author;
            books.add(b);
            cachedBooks.put(title, b);
        }
        Collections.sort(books);

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
                Collections.sort(returned);
                for (int i = 0; i < returned.size(); i++) {
                    Book b = returned.get(i);

                    int j = 0;
                    for (j = 0; j < shelve.size(); j++) {
                        Book sb = shelve.get(j);
                        // if j is smaller than b, then continue
                        if (sb.compareTo(b) > 0) {
                            break;
                        }
                    }
                    // book j bigger than b
                    boolean first;
                    String beforeTitle = "";
                    if (j == 0) {
                        first = true;
                    } else {
                        first = false;
                        beforeTitle = shelve.get(j - 1).title;
                    }
                    if (first) {
                        System.out.println(String.format("Put \"%s\" first", b.title));
                        shelve.add(0, b);
                    } else {
                        System.out.println(String.format("Put \"%s\" after \"%s\"", b.title, beforeTitle));
                        shelve.add(j, b);
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
