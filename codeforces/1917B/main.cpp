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

void dfs(string s)
{
    all.insert(s);
    if (s.size() == 1) {
        return;
    }

    string s1 = s.substr(1);
    dfs(s1);
    string s2 = s[0] + s.substr(2);
    dfs(s2);
}

void solve()
{
    all.clear();
    int n;
    cin >> n;
    string s;
    cin >> s;
    dfs(s);
    cout << all.size() << endl;
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
