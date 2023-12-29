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
    long long p = 1;
    for (int i = 0; i < n; i++) {
        int ai;
        cin >> ai;
        if (ai > 0) {
            ai = 1;
        } else if (ai < 0) {
            ai = -1;
        }
        p *= ai;
    }
    if (p > 0) {
        cout << 1 << endl;
        cout << 1 << ' ' << 0 << endl;
    } else if (p == 0) {
        cout << 0 << endl;
    } else {
        cout << 0 << endl;
    }
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
