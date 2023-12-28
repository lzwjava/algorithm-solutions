#include <algorithm>
#include <cassert>
#include <cctype>
#include <fstream>
#include <iostream>
#include <list>
#include <string>
using namespace std;

const int MOD = 1000000007;

void solve()
{
    int N;
    cin >> N;
    vector<long long> A(N);
    for (int i = 0; i < N; i++) {
        cin >> A[i];
    }
    for (int i = 0; i < N; i++) {
        if (i % 2) {
            A[i] *= -1;
#
        }
    }
    map<long long, int> pos;
    pos[0] = 0;
    long long cur = 0;
    for (int i = 1; i <= N; i++) {
        cur += A[i - 1];
        if (pos.count(cur)) {
            cout << "YES\n";
            return;
        }
        pos[cur] = i;
    }
    cout << "NO\n";
}

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int T;
    cin >> T;
    while (T--) {
        solve();
    }

    return 0;
}
