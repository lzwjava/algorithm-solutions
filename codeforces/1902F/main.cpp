// https://codeforces.com/contest/1902/submission/235568838

#include <iostream>
#include <vector>
#include <string>
#include <cstring>
#include <fstream>

using namespace std;

typedef long long ll;
typedef long double ld;

const int LIM = 2e5 + 7, LG = 20;

vector<int> V[LIM];
pair<int, int> baza[LIM][LG];
int nxt[LIM][LG], pre[LIM], post[LIM], lpre, lpost;
int odl[LIM], T[LIM], pyt[LG];

void dodaj(int x) {
    for (int i = LG - 1; i >= 0; --i) {
        if (x & (1 << i)) {
            if (!pyt[i]) {
                pyt[i] = x;
                break;
            } else
                x ^= pyt[i];
        }
    }
}

void DFS(int x, int o) {
    pair<int, int> akt = {T[x], odl[x]};
    for (int i = LG - 1; i >= 0; --i) {
        if (akt.first & (1 << i)) {
            if (akt.second > baza[x][i].second)
                swap(akt, baza[x][i]);
            if (akt.second == -1)
                break;
            akt.first ^= baza[x][i].first;
        }
    }
    pre[x] = ++lpre;
    for (auto i : V[x])
        if (i != o) {
            odl[i] = odl[x] + 1;
            nxt[i][0] = x;
            for (int j = 1; j < LG; ++j)
                nxt[i][j] = nxt[nxt[i][j - 1]][j - 1];
            for (int j = 0; j < LG; ++j)
                baza[i][j] = baza[x][j];
            DFS(i, x);
        }
    post[x] = ++lpost;
}

bool oc(int a, int b) {
    return pre[a] <= pre[b] && post[a] >= post[b];
}

int lca(int a, int b) {
    if (oc(a, b))
        return a;
    for (int i = LG - 1; i >= 0; --i)
        if (!oc(nxt[a][i], b))
            a = nxt[a][i];
    return nxt[a][0];
}

int main() {
    // Redirecting stdin to read from a file if 'in.txt' exists
    if (ifstream("in.txt")) {
        freopen("in.txt", "r", stdin);
    }

    ios_base::sync_with_stdio(0);
    cin.tie(0);
    int n;
    cin >> n;
    for (int i = 0; i < n; ++i) {
        cin >> T[i];
        for (int j = 0; j < LG; ++j)
            baza[i][j].second = -1;
    }
    for (int i = 0; i < n - 1; ++i) {
        int a, b;
        cin >> a >> b;
        --a;
        --b;
        V[a].push_back(b);
        V[b].push_back(a);
    }
    DFS(0, 0);
    int q;
    cin >> q;
    while (q--) {
        int a, b, x;
        cin >> a >> b >> x;
        --a;
        --b;
        int c = lca(a, b);
        for (int i = 0; i < LG; ++i)
            pyt[i] = 0;
        for (int i = 0; i < LG; ++i)
            if (baza[a][i].second >= odl[c]) dodaj(baza[a][i].first);
        for (int i = 0; i < LG; ++i)
            if (baza[b][i].second >= odl[c]) dodaj(baza[b][i].first);
        for (int i = LG - 1; i >= 0; --i)
            if (x & (1 << i))
                x ^= pyt[i];
        cout << (x ? "NO" : "YES") << '\n';
    }
}
