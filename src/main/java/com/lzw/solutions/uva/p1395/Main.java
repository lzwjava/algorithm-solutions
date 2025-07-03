package com.lzw.solutions.uva.p1395;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    int MAX_SLIMMEST = 100000;

    int verticesNum;
    int edgesNum;
    int[][] graph;
    ArrayList<Edge> edges;
    int slimmest;

    private class Edge {
        int a;
        int b;
        int w;
    }

    void traverseGraph(boolean[] vis, int curVertice, int[][] verifyGraph) {
        vis[curVertice] = true;
        for (int i = 0; i < verticesNum; i++) {
            if (verifyGraph[curVertice][i] > 0 && !vis[i]) {
                traverseGraph(vis, i, verifyGraph);
            }
        }
    }

    void verify(boolean edgeSelected[]) {
        int[][] verifyGraph = new int[verticesNum][verticesNum];
        ArrayList<Edge> selectedEdges = new ArrayList<>();
        for (int i = 0; i < edgesNum; i++) {
            if (edgeSelected[i]) {
                Edge e = edges.get(i);
                selectedEdges.add(e);
                verifyGraph[e.a][e.b] = e.w;
                verifyGraph[e.b][e.a] = e.w;
            }
        }
        boolean vis[] = new boolean[verticesNum];
        traverseGraph(vis, 0, verifyGraph);

        boolean allVisited = true;
        for (int i = 0; i < verticesNum; i++) {
            if (!vis[i]) {
                allVisited = false;
                break;
            }
        }

        if (allVisited) {
            int[] weights = new int[selectedEdges.size()];
            for (int i = 0; i < selectedEdges.size(); i++) {
                weights[i] = selectedEdges.get(i).w;
            }
            Arrays.sort(weights);
            int slimness = weights[selectedEdges.size() - 1] - weights[0];
            if (slimness < slimmest) {
                slimmest = slimness;
            }
        }
    }

    void permutation(boolean edgeSelected[], int i, int n, int selected, int maxSelected) {
        if (selected > maxSelected) {
            return;
        }
        if (i == n) {
            if (selected == maxSelected) {
                // for (int j = 0; j < n; j++) {
                //     System.out.print(edgeSelected[j] + " ");
                // }
                // System.out.println();
                verify(edgeSelected);
                return;
            }
            return;
        }
        edgeSelected[i] = false;
        permutation(edgeSelected, i + 1, n, selected, maxSelected);
        edgeSelected[i] = true;
        permutation(edgeSelected, i + 1, n, selected + 1, maxSelected);
    }

    void work() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            verticesNum = sc.nextInt();
            edgesNum = sc.nextInt();
            graph = new int[verticesNum][verticesNum];
            edges = new ArrayList<>();
            slimmest = MAX_SLIMMEST;

            for (int i = 0; i < edgesNum; i++) {
                int a = sc.nextInt() - 1;
                int b = sc.nextInt() - 1;
                int w = sc.nextInt();
                graph[a][b] = w;
                graph[b][a] = w;
                Edge e = new Edge();
                e.a = a;
                e.b = b;
                e.w = w;
                edges.add(e);
            }

            boolean[] edgeSelected = new boolean[edgesNum];
            permutation(edgeSelected, 0, edgesNum, 0, verticesNum - 1);

            if (slimmest != MAX_SLIMMEST) {
                System.out.println(slimmest);
            } else {
                System.out.println(-1);
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
