#include<iostream>
#include<algorithm>
#include<string>
#include<map>
#include<set>
#include<stack>
#include<cassert>

using namespace std;

typedef set<int> Set;

map<Set, int> caches;
map<int, Set> rcaches;
int cache_n = 0;

int id_by_set(set<int> s) {
    if(caches.count(s) > 0) {
        return caches[s];
    } else {
        caches[s] = cache_n;
        rcaches[cache_n] = s;
        int id = cache_n;
        cache_n++;
        return id;
    }
}

int main() {
#ifndef ONLINE_JUDGE    
    freopen("1.in", "r", stdin);
#endif
    int T;
    cin >> T;
    for (int i = 0; i < T;i++) {
        caches.clear();

        int N;
        cin >> N;
        stack<int> st;
        for (int j = 0; j < N; j++)
        {
            string s;
            cin >> s;
            if (s.compare("PUSH") == 0) {
                set<int> sa;
                int id = id_by_set(sa);
                st.push(id);
            }
            else if (s.compare("DUP") == 0)                        
            {
                st.push(st.top());
            }
            else  {
                int id1 = st.top();
                st.pop();
                int id2 = st.top();
                st.pop();
                Set s1 = rcaches[id1];
                Set s2 = rcaches[id2];
                Set s3;                
                if (s.compare("UNION") == 0)
                {            
                    set_union(s1.begin(), s1.end(), s2.begin(),
                          s2.end(), inserter(s3, s3.begin()));            
                } else if (s.compare("INTERSECT") == 0)
                {     
                    set_intersection(s1.begin(), s1.end(), s2.begin(),
                                 s2.end(), inserter(s3, s3.begin()));   
                }
                else if (s.compare("ADD") == 0)
                {
                    s3 = Set(s2);
                    s3.insert(id1);
                }
                st.push(id_by_set(s3));
            }
            int top_id = st.top();
            Set top_set = rcaches[top_id];
            cout << top_set.size() << endl;
        }
        cout << "***" << endl;
    }
    return 0;
}
