import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws java.lang.Exception {
    Scanner cin = new Scanner(new File("in.txt"));
    int t = cin.nextInt();
    while (t-- > 0) {
      int n = cin.nextInt();
      int ans = 0;
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

      ans = 1;
      System.out.println(1);
    }
  }
}
