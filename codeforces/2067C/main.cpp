#include <iostream>
#include <string>
#include <algorithm>
#include <fstream>

using namespace std;

int main() {
  ifstream cin("in.txt");
  int t;
  cin >> t;
  while (t--) {
    int n;
    cin >> n;
    int ans = 0;
    string s = to_string(n);
    bool hasSeven = false;
    for (char c : s) {
      if (c == '7') {
        hasSeven = true;
        break;
      }
    }
    if (hasSeven) {
      cout << 0 << endl;
      continue;
    }

    ans = 1;
    cout << 1 << endl;
  }
  return 0;
}
