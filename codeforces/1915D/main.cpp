#include <algorithm>
#include <cassert>
#include <cctype>
#include <fstream>
#include <iostream>
#include <list>
#include <string>

using namespace std;

bool vowel(char c)
{
    return c == 'a' || c == 'e';
}

bool consonant(char c)
{
    return c == 'b' || c == 'c' || c == 'd';
}

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

        string ns;

        int i = n - 1;

        while (i >= 0) {
            if (i != n - 1) {
                ns += '.';
            }
            if (vowel(s[i])) {
                ns += s[i--];
                ns += s[i--];
            } else {
                ns += s[i--];
                ns += s[i--];
                ns += s[i--];
            }
        }

        reverse(ns.begin(), ns.end());

        cout << ns << endl;
    }

    return 0;
}
