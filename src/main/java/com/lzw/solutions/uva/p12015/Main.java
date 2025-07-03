package com.lzw.solutions.uva.p12015;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    class Site implements Comparable<Site> {
        int index;
        String url;
        int relevance;

        @Override
        public int compareTo(Main.Site o) {
            if (relevance != o.relevance) {
                return o.relevance - relevance;
            } else {
                return index - o.index;
            }
        }
    }
   
    void solve() {
        Scanner sc = new Scanner(System.in);    
        int t = sc.nextInt();
        int caseNum = 1;
        while (t > 0) {
            ArrayList<Site> sites = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                String url = sc.next();
                int relevance = sc.nextInt();
                Site site = new Site();
                site.index = i;
                site.url = url;
                site.relevance = relevance;
                sites.add(site);
            }
            Collections.sort(sites);
            System.out.println(String.format("Case #%d:", caseNum));
            int maxRelevance = sites.get(0).relevance;
            for (int i = 0; i < sites.size(); i++) {
                Site s = sites.get(i);
                if (s.relevance == maxRelevance) {
                    System.out.println(s.url);
                }
            }
            caseNum++;
            t--;
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
