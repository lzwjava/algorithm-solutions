import java.io.*;
import java.util.ArrayList;
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
    }

    class Figure {
        char type;
        Coord[] coords;
        double radius;

        Figure(char type) {
            this.type = type;
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
        }
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(s);
            Coord coord = new Coord(st.nextToken(), st.nextToken());
            
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
