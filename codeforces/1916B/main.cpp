#include <algorithm>
#include <cassert>
#include <cctype>
#include <fstream>
#include <iostream>
#include <list>
#include <string>

using namespace std;

long gcd(long a, long b)
{
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}

long cal(long a, long b)
{
    long c = gcd(a, b);
    if (c == 1) {
        long ans;
        if (b % a == 0) {
            if (a == 1) {
                ans = b * b;
            } else {
                long d = b / a;
                if (d < a) {
                    ans = b * d;
                } else {
                    ans = b * a;
                }
            }
        } else {
            ans = b * a;
        }
        return ans;
    } else {
        return cal(a / c, b / c) * c;
    }
}

void solve()
{
    long a, b;
    cin >> a >> b;
    long ans = cal(a, b);
    cout << ans << endl;
}

long main()
{
    if (ifstream("in.txt")) {
        freopen("in.txt", "r", stdin);
    }

    long t;
    cin >> t;
    while (t--) {
        solve();
    }

    return 0;
}
