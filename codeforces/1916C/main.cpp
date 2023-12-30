#include <algorithm>
#include <cassert>
#include <cctype>
#include <fstream>
#include <iostream>
#include <list>
#include <string>

using namespace std;

void solve()
{
    int n;
    cin >> n;
    long a[n];
    long s[n];
    long ps = 0;
    long m = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        ps += a[i];
        s[i] = ps;
        m += a[i] % 2;
    }
    for (int i = 0; i < n; i++) {
        if (i != 0) {
            cout << ' ';
        }
        cout << s[i];
    }
    cout << endl;
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
