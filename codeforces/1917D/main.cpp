#include <algorithm>
#include <cassert>
#include <cctype>
#include <cmath>
#include <fstream>
#include <iostream>
#include <list>
#include <string>

using namespace std;

long MOD = 998244353;

struct A {
    int p, q;

    bool operator<(const A& other) const
    {
        if (p < other.p && q < other.q) {
            return true;
        } else if (p > other.p && q > other.q) {
            return false;
        } else {
            double res = p * 1.0 / other.p * pow(2.0, q - other.q);
            return res < 1;
        }
    }
};

int inver(A a[], int i, int j)
{
    if (i == j) {
        return 0;
    }

    int m = (i + j) / 2;
    int left = inver(a, i, m);
    int right = inver(a, m + 1, j);
    int il = i, ir = m + 1;
    int bn = j - i + 1;
    A b[bn];
    int bi = 0;
    int ln = m - i + 1;
    int rn = j - (m + 1) + 1;
    int in = 0;
    while (il <= m || ir <= j) {
        if (il <= m && ir <= j) {
            if (a[il] < a[ir]) {
                b[bi++] = a[il++];
                in += ir;
            } else {
                in += (ln - il);
                b[bi++] = a[ir++];
            }
        } else if (il <= m) {
            in += ir;
            b[bi++] = a[il++];
        } else {
            b[bi++] = a[ir++];
        }
    }
    for (int h = 0; h < bn; h++) {
        a[i + h] = b[h];
    }
    return in;
}

void solve()
{
    int n, k;
    cin >> n >> k;
    int p[n];
    for (int i = 0; i < n; i++) {
        cin >> p[i];
    }
    int q[k];
    for (int i = 0; i < k; i++) {
        cin >> q[i];
    }
    long nk = n * k;
    A a[nk];
    for (int h = 0; h < nk; h++) {
        int i = h / k;
        int j = h - i * k;
        a[h].p = p[i];
        a[h].q = q[j];
    }
    int iv = inver(a, 0, nk - 1);
    cout << iv % MOD << endl;
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
