package com.lzw.solutions.uva.p1368;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    int indexOfCode(char code) {
        HashMap<Character, Integer> maps = new HashMap<>();
        maps.put('A', 0);
        maps.put('C', 1);
        maps.put('G', 2);
        maps.put('T', 3);
        Integer indexValue = maps.get(code);
        return indexValue != null ? indexValue.intValue() : -1;
    }

    void work() {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            String[] seqs = new String[m];
            for (int j = 0; j < m; j++) {
                seqs[j] = sc.next();
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                int[] times = new int[4];
                int maxTime = 0;
                int maxSeq = -1;
                int maxIndex = -1;
                for (int k = 0; k < m; k++) {
                    int index = indexOfCode(seqs[k].charAt(j));
                    times[index]++;
                    if (times[index] > maxTime || (times[index] == maxTime && maxIndex > index)) {
                        maxTime = times[index];
                        maxSeq = k;
                        maxIndex = index;
                    }
                }
                sb.append(seqs[maxSeq].charAt(j));
            }

            String consensus = sb.toString();
            int consensusError = 0;
            for (int j = 0; j < m; j++) {
                int dist = 0;
                for (int k = 0; k < n; k++) {
                    if (consensus.charAt(k) != seqs[j].charAt(k)) {
                        dist++;
                    }
                }
                consensusError += dist;
            }

            System.out.println(consensus);
            System.out.println(consensusError);
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().work();
    }
}
