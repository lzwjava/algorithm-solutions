package com.lzw.solutions.uva.p478;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Coord {
        double x, y;

        Coord(double x, double y) {
            this.x = x;
            this.y = y;
        }

        Coord(String xs, String ys) {
            double x = Double.parseDouble(xs);
            double y = Double.parseDouble(ys);
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coord coord = (Coord) o;
            return Double.compare(coord.x, x) == 0 && Double.compare(coord.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    class Figure {
        char type;
        Coord[] coords;
        double radius;

        Figure(char type) {
            this.type = type;
        }

        double dist(Coord a, Coord b) {
            return Math.hypot(a.x - b.x, a.y - b.y);
        }

        double area(Coord c1, Coord c2, Coord c3) {
            double a = dist(c1, c2);
            double b = dist(c1, c3);
            double c = dist(c2, c3);
            double s = (a + b + c) / 2;
            return Math.sqrt(s * (s - a) * (s - b) * (s - c));
        }

        boolean contains(Coord a) {
            if (type == 'c') {
                double d = dist(a, coords[0]);
                return Double.compare(d, radius) < 0;
            } else if (type == 'r') {
                return Double.compare(a.x, coords[0].x) > 0
                        && Double.compare(a.x, coords[1].x) < 0
                        && Double.compare(a.y, coords[0].y) < 0
                        && Double.compare(a.y, coords[1].y) > 0;
            } else if (type == 't') {
                double[] q = new double[3];
                q[0] = area(coords[0], coords[1], a);
                q[1] = area(coords[0], coords[2], a);
                q[2] = area(coords[1], coords[2], a);
                for (int i = 0; i < 3; i++) {
                    if (q[i] < 1e-6) {
                        return false;
                    }
                }
                double s1 = q[0] + q[1] + q[2];
                double s2 = area(coords[0], coords[1], coords[2]);
                return Math.abs(s1 - s2) < 1e-6;
            }
            return false;
        }
    }

    void solve() throws IOException {
        ArrayList<Figure> figures = new ArrayList<Figure>();
        while (true) {
            String line = in.readLine();
            if (line.equals("*")) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            char type = st.nextToken().charAt(0);
            Figure figure = new Figure(type);
            if (type == 'r') {
                Coord[] coords = new Coord[2];
                coords[0] = new Coord(st.nextToken(), st.nextToken());
                coords[1] = new Coord(st.nextToken(), st.nextToken());
                figure.coords = coords;
            } else if (type == 'c') {
                Coord[] coords = new Coord[1];
                coords[0] = new Coord(st.nextToken(), st.nextToken());
                figure.coords = coords;

                double radius = Double.parseDouble(st.nextToken());
                figure.radius = radius;
            } else {
                assert (type == 't');
                Coord[] coords = new Coord[3];
                coords[0] = new Coord(st.nextToken(), st.nextToken());
                coords[1] = new Coord(st.nextToken(), st.nextToken());
                coords[2] = new Coord(st.nextToken(), st.nextToken());
                figure.coords = coords;
            }
            figures.add(figure);
        }
        int p = 0;
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            String sa = st.nextToken();
            String sb = st.nextToken();
            if (sa.equals("9999.9") && sb.equals("9999.9")) {
                break;
            }
            Coord coord = new Coord(sa, sb);
            boolean ok = false;
            for (int i = 0; i < figures.size(); i++) {
                Figure f = figures.get(i);
                //                if (p == 0 && i == 1) {
                //                    System.out.println();
                //                }
                if (f.contains(coord)) {
                    ok = true;
                    out.append(String.format("Point %d is contained in figure %d\n", p + 1, i + 1));
                }
            }
            if (!ok) {
                out.append(String.format("Point %d is not contained in any figure\n", p + 1));
            }
            p++;
        }
        //        double v = figures.get(0).area(new Coord(0, 2), new Coord(0, 0), new Coord(2, 0));
        //        out.append(String.format("%.3f\n", v));
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

        Main main = new Main();
        main.solve();
        main.close();
    }
}
