#include <assert.h>
#include <stdio.h>
#include <string.h>

typedef struct Result {
    int min;
    int max;
    long long p;
} Result;

#define MAXN 100000000

Result map[MAXN];
int n;

long long key(int i, int j)
{
    return (long long)i * n + j;
}

Result* get(int i, int j)
{
    long long k = key(i, j);
    if (k < MAXN) {
        return &map[k];
    } else {
        return NULL;
    }
}

void set(int i, int j, Result r)
{
    long long k = key(i, j);
    if (k < MAXN) {
        map[k] = r;
    }
}

Result dp(int a[], int i, int j)
{
    Result* cache = get(i, j);
    if (cache != NULL && cache->p != 0) {
        return *cache;
    }
    Result fr;
    if (j - i == 1) {
        Result r;
        r.max = a[i] > a[j] ? a[i] : a[j];
        r.min = a[i] < a[j] ? a[i] : a[j];
        r.p = (long long)a[i] * a[j];
        fr = r;
    } else {
        Result r1 = dp(a, i, j - 1);
        Result r2 = dp(a, i + 1, j);
        int max = r1.max > r2.max ? r1.max : r2.max;
        int min = r1.min < r2.min ? r1.min : r2.min;
        long long p = (long long)max * min;
        Result r;
        r.max = max;
        r.min = min;
        r.p = p;
        if (r.p > r1.p) {
            if (r.p > r2.p) {
                fr = r;
            } else {
                fr = r2;
            }
        } else {
            if (r1.p > r2.p) {
                fr = r1;
            } else {
                fr = r2;
            }
        }
    }
    set(i, j, fr);
    return fr;
}

int main()
{
#ifndef ONLINE_JUDGE
    freopen("1.in", "r", stdin);
#endif
    int t;
    scanf("%d", &t);
    for (int i = 0; i < t; i++) {
        scanf("%d", &n);
        long long size = (long long)n * n;
        if (size > MAXN) {
            size = MAXN;
        }
        memset(map, 0, sizeof(Result) * size);
        int a[n];
        for (int i = 0; i < n; i++) {
            scanf("%d", &a[i]);
        }
        Result r = dp(a, 0, n - 1);
        printf("%lld\n", r.p);
    }
    return 0;
}
