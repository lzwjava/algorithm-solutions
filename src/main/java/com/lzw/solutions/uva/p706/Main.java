package com.lzw.solutions.uva.p706;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    char[][] subGrid(int s, int num) {
        int rows = 2 * s + 3;
        int cols = s + 2;
        char[][] grid = new char[rows][cols];
        if (num == 0) {
            for (int i = 0; i < rows; i++) {
                if (i == 0 || i == rows - 1) {
                    draw1(grid, cols, i);              
                } else if (i == s + 1) {
                    draw2(grid, cols, i);                   
                } else {
                    draw3(grid, cols, i);
                }
            }
        } else if (num == 1) {
            for (int i = 0; i < rows; i++) {
                if (i == 0 || i == rows - 1 || i == s + 1) {
                    draw2(grid, cols, i);                    
                } else {
                    draw4(grid, cols, i);               
                }
            }
        } else if (num == 2) {
            for (int i = 0; i < rows; i++) {
                if (i == 0 || i == rows - 1 || i == s + 1) {
                    draw1(grid, cols, i);                
                } else if (i >= 1 && i < s + 1) {
                    draw4(grid, cols, i);                    
                } else {
                    draw5(grid, cols, i);
                }
            }
        } else if (num == 3) {
            for (int i = 0; i < rows; i++) {
                if (i == 0 || i == rows - 1 || i == s + 1) {
                    draw1(grid, cols, i);               
                } else {
                    draw4(grid, cols, i);
                }
            }
        } else if (num == 4) {
            for (int i = 0; i < rows; i++) {
                if (i == 0 || i == rows - 1) {
                    draw2(grid, cols, i);                    
                } else if (i == s + 1) {
                    draw1(grid, cols, i);
                } else if (i < s + 1) {
                    draw3(grid, cols, i);            
                } else {
                    draw4(grid, cols, i);                                
                }
            }
        } else if (num == 5) {
            for (int i = 0; i < rows; i++) {
                if (i == 0 || i == rows - 1 || i == s + 1) {
                    draw1(grid, cols, i);                    
                } else if (i < s + 1) {
                    draw5(grid, cols, i);                    
                } else {
                    draw4(grid, cols, i);
                }
            }
        } else if (num == 6) {
            for (int i = 0; i < rows; i++) {
                if (i == 0 || i == rows - 1 || i == s + 1) {
                    draw1(grid, cols, i);                    
                } else if (i < s + 1) {
                    draw5(grid, cols, i);                    
                } else {
                    draw3(grid, cols, i);
                }
            }
        } else if (num == 7) {
            for (int i = 0; i < rows; i++) {
                if (i == rows - 1 || i == s + 1) {
                    draw2(grid, cols, i);                    
                } else if (i ==0) {
                    draw1(grid, cols, i);                    
                } else {
                    draw4(grid, cols, i);
                }
            }
        } else if (num == 8) {
            for (int i = 0; i < rows; i++) {
                if (i == 0 || i == rows - 1 || i == s + 1) {                    
                    draw1(grid, cols, i);              
                } else {
                    draw3(grid, cols, i);
                }
            }            
        } else if (num == 9) {
            for (int i = 0; i < rows; i++) {
                if (i == 0 || i == rows - 1 || i == s + 1) {                    
                    draw1(grid, cols, i);              
                } else if (i<s+1){
                    draw3(grid, cols, i);
                } else {
                    draw4(grid, cols, i);                    
                }
            }               
        }
        return grid;
    }
    
    // " -- "
    void draw1(char[][] grid, int cols, int i) {
        for (int j = 0; j < cols; j++) {
            if (j == 0 || j == cols - 1) {
                grid[i][j] = ' ';
            } else {
                grid[i][j] = '-';
            }
        }
    }

    // "    "
    void draw2(char[][] grid, int cols, int i) {
        for (int j = 0; j < cols; j++) {
            grid[i][j] = ' ';
        }
    }

    // "|  |"
    void draw3(char[][] grid, int cols, int i) {
        for (int j = 0; j < cols; j++) {
            if (j == 0 || j == cols - 1) {
                grid[i][j] = '|';
            } else {
                grid[i][j] = ' ';
            }
        }
    }

    // "   |"
    void draw4(char[][] grid, int cols, int i) {
        for (int j = 0; j < cols; j++) {
            if (j == cols - 1) {
                grid[i][j] = '|';
            } else {
                grid[i][j] = ' ';
            }
        }
    }
    
    // "|   "
    void draw5(char[][] grid, int cols, int i) {
        for (int j = 0; j < cols; j++) {
            if (j == 0) {
                grid[i][j] = '|';
            } else {
                grid[i][j] = ' ';
            }
        }    
    }    
   
    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int s = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (s == 0 && n == 0) {
                break;
            }
            String ns = String.format("%d", n);
            int len = ns.length();
            int rows = 2 * s + 3;
            int cols = (s + 2) * len + (len-1);            
            char[][] grid = new char[rows][cols];
            for (int i = 0; i < len; i++) {
                char ch = ns.charAt(i);
                int num = ch - '0';
                char[][] subGrid = subGrid(s, num);
                int subCols = s + 2;
                for (int j = 0; j < rows; j++) {
                    for (int k = 0; k < subCols; k++) {
                        int c = k + (s + 2) * i + i;
                        grid[j][c] = subGrid[j][k];
                    }
                }
                if (i != len - 1) {
                    for (int j = 0; j < rows; j++) {
                        int c = (s + 2) * (i + 1) + i;
                        grid[j][c] = ' ';
                    }
                }
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    out.append(grid[i][j]);
                }
                out.append('\n');
            }
            out.append('\n');
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
