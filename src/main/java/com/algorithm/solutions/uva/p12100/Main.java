package com.algorithm.solutions.uva.p12100;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    
    class Job {
        int pos;
        int priority;
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0) {
            int n, m;
            n = sc.nextInt();
            m = sc.nextInt();
            ArrayBlockingQueue<Job> jobs = new ArrayBlockingQueue<>(n);
            PriorityQueue <Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }                
            });
            for (int i = 0; i < n; i++) {
                Job job = new Job();
                job.pos = i;
                job.priority = sc.nextInt();
                jobs.add(job);
                pq.add(job.priority);
            }
            int minutes = 0;
            while (true) {
                Job j = jobs.peek();
                if (j.priority == pq.peek()) {
                    minutes++;
                    jobs.poll();
                    pq.poll();                    
                    if (j.pos == m) {
                        System.out.println(minutes);
                        break;
                    } 
                } else {
                    jobs.poll();
                    jobs.add(j);
                }
            }
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

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
