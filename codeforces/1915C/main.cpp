#include <algorithm>
#include <cassert>
#include <cctype>
#include <cmath>
#include <fstream>
#include <iostream>
#include <list>
#include <string>

using namespace std;

int main()
{
    if (ifstream("in.txt")) {
        freopen("in.txt", "r", stdin);
    }

    int t;
    cin >> t;

    while (t--) {
        int n;
        cin >> n;
        long long s = 0;
        for (int i = 0; i < n; i++) {
            long long ai;
            cin >> ai;
            s += ai;
        }
        long long sn = sqrt(s);
        if (sn * sn == s) {
            cout << "YES" << endl;
        } else {
            cout << "NO" << endl;
        }
    }

    return 0;
}
