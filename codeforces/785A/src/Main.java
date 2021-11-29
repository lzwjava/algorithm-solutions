import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        String[] ss = new String[]{"Tetrahedron", "Cube", "Octahedron", "Dodecahedron", "Icosahedron"};
        int[] fs = new int[]{4, 6, 8, 12, 20};
        int n = in.nextInt();
        int c = 0;
        while (n > 0) {
            String s = in.next();
            int i;
            for (i = 0; i < ss.length; i++) {
                if (ss[i].equals(s)) {
                    break;
                }
            }
            c += fs[i];
            n--;
        }
        System.out.println(c);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
