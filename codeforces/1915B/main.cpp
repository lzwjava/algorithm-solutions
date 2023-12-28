#include <algorithm>
#include <cassert>
#include <cctype>
#include <fstream>
#include <iostream>
#include <list>
#include <string>
#include <set>

using namespace std;

int main()
{
    if (ifstream("in.txt")) {
        freopen("in.txt", "r", stdin);
    }

    int t;
    cin >> t;
    while (t--) {
        char ans;
        for (int i = 0; i < 3; i++) {
            string s;
            cin >> s;
            bool hasQ = false;
            set<char> sc;
            for (int j = 0; j < 3; j++) {
                if (s[j] == '?') {
                    hasQ = true;
                } else {
                    sc.insert(s[j]);
                }
            }
            if (hasQ) {
                set<char> all = { 'A',
                    'B',
                    'C' };
                for (const char& ch : sc) {
                    all.erase(sc)
                }
                if (!all.empty()) {
                    ans = *all.begin()
                }
            }
        }
        cout << ans << endl;
    }

    return 0;
}
