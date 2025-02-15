import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws java.lang.Exception {
    Scanner cin = new Scanner(new File("in.txt"));
    int t = cin.nextInt();
    while (t-- > 0) {
      int n = cin.nextInt();
      String s = Integer.toString(n);
      boolean hasSeven = false;
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == '7') {
          hasSeven = true;
          break;
        }
      }
      if (hasSeven) {
        System.out.println(0);
        continue;
      }
      int ans = 0;
      if (n % 9 == 0) {
        System.out.println(1);
      } else if ((n + (9 - (n % 9))) % 10 == 7) {
        System.out.println(1);
      }
       else {
        System.out.println(1);
      }
    }
  }
}
