// https://codeforces.com/contest/1902/submission/235568838

#include <iostream>
#include <vector>
#include <string>
#include <cstring>
#include <fstream>


using namespace std;
typedef long long ll;
typedef long double ld;
#define rep(a, b) for (int a = 0; a < (b); ++a)
#define st first
#define nd second
#define pb push_back
#define all(a) a.begin(), a.end()
const int LIM = 2e5 + 7, LG = 20;
vector<int> V[LIM];
pair<int, int> baza[LIM][LG];
int nxt[LIM][LG], pre[LIM], post[LIM], lpre, lpost;
int odl[LIM], T[LIM], pyt[LG];
void dodaj(int x)
{
    for (int i = LG - 1; i >= 0; --i)
        if (x & (1 << i)) {
            if (!pyt[i]) {
                pyt[i] = x;
                break;
            } else
                x ^= pyt[i];
        }
}
void DFS(int x, int o)
{
    pair<int, int> akt = { T[x], odl[x] };
    for (int i = LG - 1; i >= 0; --i) {
        if (akt.st & (1 << i)) {
            if (akt.nd > baza[x][i].nd)
                swap(akt, baza[x][i]);
            if (akt.nd == -1)
                break;
            akt.st ^= baza[x][i].st;
        }
    }
    pre[x] = ++lpre;
    for (auto i : V[x])
        if (i != o) {
            odl[i] = odl[x] + 1;
            nxt[i][0] = x;
            for (int j = 1; j < LG; ++j)
                nxt[i][j] = nxt[nxt[i][j - 1]][j - 1];
            rep(j, LG) baza[i][j] = baza[x][j];
            DFS(i, x);
        }
    post[x] = ++lpost;
}
bool oc(int a, int b)
{
    return pre[a] <= pre[b] && post[a] >= post[b];
}
int lca(int a, int b)
{
    if (oc(a, b))
        return a;
    for (int i = LG - 1; i >= 0; --i)
        if (!oc(nxt[a][i], b))
            a = nxt[a][i];
    return nxt[a][0];
}
int main()
{
     // Redirecting stdin to read from a file if 'in.txt' exists
    if (ifstream("in.txt")) {
        freopen("in.txt", "r", stdin);
    }


    ios_base::sync_with_stdio(0);
    cin.tie(0);
    int n;
    cin >> n;
    rep(i, n)
    {
        cin >> T[i];
        rep(j, LG) baza[i][j].nd = -1;
    }
    rep(i, n - 1)
    {
        int a, b;
        cin >> a >> b;
        --a;
        --b;
        V[a].pb(b);
        V[b].pb(a);
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
        rep(i, LG) pyt[i] = 0;
        rep(i, LG) if (baza[a][i].nd >= odl[c]) dodaj(baza[a][i].st);
        rep(i, LG) if (baza[b][i].nd >= odl[c]) dodaj(baza[b][i].st);
        for (int i = LG - 1; i >= 0; --i)
            if (x & (1 << i))
                x ^= pyt[i];
        cout << (x ? "NO" : "YES") << '\n';
    }
}