#include <assert.h>
#include <stdio.h>
#include <string.h>

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
        long long max = 0;
        for (int i = 1; i < n; i++) {
            long long v = (long long)a[i] * a[i - 1];
            if (v > max) {
                max = v;
            }
        }
        printf("%lld\n", max);
    }
    return 0;
}
