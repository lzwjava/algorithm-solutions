#include<iostream>
#include<algorithm>
#include<string>
#include<cassert>
#include<vector>

using namespace std;

int main() {
#ifndef ONLINE_JUDGE
    freopen("2.in", "r", stdin);
#endif
    int t;
    cin>>t;
    while (t>0)
    {
        int n;
        cin >> n;
        vector<int> a(n);
        int maxn = 1000001;
        vector<int> q(maxn);
        for (int i = 0; i < n; i++)
        {
            cin >> a[i];
            q[a[i]] = 1;
        }
        int p = n / 2;
        int c = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n;j++){
                int m1 = min(a[i], a[j]);
                int m2 = max(a[i], a[j]);
                int m = m2 % m1;
                if (q[m] == 0){
                    cout << m2 << ' ' << m1 << '\n';
                    c++;
                    if (c==p){
                        break;
                    }
                }
            }
            if (c==p){
                break;
            }
        }
        t--;
    }
    return 0;
}