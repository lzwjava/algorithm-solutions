#include<iostream>
#include<algorithm>
#include<string>
#include<cassert>
#include<queue>
#include<set>

using namespace std;

int main() {
#ifndef ONLINE_JUDGE    
    freopen("1.in", "r", stdin);
#endif
    priority_queue <long, vector<long>, greater<long> > pq;
    pq.push(1);
    set<long> s;
    s.insert(1);

    int n = 0;
    long target = 0;
    int coffi[3] = {2, 3, 5};
    for (;;)
    {
        long top = pq.top();
        n++;
        // cout << top << endl;
        if (n == 1500)
        {
            target = top;
            break;
        }
        pq.pop();
        for (int i = 0; i < 3;i++) {
            if (s.count(top * coffi[i]) == 0)
            {
                s.insert(top * coffi[i]);
                pq.push(top * coffi[i]);
            }
        }
    }
    cout << target << endl;
    return 0;
}
