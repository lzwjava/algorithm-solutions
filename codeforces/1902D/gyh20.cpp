#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
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
int t, n, m, a[1000002], ans, X[200002], Y[200002], R[1000002], stk[1000002], tp;
char s[1000002];
map<pair<int, int>, vector<int>> V;
inline bool Ask(int x, int y, int l, int r)
{
    pair<int, int> tmp = make_pair(x, y);
    int o = lower_bound(V[tmp].begin(), V[tmp].end(), l) - V[tmp].begin();
    if (o >= V[tmp].size())
        return 0;
    return V[tmp][o] <= r;
}
int main()
{
	freopen("in.txt", "r", stdin);
    n = read(), m = read();
    scanf("%s", s + 1);
    for (int i = 1; i <= n; ++i) {
        X[i] = X[i - 1], Y[i] = Y[i - 1];
        if (s[i] == 'R')
            ++X[i];
        else if (s[i] == 'L')
            --X[i];
        else if (s[i] == 'U')
            ++Y[i];
        else
            --Y[i];
    }
    for (int i = 0; i <= n; ++i)
        V[make_pair(X[i], Y[i])].push_back(i);
    while (m--) {
        int x = read(), y = read(), l = read(), r = read();
        if (Ask(x, y, 0, l - 1))
            puts("YES");
        else if (Ask(x, y, r, n))
            puts("YES");
        else if (Ask(X[l - 1] * 2 + (X[r] - X[l - 1]) - x, Y[l - 1] * 2 + (Y[r] - Y[l - 1]) - y, l, r))
            puts("YES");
        else
            puts("NO");
    }
}