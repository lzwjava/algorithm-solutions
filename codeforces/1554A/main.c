#include <stdio.h>
int main()
{
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
        long long mm = 0;
        int mins[n][n];
        for (int l = 1; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                if (i == j) {
                    mins[i][j] = a[i];
                } else {
                    if (mins[i][j - 1] < mins[j][j]) {
                        mins[i][j] = mins[i][j - 1];
                    } else {
                        mins[i][j] = mins[j][j];
                    }
                }
            }
        }
        int maxs[n][n];
        for (int l = 1; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                if (i == j) {
                    maxs[i][j] = a[i];
                } else {
                    if (maxs[i][j - 1] > maxs[j][j]) {
                        maxs[i][j] = maxs[i][j - 1];
                    } else {
                        maxs[i][j] = maxs[j][j];
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long long p = (long long)mins[i][j] * maxs[i][j];
                if (p > mm) {
                    mm = p;
                }
            }
        }
        printf("%lld\n", mm);
    }
    return 0;
}
