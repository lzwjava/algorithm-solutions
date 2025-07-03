package com.lzw.solutions.uva.p1572;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    class Molecule {
        // top right bottom left        
        String labels[];

        Molecule() {

        }

        Molecule(String[] labels) {
            this.labels = labels;
        }
    }
    
    // top right bottom left
    int dx[] = { -1, 0, 1, 0 };
    int dy[] = { 0, 1, 0, -1 };
    int size;
    int total;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    String[] makeLabels(String molecule) {
        assert (molecule.length() == 8);
        String labels[] = new String[4];
        for (int i = 0; i < 4; i++) {
            labels[i] = molecule.substring(i * 2, (i + 1) * 2);
        }
        return labels;
    }

    boolean inside(int x, int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            return true;
        }
        return false;
    }

    private void extend(Molecule molecules[][], int x, int y, String types[][]) {
        Molecule m = molecules[x][y];
        for (int i = 0; i < dx.length; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;            
            if (inside(nx, ny) &&  molecules[nx][ny] == null) {
                String label = m.labels[i];
                HashSet<String> typeSet = new HashSet<>();
                for (int j = 0; j < types.length; j++) {
                    String[] type = types[j];
                    for (int degree = 0; degree < 4; degree++) {
                        String[] dtype = rotateType(type, degree);                        
                        for (int invert = 0; invert < 2; invert++) {
                            String[] ntype = invertType(dtype, invert);
                            String ntypeString = String.join("", ntype);
                            if (typeSet.contains(ntypeString)) {
                                continue;
                            }
                            typeSet.add(ntypeString);
                            boolean match = match(ntype, label, i);
                            if (match) {                                
                                boolean ok = true;
                                for (int k = 0; k < dx.length; k++) {
                                    int nnx = nx + dx[k];
                                    int nny = ny + dy[k];
                                    if (inside(nnx,nny) && nnx != x && nny != y) {
                                        if (molecules[nnx][nny] != null) {
                                            String nlabel = ntype[k];                                            
                                            String[] nntype = molecules[nnx][nny].labels;
                                            boolean nnmatch = match(nntype, nlabel, k);
                                            if (!nnmatch) {
                                                ok = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (ok) {
                                    molecules[nx][ny] = new Molecule(ntype);
                                    total++;                                    
                                    extend(molecules, nx, ny, types);
                                }
                            }
                        }
                    }
                }
            }
        }
        // for (int i = 0; i < types.length; i++) {

        // }
    }
       
    private boolean match(String[] ntype, String label, int dir) {
        int ndir;
        if (dir == 0 || dir == 2) {
            ndir = 2 - dir;
        } else {
            ndir = 4 - dir;
        }
        return labelMatch(ntype[ndir], label);
    }

    private boolean labelMatch(String nlabel, String label) {
        if (label.equals("00") || nlabel.equals("00")) {
            return false;
        }
        char nch = nlabel.charAt(0);
        char ch = label.charAt(0);
        if (nch != ch) {
            return false;
        }
        char sign = label.charAt(1);
        char nsign = nlabel.charAt(1);
        if ((sign == '+' && nsign == '-') || (sign == '-' && nsign == '+')) {
            return true;
        }
        return false;
    }

    private String[] invertType(String[] dtype, int invert) {
        if (invert == 0) {
            return dtype.clone();
        } else if (invert == 1) {
            String[] ntype = dtype.clone();
            String tmp = ntype[0];
            ntype[0] = ntype[2];
            ntype[2] = tmp;
        } else if (invert == 2) {
            String[] ntype = dtype.clone();
            String tmp = ntype[1];
            ntype[1] = ntype[3];
            ntype[3] = tmp;
        }
        return dtype.clone();
    }

    private String[] rotateType(String[] type, int degree) {
        String[] ntype = new String[type.length];
        for (int i = 0; i < type.length; i++) {
            int ni = i + degree;
            if (ni >= type.length) {
                ni = ni % type.length;
            }
            ntype[ni] = type[i];
        }
        return ntype;
    }

    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            int n = Integer.parseInt(s);
            String moleculeLine = in.readLine();
            StringTokenizer st = new StringTokenizer(moleculeLine);
            String moleculeStrings[] = new String[n];
            String types[][] = new String[n][];
            for (int i = 0; i < n; i++) {
                moleculeStrings[i] = st.nextToken();
                types[i] = makeLabels(moleculeStrings[i]);
            }
            size = 51;
            total = 0;
            Molecule molecules[][] = new Molecule[size][size];
            int center = size / 2;

            molecules[center][center] = new Molecule(types[0].clone());
            total++;      

            extend(molecules, center, center, types);

            if (total == size * size) {
                System.out.println("unbounded");                
            } else {
                System.out.println("bounded"); 
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
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
