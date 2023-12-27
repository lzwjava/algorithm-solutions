// https://codeforces.com/contest/1902/submission/235568838

#include <iostream>
#include <vector>
#include <string>
#include <cstring>
#include <fstream>

using namespace std;

typedef long long ll;
typedef long double ld;

const int MAX_N = 2e5 + 7, MAX_LG = 20;

vector<int> adjList[MAX_N];
pair<int, int> base[MAX_N][MAX_LG];
int nextNode[MAX_N][MAX_LG], pre[MAX_N], post[MAX_N], preCnt, postCnt;
int dist[MAX_N], val[MAX_N], basis[MAX_LG];

void addBasis(int x) {
    for (int i = MAX_LG - 1; i >= 0; --i) {
        if (x & (1 << i)) {
            if (!basis[i]) {
                basis[i] = x;
                break;
            } else
                x ^= basis[i];
        }
    }
}

void DFS(int node, int parent) {
    pair<int, int> curr = {val[node], dist[node]};
    for (int i = MAX_LG - 1; i >= 0; --i) {
        if (curr.first & (1 << i)) {
            if (curr.second > base[node][i].second)
                swap(curr, base[node][i]);
            if (curr.second == -1)
                break;
            curr.first ^= base[node][i].first;
        }
    }
    pre[node] = ++preCnt;
    for (auto n : adjList[node])
        if (n != parent) {
            dist[n] = dist[node] + 1;
            nextNode[n][0] = node;
            for (int j = 1; j < MAX_LG; ++j)
                nextNode[n][j] = nextNode[nextNode[n][j - 1]][j - 1];
            for (int j = 0; j < MAX_LG; ++j)
                base[n][j] = base[node][j];
            DFS(n, node);
        }
    post[node] = ++postCnt;
}

bool isAnc(int a, int b) {
    return pre[a] <= pre[b] && post[a] >= post[b];
}

int LCA(int a, int b) {
    if (isAnc(a, b))
        return a;
    for (int i = MAX_LG - 1; i >= 0; --i)
        if (!isAnc(nextNode[a][i], b))
            a = nextNode[a][i];
    return nextNode[a][0];
}

int main() {
    // Redirect stdin if 'in.txt' exists
    if (ifstream("in.txt")) {
        freopen("in.txt", "r", stdin);
    }

    ios_base::sync_with_stdio(0);
    cin.tie(0);
    int n;
    cin >> n;
    for (int i = 0; i < n; ++i) {
        cin >> val[i];
        for (int j = 0; j < MAX_LG; ++j)
            base[i][j].second = -1;
    }
    for (int i = 0; i < n - 1; ++i) {
        int a, b;
        cin >> a >> b;
        --a; --b;
        adjList[a].push_back(b);
        adjList[b].push_back(a);
    }
    DFS(0, 0);
    int q;
    cin >> q;
    while (q--) {
        int a, b, x;
        cin >> a >> b >> x;
        --a; --b;
        int ancestor = LCA(a, b);
        for (int i = 0; i < MAX_LG; ++i) basis[i] = 0;
        for (int i = 0; i < MAX_LG; ++i)
            if (base[a][i].second >= dist[ancestor]) addBasis(base[a][i].first);
        for (int i = 0; i < MAX_LG; ++i)
            if (base[b][i].second >= dist[ancestor]) addBasis(base[b][i].first);
        for (int i = MAX_LG - 1; i >= 0; --i)
            if (x & (1 << i))
                x ^= basis[i];
        cout << (x ? "NO" : "YES") << '\n';
    }
}
