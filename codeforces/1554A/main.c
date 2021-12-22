#include <stdio.h>
int main() {
#ifndef ONLINE_JUDGE
  freopen("1.in", "r", stdin);
#endif
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) {
    int n;
    scanf("%d", &n);
    int a[n];
    for (int i = 0; i < n; i++) {
      scanf("%d", &a[i]);
    }
    long mm = 0;
    for (int l = 0; l < n; l++) {
      for (int r = l + 1; r < n; r++) {
        int min = a[l];
        int max = a[l];
        for (int k = l + 1; k <= r; k++) {
          if (a[k] < min) {
            min = a[k];
          }
          if (a[k] > max) {
            max = a[k];
          }
        }
        long p = (long)min * max;
        if (p > mm) {
          mm = p;
        }
      }
    }
    printf("%ld\n", mm);
  }
  return 0;
}
