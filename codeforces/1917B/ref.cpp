// https://github.com/tar3q-az1z/Codeforces-solutions/blob/main/1917B.cpp
#include <algorithm>
#include <cassert>
#include <cctype>
#include <fstream>
#include <iostream>
#include <list>
#include <numeric>
#include <set>
#include <string>
using namespace std;

int main()
{
    if (ifstream("in.txt")) {
        freopen("in.txt", "r", stdin);
    }
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;
    using lli = long long int;
    using vii = vector<lli>;
    for (int tc = 1; tc <= t; ++tc) {
        long long int n;
        cin >> n;

        string s;
        cin >> s;

        set<char> chars;
        vector<long long int> sums;

        for (long long int i = 0; i < n; ++i) {
            chars.insert(s[i]);
            sums.push_back(chars.size());
        }

        long long int total = accumulate(sums.begin(), sums.end(), 0LL);
        cout << total << "\n";
    }

    return 0;
}
