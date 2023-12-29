#include <algorithm>
#include <cassert>
#include <cctype>
#include <fstream>
#include <iostream>
#include <list>
#include <string>

using namespace std;

struct P {
    int a, b;
    P(int av, int bv)
        : a(av)
        , b(bv)
    {
    }
};

bool compareByA(P& o1, P& o2)
{
    return o1.a < o2.a;
}

void solve()
{
    int n;
    cin >> n;
    vector<P> ps;
    for (int i = 0; i < n; i++) {
        int ai, bi;
        cin >> ai >> bi;
        ps.emplace_back(ai, bi);
    }

    sort(ps.begin(), ps.end(), compareByA);

    int b[n];
    for (int i = 0; i < n; i++) {
        b[i] = ps[i].b;
    }

    long sum = 0;

    for (int i = 1; i < n; i++) {
        for (int j = i; j > 0; j--) {
            if (b[j] < b[j - 1]) {
                int t = b[j];
                b[j] = b[j - 1];
                b[j - 1] = t;
                sum++;
            } else {
                break;
            }
        }
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
