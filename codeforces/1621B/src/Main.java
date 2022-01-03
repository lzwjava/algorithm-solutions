import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    class Segment implements Comparable<Segment> {
        int l, r, c;

        Segment(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }


        @Override
        public int compareTo(Segment o) {
            return Integer.compare(l, o.l);
        }

        @Override
        protected Segment clone() {
            return new Segment(l, r, c);
        }
    }

    boolean intersect(Segment a, Segment b) {
        if (a.compareTo(b) > 0) {
            Segment t = a;
            a = b;
            b = t;
        }
        return a.r >= b.l;
    }

    class Selection {
        int cnt;
        Segment one;
        Segment left, right;

        Segment maxSegment() {
            if (cnt == 1) {
                return one;
            } else {
                return new Segment(left.l, right.r, left.c + right.c);
            }
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            Segment[] segments = new Segment[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                segments[i] = new Segment(l, r, c);
            }
            Selection selection = new Selection();
            selection.cnt = 1;
            selection.one = segments[0].clone();

            for (int i = 1; i < n; i++) {
                Segment si = segments[i];
                Segment maxSegment = selection.maxSegment();
                out.append(String.format("%d\n", maxSegment.c));

                if (intersect(si, maxSegment)) {
                    if (si.l <= maxSegment.l && si.r >= maxSegment.r) {
                        if (si.l == maxSegment.l && si.r == maxSegment.r && si.c > maxSegment.r) {
                            selection.cnt = 1;
                            selection.one = si;
                        } else if (si.l < maxSegment.l || si.r > maxSegment.r) {
                            selection.cnt = 1;
                            selection.one = si;
                        }
                    } else {
                        if (si.l < maxSegment.l) {
                            if (selection.cnt == 1) {
                                selection.cnt = 2;
                                selection.right = selection.one;
                                selection.left = si;
                            } else if (selection.cnt == 2) {
                                selection.left = si;
                            }
                        } else if (si.r > maxSegment.r) {
                            if (selection.cnt == 1) {
                                selection.cnt = 2;
                                selection.right = si;
                                selection.left = selection.one;
                            } else if (selection.cnt == 2) {
                                selection.right = si;
                            }
                        } else if (si.l == maxSegment.l) {
                            if (selection.cnt == 2 && si.c < selection.left.c) {
                                selection.left = si;
                            }
                        } else if (si.r == maxSegment.r) {
                            if (selection.cnt == 2 && si.c < selection.right.c) {
                                selection.right = si;
                            }
                        }
                    }
                } else {
                    int comp = si.compareTo(maxSegment);
                    if (comp < 0) {
                        if (selection.cnt == 1) {
                            selection.cnt = 2;
                            selection.left = si;
                            selection.right = selection.one;
                        } else {
                            selection.left = si;
                        }
                    } else if (comp > 0) {
                        if (selection.cnt == 1) {
                            selection.cnt = 2;
                            selection.left = selection.one;
                            selection.right = si;
                        }
                    }
                }
            }
            out.append(String.format("%d\n", selection.maxSegment().c));
        }
    }
}