#include <iostream>
#include <vector>
#include <string>
#include <cstring>
#include <fstream>

using namespace std;

class TrieNode {
public:
    TrieNode* children[26];
    int cnt;

    TrieNode() {
        memset(children, 0, sizeof(children));
        cnt = 0;
    }
};

TrieNode* createNode() {
    return new TrieNode();
}

int main() {
    // Redirecting stdin to read from a file if 'in.txt' exists
    if (ifstream("in.txt")) {
        freopen("in.txt", "r", stdin);
    }

    int n;
    cin >> n;
    cin.ignore();  // Consume the newline character

    vector<string> strs(n);

    for (int i = 0; i < n; i++) {
        getline(cin, strs[i]);
    }

    TrieNode* root = createNode();
    int sm = 0;

    for (int i = 0; i < n; i++) {
        sm += strs[i].length();
        TrieNode* r = root;

        for (int j = 0; j < strs[i].length(); j++) {
            int v = strs[i][j] - 'a';

            if (!r->children[v]) {
                r->children[v] = createNode();
            }

            r = r->children[v];
            r->cnt += 1;
        }
    }

    int ans = 2 * n * sm;

    for (int i = 0; i < n; i++) {
        TrieNode* r = root;

        for (int j = strs[i].length() - 1; j >= 0; j--) {
            int v = strs[i][j] - 'a';

            if (!r->children[v]) {
                break;
            }

            r = r->children[v];
            ans -= 2 * r->cnt;
        }
    }

    cout << ans << endl;

    return 0;
}
