package com.lzw.solutions.lintcode.p2467;

import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            String inputPath = args[0];
            String outputPath = args[1];
            Scanner in = new Scanner(new FileReader(inputPath));
            PrintStream ps = new PrintStream(outputPath);

            // Editable
            int out = 0;
            List<Integer> list = new ArrayList<>();

            String name = in.nextLine();
            int n = Integer.parseInt(name.substring(name.indexOf("(") + 1, name.indexOf(")")));
            while (in.hasNext()) {
                String str = in.next();
                String s = "push";
                if (str.indexOf(s) != -1) {
                    list.add(Integer.parseInt(str.substring(s.length() + 1, str.length() - 1)));
                } else {
                    out++;
                }
            }
            final BoundedBlockingStack bd = new BoundedBlockingStack(n);
            List<Integer> ls1 = new ArrayList<>();
            final int num = out;

            Thread t1 = new Thread(() -> {
                for (int a : list) {
                    ls1.add(a);
                    bd.push(a);
                }
            });

            Thread t2 = new Thread(() -> {
                try {
                    for (int i = 0; i < num; i++) {
                        int x = bd.pop();
                        if (!ls1.contains((Object) x)) {
                            throw new Exception("The {element} you pop is not on the stack.");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();
            if (bd.size() != (list.size() - out)) {
                throw new Exception("Wrong stack size returned.");
            } else {
                ps.print("accepted");
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
