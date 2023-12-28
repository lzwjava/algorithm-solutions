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

        string ch = "abcdefgh";

        int it = ch.find(s[0]);

        for (int i = 0; i < (int)ch.size(); i++) {

            if (i != it) {
                cout << ch[i] << s[1] << endl;
            }
        }

        for (int i = 1; i <= 8; i++) {

            if (i != s[1] - '0') {
                cout << s[0] << i << endl;
            }
        }

        return 0;
    }
}