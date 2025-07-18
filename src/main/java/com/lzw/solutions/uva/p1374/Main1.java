package com.lzw.solutions.uva.p1374;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;

public class Main1 {

    BufferedReader in;
    PrintWriter out;

    Main1() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    static class State {
        String str;
        int dist;
        int subtracCount;

        State() {
            this.str = "";
            this.dist = 0;
            this.subtracCount = 0;
        }

        State(String str) {
            this.str = str;
            this.dist = 0;
            this.subtracCount = 0;
        }

        static String numsToString(int nums[]) {
            Arrays.sort(nums);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nums.length; i++) {
                if (i != 0) {
                    sb.append(',');
                }
                sb.append(String.valueOf(nums[i]));
            }
            return sb.toString();
        }

        static Main1.State fromNums(int nums[]) {
            String s = numsToString(nums);
            return new State(s);
        }

        int[] nums() {
            String[] ss = str.split(",");
            int nums[] = new int[ss.length];
            for (int i = 0; i < ss.length; i++) {
                nums[i] = Integer.parseInt(ss[i]);
            }
            return nums;
        }
    }

    ArrayBlockingQueue<State> queue;
    HashSet<String> set;
    int maxDepth;
    HashMap<Integer, Integer> map;

    void tryAddToQueue(State cur, int nums[], int newNum, int n, boolean isSubtract) {
        recordAns(newNum, cur.dist + 1);
        int newNumIndex = Arrays.binarySearch(nums, newNum);
        if (newNumIndex >= 0) {
            return;
        }
        int newNums[] = Arrays.copyOf(nums, nums.length + 1);
        newNums[nums.length] = newNum;
        String str = State.numsToString(newNums);
        if (set.contains(str)) {
            return;
        }
        int maxNum = nums[nums.length - 1];
        if (newNum > maxNum) {
            maxNum = newNum;
        }
        if (maxNum * Math.pow(2, maxDepth - cur.dist - 1) < n) {
            return;
        }
        int surpassN = 0;
        for (int i = newNums.length - 1; i >= 0; i--) {
            if (newNums[i] > n) {
                surpassN++;
            } else {
                break;
            }
        }
        if (surpassN > 1) {
            return;
        }
        if (isSubtract && cur.subtracCount + 1 >= maxDepth / 5) {
            return;
        }
        set.add(str);
        State newState = new State(str);
        newState.dist = cur.dist + 1;
        if (isSubtract) {
            newState.subtracCount = cur.subtracCount + 1;
        } else {
            newState.subtracCount = cur.subtracCount;
        }
        queue.add(newState);
    }

    int cal(int n) {
        Integer ans = map.get(n);
        if (ans != null) {
            return ans;
        }
        if (n >= 512) {
            maxDepth = (int) Math.ceil((Math.log(n) / Math.log(2)) * 8 / 7);
        } else {
            maxDepth = (int) Math.ceil((Math.log(n) / Math.log(2)) * 8 / 7);
        }
        queue = new ArrayBlockingQueue<>(1000000);
        set = new HashSet<>();
        set.add("1");
        State startState = new State("1");
        queue.add(startState);
        while (!queue.isEmpty()) {
            State st = queue.poll();
            int[] nums = st.nums();
            int i = nums.length - 1;
            for (int j = nums.length - 1; j >= 0; j--) {
                int sum = nums[i] + nums[j];
                tryAddToQueue(st, nums, sum, n, false);
                if (sum == n) {
                    return st.dist + 1;
                }
                int subtract = nums[i] - nums[j];
                if (subtract <= 0) {
                    continue;
                }
                tryAddToQueue(st, nums, subtract, n, true);
                if (subtract == n) {
                    return st.dist + 1;
                }
            }
        }
        return 0;
    }

    private void recordAns(int newNum, int depth) {
        Integer ans = map.get(newNum);
        if (ans == null) {
            map.put(newNum, depth);
        }
    }

    void solve() throws IOException {
        map = new HashMap<>();
        ArrayList<Integer> inputs = new ArrayList<>();
        int maxN = 0;
        int i = 1;
        while (true) {
            // int n = Integer.parseInt(in.readLine());
            int n = i;
            if (n == 0) {
                break;
            }
            inputs.add(n);
            if (n > maxN) {
                maxN = n;
            }
            i++;
            if (i == 1001) {
                break;
            }
        }
        map.put(1, 0);
        cal(maxN);
        for (Integer input : inputs) {
            int ans = cal(input);
            if (input > 1) {
                assert (ans != 0);
            }
            out.append(String.valueOf(ans)).append(',');
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

        Main1 main = new Main1();
        main.solve();
        main.close();
    }
}
