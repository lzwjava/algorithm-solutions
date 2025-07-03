package com.algorithm.solutions.uva.p165;

// chatgpt
import java.util.Arrays;

public class StampOptimization {

    private static int maxValue = 0;
    private static int[] bestStamps = new int[10];
    private static int H, K;
    private static int[] currStamps = new int[10];
    private static int[][] dp = new int[200][10];

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (true) {
            H = scanner.nextInt();
            K = scanner.nextInt();
            if (H == 0 && K == 0) break;

            // Initialize the dp table
            for (int[] row : dp) {
                Arrays.fill(row, 0);
            }
            dp[0][0] = 1;
            maxValue = 0;

            // Start the DFS to find the optimal denominations
            dfs(0, 0, 0);

            // Output the result
            for (int i = 0; i < K; i++) {
                System.out.printf("%3d", bestStamps[i]);
            }
            System.out.printf(" ->%3d%n", maxValue);
        }
        scanner.close();
    }

    private static void dfs(int idx, int lastStamp, int maxReach) {
        int currMax = 0;

        // Calculate the maximum consecutive value that can be reached
        for (int i = maxReach; ; i++) {
            int j;
            for (j = 0; j <= H; j++) {
                if (dp[i][j] != 0) break;
            }
            if (j == H + 1) {
                currMax = i - 1;
                break;
            }
        }

        // Check if we have selected all denominations
        if (idx == K) {
            if (currMax >= maxValue) {
                maxValue = currMax;
                System.arraycopy(currStamps, 0, bestStamps, 0, K);
            }
            return;
        }

        // Try adding a new denomination
        int[][] steps = new int[10000][2];
        for (int i = lastStamp + 1; i <= currMax + 1; i++) {
            int stepCount = 0;

            // Update dp table with the current denomination
            for (int j = 0; j <= 100; j++) {
                for (int k = 0; k < H; k++) {
                    if (dp[j + i][k + 1] == 0 && dp[j][k] != 0) {
                        dp[j + i][k + 1] = 1;
                        steps[stepCount][0] = j + i;
                        steps[stepCount][1] = k + 1;
                        stepCount++;
                    }
                }
            }

            currStamps[idx] = i;
            dfs(idx + 1, i, currMax);

            // Backtrack to undo dp updates
            for (int j = 0; j < stepCount; j++) {
                dp[steps[j][0]][steps[j][1]] = 0;
            }
        }
    }
}
