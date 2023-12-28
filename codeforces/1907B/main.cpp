#include <algorithm>
#include <cassert>
#include <fstream>
#include <iostream>
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

        for (int i = 0; i < s.size(); i++) {
            char ch = s[i];
            cout << ch << endl;
        }
    }

    return 0;
}
