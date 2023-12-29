#include <algorithm>
#include <cassert>
#include <cctype>
#include <fstream>
#include <iostream>
#include <list>
#include <string>

using namespace std;

bool greet(int a1, int b1, int a2, int b2)
{
    if (a1 > a2) {
        return greet(a2, b2, a1, b1);
    } else {
        return b1 > b2;
    }
}

void solve()
{
    int n;
    cin >> n;
    int a[n], b[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i] >> b[i];
    }
    long sum = 0;
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            if (greet(a[i], b[i], a[j], b[j])) {
                sum += 1;
            }
        }
    }
    cout << sum << endl;
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