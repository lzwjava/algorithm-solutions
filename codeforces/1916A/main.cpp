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
    int n, k;
    cin >> n >> k;
    int b[n];
    long long p = 1;
    for (int i = 0; i < n; i++) {
        cin >> b[i];
        p *= b[i];
    }
    if (2023 % p == 0) {
        cout << "YES" << endl;
        int a = 2023 / p;
        int rs[k];
        rs[0] = a;
        for (int i = 1; i < k; i++) {
            rs[i] = 1;
        }
        for (int i = 0; i < k; i++) {
            if (i != 0) {
                cout << " ";
            }
            cout << rs[i];
        }
        cout << endl;
    } else {
        cout << "NO" << endl;
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
