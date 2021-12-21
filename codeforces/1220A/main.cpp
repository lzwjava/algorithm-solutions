#include <algorithm>
#include <cassert>
#include <iostream>
#include <string>

using namespace std;

int main() {
#ifndef ONLINE_JUDGE
  freopen("1.in", "r", stdin);
#endif
  int n;
  string s;
  cin >> n >> s;
  int one = 0, zero = 0;
  for (int i = 0; i < n; i++) {
    if (s[i] == 'z') {
      zero++;
    } else if (s[i] == 'n') {
      one++;
    }
  }
  bool first = true;
  for (int i = 0; i < one; i++) {
    if (!first) {
      cout << ' ';
    }
    first = false;
    cout << 1;
  }
  for (int i = 0; i < zero; i++) {
    if (!first) {
      cout << ' ';
    }
    first = false;
    cout << 0;
  }
  cout << '\n';
  return 0;
}
