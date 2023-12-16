#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
inline int read()
{
    int t = 0;
    scanf("%d", &t);
    return t;
}
const int M = 998244353;
inline void add(int& x, int y) { (x += y) >= M ? x -= M : x; }
inline int Mod(int x) { return x >= M ? x - M : x; }
inline int ksm(int x, int y)
{
    int s = 1;
    while (y) {
        if (y & 1)
            s = 1ll * s * x % M;
        x = 1ll * x * x % M, y >>= 1;
    }
    return s;
}
int gcd(int a, int b) {
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}


int t, n, m, a[1000002], ans, A, B, R[1000002], stk[1000002], tp;
char s[1000002];
int main()
{
    t = read();
    while (t--) {
        n = read();
        for (int i = 1; i <= n; ++i)
            a[i] = read();
        if (n == 1) {
            puts("1");
            continue;
        }
        sort(a + 1, a + n + 1);
        for (int i = n; i; --i)
            a[i] -= a[1];
        int G = a[1];
        for (int i = 2; i <= n; ++i)
            G = gcd(G, a[i]);
        for (int i = 1; i <= n; ++i)
            a[i] /= G;
        bool ia = 1;
        for (int i = 1; i <= n; ++i)
            ia &= (a[i] == i - 1);
        if (ia) {
            printf("%lld\n", 1ll * n * (n + 1) >> 1);
            continue;
        }
        long long sum = 0;
        for (int i = 1; i <= n; ++i)
            sum += a[n] - a[i];
        for (int i = n - 1; i; --i)
            if (a[i] + 1 != a[i + 1]) {
                sum += a[n] - a[i + 1] + 1;
                break;
            }
        printf("%lld\n", sum);
    }
}