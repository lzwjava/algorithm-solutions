#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <queue>
#include <map>
using namespace std;

char txt[100010], pat[100010];
vector<int>match_pos;
bool flag;

vector<int> compute_prefix(string p)
{
    int m = p.size();
    vector<int> prefix(m+1);
    prefix[0] = 0;
    int k = 0;
    for(int i = 1; i < m; i++)
    {
        while(k>0 and p[k] != p[i]) k = prefix[k-1];
        if(p[k] == p[i]) k++;
        prefix[i] = k;
    }
    return prefix;
}

void KMP_matcher(string txt, string ptrn)
{
    int n = txt.size();
    int m = ptrn.size();
    vector<int> Prefix = compute_prefix(ptrn);
    int q = 0;

    for(int i = 0; i < n; i++)
    {
        while(q > 0 and ptrn[q] != txt[i]) q = Prefix[q-1];
        if(ptrn[q] == txt[i]) q++;
        if(q == m)
        {
            flag = true;
            // match_pos.push_back(i-m+1);
            q = Prefix[q-1];
            break;
        }
    }
    return;
}

int main()
{
    // character array index start from 0
    freopen("1.in", "r", stdin);
    int test;
    scanf("%d", &test);
    while(test--) {
        scanf("%s", txt);
        int q;
        scanf("%d", &q);
        while(q--) {
            flag = false;
            scanf("%s", pat);
            KMP_matcher(txt, pat);
            if(flag) printf("y\n");
            else printf("n\n");
            // match_pos.clear();
        }
    }
    return 0;
}