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
        int a, b, c;
        cin >> a >> b >> c;

        if (a == c) {
            cout << b << endl;
        } else if (a == b) {
            cout << c << endl;
        } else {
            cout << a << endl;
        }
    }

    return 0;
}
