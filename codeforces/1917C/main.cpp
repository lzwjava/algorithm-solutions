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
    int n, k, d;
    cin >> n >> k >> d;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    int v[k];
    for (int i = 0; i < k; i++) {
        cin >> v[i];
    }
    cout << d << endl;
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
