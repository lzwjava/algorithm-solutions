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
        string s;

        cin >> s;

        string ans;

        list<char> chs;

        for (int i = 0; i < s.size(); i++) {
            char ch = s[i];

            if (ch == 'b') {

                auto rit = find_if(chs.rbegin(), chs.rend(), ::islower);
                if (rit != chs.rend()) {
                    chs.erase(next(rit).base());
                }
            } else if (ch == 'B') {
                auto rit = find_if(chs.rbegin(), chs.rend(), ::isupper);
                if (rit != chs.rend()) {
                    chs.erase(next(rit).base());
                }
            } else {
                chs.push_back(ch);
            }
        }

        ans = string(chs.begin(), chs.end());

        cout << ans << endl;
    }

    return 0;
}
