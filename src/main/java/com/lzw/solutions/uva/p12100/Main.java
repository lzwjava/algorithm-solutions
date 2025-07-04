package com.lzw.solutions.uva.p12100;

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
            PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
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

        new Main().work();
    }
}
