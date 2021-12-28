#include <algorithm>
#include <cassert>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main()
{
#ifndef ONLINE_JUDGE
    freopen("1.in", "r", stdin);
#endif
    int t;
    cin >> t;
    while (t > 0) {
        t--;
        int n;
        long long k;
        cin >> n >> k;
        vector<int> a(n);
        long long s = 0;
        for (int i = 0; i < n; i++) {
            cin >> a[i];
            s += a[i];
        }
        sort(a.begin(), a.end());
        long long ans;
        if (s <= k) {
            ans = 0;
        } else {
            ans = 1 << 30;
            for (int set = 0; set <= n - 1; set++) {
                if (set >= ans) {
                    break;
                }
                long long ns = s + (long long)a[0] * set;
                long long decrease = 0;
                if (ns > k) {
                    decrease = (ns - k + set) / (set + 1);
                }
                long long step = decrease + set;
                ans = min(ans, step);
                s -= a[n - 1 - set];
            }
        }
        cout << ans << endl;
    }
    return 0;
}