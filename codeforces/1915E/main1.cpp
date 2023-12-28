#include <algorithm>
#include <cassert>
#include <cctype>
#include <fstream>
#include <iostream>
#include <list>
#include <set>
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
        int a[n];
        for (int i = 0; i < n; i++) {
            cin >> a[i];
            if (i % 2) {
                a[i] *= -1;
            }
        }

        set<long long> pos;

        pos.insert(0);

        long long s = 0;
        bool found = false;
        for (int i = 0; i < n; i++) {
            s += a[i];
            if (pos.find(s) != pos.end()) {
                found = true;
                break;
            }
            pos.insert(s);
        }
        if (found) {
            cout << "YES" << endl;
        } else {
            cout << "NO" << endl;
        }
    }

    return 0;
}
