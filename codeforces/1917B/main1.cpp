#include <algorithm>
#include <cassert>
#include <cctype>
#include <fstream>
#include <iostream>
#include <list>
#include <set>
#include <string>

using namespace std;

set<string> all;

void solve()
{
    all.clear();
    int n;
    cin >> n;
    string s;
    cin >> s;
    set<char> chs;
    long long sum = 0;
    for (int i = 0; i < n; i++) {
        chs.insert(s[i]);
        sum += chs.size();
    }
    cout << sum << endl;
}

int main()
{
    if (ifstream("in.txt")) {
        freopen("in.txt", "r", stdin);
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }

    return 0;
}
