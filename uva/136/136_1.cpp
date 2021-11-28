#include<iostream>
#include<algorithm>
#include<string>
#include<cassert>
#include<vector>
#include<cmath>

using namespace std;

int main() {
#ifndef ONLINE_JUDGE    
    freopen("1.in", "r", stdin);
#endif

    vector<int> v;
    int n = 30;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n;j++){
            for (int k = 0; k < n;k++) {
                double p = pow(2, i) * pow(3, j) * pow(5, k);
                if (p < INT32_MAX) {
                    v.push_back((int)p);
                }
            }
        }
    }

    sort(v.begin(), v.end());
    // cout << v.size() << endl;
    // cout << v.at(0) << endl;
    cout << "The 1500'th ugly number is " << v.at(1500-1) << "." << endl;
    return 0;
}
