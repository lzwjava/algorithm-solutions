// https://codeforces.com/contest/1902/submission/235568838

#include <iostream>
#include <vector>
#include <string>
#include <cstring>
#include <fstream>

using namespace std;

typedef long long ll;
typedef long double ld;

const int LIMIT = 2e5 + 7, LOG_SIZE = 20;

vector<int> graph[LIMIT];
pair<int, int> base[LIMIT][LOG_SIZE];
int nextNode[LIMIT][LOG_SIZE], preOrder[LIMIT], postOrder[LIMIT], preOrderCount, postOrderCount;
int distanceFromRoot[LIMIT], nodeValue[LIMIT], linearBasis[LOG_SIZE];

void addLinearBasis(int x) {
    for (int i = LOG_SIZE - 1; i >= 0; --i) {
        if (x & (1 << i)) {
            if (!linearBasis[i]) {
                linearBasis[i] = x;
                break;
            } else
                x ^= linearBasis[i];
        }
    }
}

void depthFirstSearch(int currentNode, int parent) {
    pair<int, int> currentBase = {nodeValue[currentNode], distanceFromRoot[currentNode]};
    for (int i = LOG_SIZE - 1; i >= 0; --i) {
        if (currentBase.first & (1 << i)) {
            if (currentBase.second > base[currentNode][i].second)
                swap(currentBase, base[currentNode][i]);
            if (currentBase.second == -1)
                break;
            currentBase.first ^= base[currentNode][i].first;
        }
    }
    preOrder[currentNode] = ++preOrderCount;
    for (auto neighbor : graph[currentNode])
        if (neighbor != parent) {
            distanceFromRoot[neighbor] = distanceFromRoot[currentNode] + 1;
            nextNode[neighbor][0] = currentNode;
            for (int j = 1; j < LOG_SIZE; ++j)
                nextNode[neighbor][j] = nextNode[nextNode[neighbor][j - 1]][j - 1];
            for (int j = 0; j < LOG_SIZE; ++j)
                base[neighbor][j] = base[currentNode][j];
            depthFirstSearch(neighbor, currentNode);
        }
    postOrder[currentNode] = ++postOrderCount;
}

bool isAncestor(int a, int b) {
    return preOrder[a] <= preOrder[b] && postOrder[a] >= postOrder[b];
}

int lowestCommonAncestor(int a, int b) {
    if (isAncestor(a, b))
        return a;
    for (int i = LOG_SIZE - 1; i >= 0; --i)
        if (!isAncestor(nextNode[a][i], b))
            a = nextNode[a][i];
    return nextNode[a][0];
}

int main() {
    // Redirecting stdin to read from a file if 'in.txt' exists
    if (ifstream("in.txt")) {
        freopen("in.txt", "r", stdin);
    }

    ios_base::sync_with_stdio(0);
    cin.tie(0);
    int numNodes;
    cin >> numNodes;
    for (int i = 0; i < numNodes; ++i) {
        cin >> nodeValue[i];
        for (int j = 0; j < LOG_SIZE; ++j)
            base[i][j].second = -1;
    }
    for (int i = 0; i < numNodes - 1; ++i) {
        int nodeA, nodeB;
        cin >> nodeA >> nodeB;
        --nodeA;
        --nodeB;
        graph[nodeA].push_back(nodeB);
        graph[nodeB].push_back(nodeA);
    }
    depthFirstSearch(0, 0);
    int numQueries;
    cin >> numQueries;
    while (numQueries--) {
        int nodeA, nodeB, xorValue;
        cin >> nodeA >> nodeB >> xorValue;
        --nodeA;
        --nodeB;
        int commonAncestor = lowestCommonAncestor(nodeA, nodeB);
        for (int i = 0; i < LOG_SIZE; ++i)
            linearBasis[i] = 0;
        for (int i = 0; i < LOG_SIZE; ++i)
            if (base[nodeA][i].second >= distanceFromRoot[commonAncestor]) addLinearBasis(base[nodeA][i].first);
        for (int i = 0; i < LOG_SIZE; ++i)
            if (base[nodeB][i].second >= distanceFromRoot[commonAncestor]) addLinearBasis(base[nodeB][i].first);
        for (int i = LOG_SIZE - 1; i >= 0; --i)
            if (xorValue & (1 << i))
                xorValue ^= linearBasis[i];
        cout << (xorValue ? "NO" : "YES") << '\n';
    }
}
