package com.lzw.solutions.uva.p10205;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int posInOrders(int[] orders, int v) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == v) {
                return i;
            }
        }
        return -1;
    }

    int[] shuffle(int[] nums, int[] orders) {
        int[] news = Arrays.copyOf(nums, nums.length);
        int origins[] = new int[52];
        for (int i = 0; i < origins.length; i++) {
            origins[i] = i + 1;
        }
        for (int i = 0; i < 52; i++) {
            for (int j = i + 1; j < 52; j++) {
                int pi = posInOrders(orders, origins[i]);
                int pj = posInOrders(orders, origins[j]);
                if (pi > pj) {
                    int t = origins[i];
                    origins[i] = origins[j];
                    origins[j] = t;

                    t = news[i];
                    news[i] = news[j];
                    news[j] = t;
                }
            }
        }
        return news;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        in.readLine();
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            int[][] orders = new int[n][52];
            int p = 0;
            while (p < n * 52) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int m = st.countTokens();
                for (int i = 0; i < m; i++) {
                    int a = p / 52;
                    int b = p % 52;
                    orders[a][b] = Integer.parseInt(st.nextToken());
                    p++;
                }
            }
            int[] nums = new int[52];
            for (int i = 0; i < 52; i++) {
                nums[i] = i + 1;
            }
            while (true) {
                String line = in.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                int k = Integer.parseInt(line);
                nums = shuffle(nums, orders[k - 1]);
            }
            for (int i = 0; i < 52; i++) {
                int a = (nums[i] - 1) / 13;
                int b = (nums[i] - 1) % 13;
                String[] values = new String[]{"2", "3", "4", "5", "6", "7", "8", "9",
                    "10", "Jack", "Queen", "King", "Ace"};
                String value = values[b];
                String[] suits = new String[]{"Clubs", "Diamonds", "Hearts", "Spades"};
                String suit = suits[a];
                out.append(String.format("%s of %s\n", value, suit));
            }
            t--;
            if (t != 0) {
                out.append('\n');
            }
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
