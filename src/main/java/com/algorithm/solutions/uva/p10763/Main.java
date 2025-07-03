package com.algorithm.solutions.uva.p10763;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
   
    void work() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            HashMap<String, Integer> routes = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int o = sc.nextInt();
                int t = sc.nextInt();
                String key = o + "," + t;
                Integer count = routes.get(key);
                if (count == null) {
                    count = 0;
                }
                count++;
                routes.put(key, count);
            }
            boolean ok = true;
            ArrayList<String> visited = new ArrayList<>();
            for (String route : routes.keySet()) {
                if (visited.contains(route)) {
                    continue;
                }
                int count = routes.get(route);
                int middle = route.indexOf(",");
                String reversed =route.substring(middle + 1)  + "," + route.substring(0, middle);
                Integer reversedCount = routes.get(reversed);
                if (reversedCount == null || count != reversedCount) {
                    ok = false;
                    break;
                }
                visited.add(route);
                visited.add(reversed);
            }
            if (ok) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("2.in");
            // outStream = new PrintStream("2.out");
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
