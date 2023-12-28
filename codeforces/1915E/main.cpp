#include <algorithm>
#include <cassert>
#include <cctype>
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
        int a[n];
        for (int i = 0; i < n; i++) {
            cin >> a[i];
        }
        long long s[n];
        s[0] = a[0];
        for (int i = 1; i < n; i++) {
            s[i] = s[i - 1] + a[i];
        }
        for (int l = 0; l < n; l++) {
            for (int r = l + 1; r < n; r++) {
                long long sl = l == 0 ? 0 : s[l - 1];
                long long slr = s[r] - sl;

                if (slr % 2 == 0) {
                    cout << l << " " << r << endl;
                }
            }
        }
        cout << endl;
    }

    return 0;
}
