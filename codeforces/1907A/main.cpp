#include <algorithm>
#include <cassert>
#include <fstream>
#include <iostream>
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
    for (int i = 0; i < t; i++) {
        string s;
        cin >> s;

        char c1 = s[0];
        char c2 = s[1];

        string sa = "abcdefgh";
        string sb = "12345678";

        set<string> ans;

        for (char c : sa) {
            string cs = string(1, c) + string(1, c2);
            ans.insert(cs)
        }
    }

    return 0;
}
