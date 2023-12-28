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

        list<pair<char, int>> ls;
        list<pair<char, int>> us;

        for (int i = 0; i < s.size(); i++) {
            char ch = s[i];
            if (ch == 'b') {
                ls.pop_back();
            } else if (ch == 'B') {
                us.pop_back();
            } else {
                if (islower(ch)) {
                    ls.push_back({ ch, i })
                } else {
                    us.push_back({ ch, i })
                }
            }
        }

        string ans;

        ils = ls.begin();
        ius = us.begin();

        while (ils != ls.end() || ius != us.end()) {
            if (ils != ls.end() && ius != us.end()) {
                if ((*ils).second < (*ius).second) {
                    ans += (*ils).first;
                    ++ils;
                } else {
                    ans += (*ius).first;
                    ++ius;
                }
            } else if (ils != ls.end()) {
                ans += (*ils).first;
                ++ils;
            } else {
                ans += (*ius).first;
                ++ius;
            }
        }

        cout << ans << endl;
    }

    return 0;
}
