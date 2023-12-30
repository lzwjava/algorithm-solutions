// https://codeforces.com/contest/1917/submission/238700857
#include <algorithm>
#include <cassert>
#include <cctype>
#include <cmath>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <list>
#include <string>

using namespace std;

struct BIT {
    int n;
    vector<int> bit;

    BIT(int n)
        : n(n)
        , bit(n + 1)
    {
    }

    void add(int x, int y)
    {
        for (; x <= n; x += x & -x) {
            bit[x] += y;
        }
    }

    int sum(int x)
    {
        int y = 0;
        for (; x; x -= x & -x) {
            y += bit[x];
        }
        return y;
    }
};

using i64 = int64_t;
constexpr i64 mod = 998244353;

int main()
{
    if (ifstream("in.txt")) {
        freopen("in.txt", "r", stdin);
    }
    cin.tie(nullptr)->sync_with_stdio(false);
    cout << fixed << setprecision(20);

    int t;
    cin >> t;

    for (int ti = 0; ti < t; ti += 1) {
        int n, k;
        cin >> n >> k;
        vector<int> p(n), q(k);

        for (int& pi : p) {
            cin >> pi;
        }
        for (int& qi : q) {
            cin >> qi;
        }

        i64 ans = 0;
        BIT bit_q(k);

        for (int qi : q) {
            ans += bit_q.sum(k) - bit_q.sum(qi);
            bit_q.add(qi + 1, 1);
        }

        ans = ans % mod * n % mod;
        BIT bit_p(2 * n - 1);

        for (int pi : p) {
            for (int i = 0; i < k; i += 1) {
                int x = (pi + (1 << i) - 1) >> i;
                if (x == 1) {
                    ans += i64(k - i + 1) * (k - i) / 2 % mod * bit_p.sum(2 * n - 1) % mod;
                    break;
                }
                ans += i64(k - i) * (bit_p.sum(2 * n - 1) - bit_p.sum(x - 1)) % mod;
            }
            for (int i = 1; i < k; i += 1) {
                int x = pi << i;
                if (x > 2 * n - 1) {
                    break;
                }
                ans += i64(k - i) * (bit_p.sum(2 * n - 1) - bit_p.sum(x - 1)) % mod;
            }
            bit_p.add(pi, 1);
        }

        cout << ans % mod << "\n";
    }
}
