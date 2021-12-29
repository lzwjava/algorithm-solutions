#include <algorithm>
#include <cassert>
#include <iostream>
#include <set>
#include <string>
#include <vector>

using namespace std;

bool palindrome(vector<int> a)
{
    int m = a.size();
    for (int i = 0; i < m; i++) {
        if (a[i] != a[m - 1 - i]) {
            return false;
        }
    }
    return true;
}

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
        cin >> n;
        vector<int> a(n);
        for (int i = 0; i < n; i++) {
            cin >> a[i];
        }
        bool ok;
        if (palindrome(a)) {
            ok = true;
        } else {
            ok = false;
            set<int> s;
            for (int i = 0; i < n / 2; i++) {
                int i1 = i;
                int i2 = n - 1 - i;
                if (a[i1] != a[i2]) {
                    s.insert(a[i1]);
                    s.insert(a[i2]);
                    break;
                }
            }
            set<int>::iterator itr;
            for (itr = s.begin(); itr != s.end(); itr++) {
                vector<int> tl;
                for (int i = 0; i < n; i++) {
                    if (a[i] != *itr) {
                        tl.push_back(a[i]);
                    }
                }
                if (palindrome(tl)) {
                    ok = true;
                    break;
                }
            }
        }
        if (ok) {
            cout << "YES" << endl;
        } else {
            cout << "NO" << endl;
        }
    }
    return 0;
}