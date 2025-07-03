package com.lzw.solutions.lintcode.p2462;

import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {

        try {
            String in_file_dir = new File(args[0]).getParent();
            String out_file_dir = new File(args[1]).getParent();

            List<String> in_file_paths = new ArrayList<>();
            File[] all_file_paths = new File(in_file_dir).listFiles();
            for (File file : all_file_paths) {
                if (file.getPath().endsWith(".in")) {
                    in_file_paths.add(file.getPath());
                }
            }
            Collections.sort(in_file_paths, new Comparator<String>() {
                @Override
                public int compare(String f1, String f2) {
                    int f1num = Integer.parseInt(new File(f1).getName().replace(".in", ""));
                    int f2num = Integer.parseInt(new File(f2).getName().replace(".in", ""));
                    return f1num - f2num;
                }
            });

            for (String in_file_path : in_file_paths) {
                Scanner in = new Scanner(new FileReader(in_file_path));
                PrintWriter writer = new PrintWriter(
                        out_file_dir + "/" + new File(in_file_path).getName().replace(".in", ".out"), "UTF-8");

                // Editable
                int out = 0;

                List<Integer> list = new ArrayList<>();

                String name = in.nextLine();
                int n = Integer.parseInt(name.substring(name.indexOf("(") + 1, name.indexOf(")")));
                int zz = 0;
                while (in.hasNext()) {
                    String str = in.next();
                    String s = "enqueue";
                    if (str.indexOf(s) != -1) {
                        list.add(Integer.parseInt(str.substring(s.length() + 1, str.length() - 1)));
                    } else {
                        out++;
                    }
                }

                final BoundedBlockingQueue bd = new BoundedBlockingQueue(n);

                final int num = out;

                Thread t1 = new Thread(() -> {
                    for (int a : list) {
                        bd.enqueue(a);
                    }
                });

                Thread t2 = new Thread(() -> {
                    for (int i = 0; i < num; i++) {
                        int x = bd.dequeue();
                        writer.println(x);
                    }
                });

                t1.start();
                t2.start();

                t1.join();
                t2.join();

                int size = bd.size();
                writer.println(size);

                // Editable end
                // stdout.close();
                writer.close();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
