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
        long s[n];
        s[0] = a[0];
        for (int i = 1; i < n; i++) {
            s[i] = s[i - 1] + a[i];
        }
        long s0[n];
        for (int i = 0; i < n; i += 2) {
            long prev = i == 0 ? 0 : s0[i - 2];
            s0[i] = prev + a[i];
        }
        long s1[n];
        for (int i = 1; i < n; i += 2) {
            long prev = i == 1 ? 0 : s1[i - 2];
            s1[i] = prev + a[i];
        }

        bool found = false;

        for (int l = 0; l < n; l++) {
            for (int r = l + 1; r < n; r++) {
                long sl = l == 0 ? 0 : s[l - 1];
                long slr = s[r] - sl;

                if (slr % 2 == 0) {
                    long left, right;
                    if (l % 2 == 0 && r % 2 == 0) {
                        long ll = l == 0 ? 0 : s0[l - 2];
                        left = s0[r] - ll;
                        long rl = l == 0 ? 0 : s1[l - 1];
                        right = s1[r - 1] - rl;
                    } else if (l % 2 == 0 && r % 2 == 1) {
                        long ll = l == 0 ? 0 : s0[l - 2];
                        left = s0[r - 1] - ll;
                        long rl = l == 0 ? 0 : s1[l - 1];
                        right = s1[r] - rl;
                    } else if (l % 2 == 1 && r % 2 == 0) {
                        long ll = l == 1 ? 0 : s1[l - 2];
                        left = s1[r - 1] - ll;
                        long rl = s0[l - 1];
                        right = s0[r] - rl;
                    } else {
                        // l%2 ==1 && r%2==1
                        long ll = l == 1 ? 0 : s1[l - 2];
                        left = s1[r] - ll;
                        long rl = s0[l - 1];
                        right = s0[r - 1] - rl;
                    }

                    if (left == right) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                break;
            }
        }

        if (found) {
            cout << "YES" << endl;
        } else {
            cout << "NO" << endl;
        }
    }

    return 0;
}
