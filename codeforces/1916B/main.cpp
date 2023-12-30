#include <algorithm>
#include <cassert>
#include <cctype>
#include <fstream>
#include <iostream>
#include <list>
#include <string>

using namespace std;

int gcd(int a, int b)
{
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}

long cal(int a, int b)
{
    int c = gcd(a, b);
    if (c == 1) {
        long ans;
        if (b % a == 0) {
            if (a == 1) {
                ans = (long)b * b;
            } else {
                int d = b / a;
                if (d < a) {
                    ans = (long)b * d;
                } else {
                    ans = (long)b * a;
                }
            }
        } else {
            ans = (long long)b * a;
        }
        return ans;
    } else {
        return cal(a / c, b / c) * c;
    }
}

void solve()
{
    int a, b;
    cin >> a >> b;
    long ans = cal(a, b);
    cout << ans << endl;
}

int main()
{
    if (ifstream("in.txt")) {
        freopen("in.txt", "r", stdin);
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }

    return 0;
}
