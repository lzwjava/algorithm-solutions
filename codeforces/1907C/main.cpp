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
        string s;
        cin >> s;

        list<char> chs;

        for (int i = 0; i < s.size(); i++) {
            chs.push_back(s[i]);
        }

        cout << chs.size() << endl;
    }

    return 0;
}
