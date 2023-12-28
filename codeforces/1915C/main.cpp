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
        int as[n];
        int s = 0;
        for (int i = 0; i < n; i++) {
            cin >> as[i];
            s += as[i];
        }
        int sn = sqrt(s);
        if (sn * sn == s) {
            cout << "YES" << endl;
        } else {
            cout << "NO" << endl;
        }
    }

    return 0;
}
