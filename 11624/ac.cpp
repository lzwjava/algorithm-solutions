//Sinha Saab
//NARUTO Fan



#include <cstdio>
#include <iostream>
#include <algorithm>
#include <string.h>
#include <vector>
#include <set>
#include <map>
#include <queue>
#include <list>
#include <iterator>
#include <stack>
#include <math.h>

#define ll long long int
#define maxN 1000
#define maxVal 100000000
#define minVal -100000000
#define mod 1000000007LL

#define gcd(a,b) __gcd(a,b)

using namespace std;

int n,m;
char a[maxN+5][maxN+5];
int fire[maxN+5][maxN+5];
int visit[maxN+5][maxN+5];
queue<pair<int,int> > q;

int r[]={0,0,1,-1};
int c[]={1,-1,0,0};

void setFire()
{
    int k,ui,uj,vi,vj;
    while(!q.empty())
    {
        ui=(q.front()).first;
        uj=(q.front()).second;
        q.pop();
        for(k=0;k<4;k++)
        {
            vi=ui+r[k];
            vj=uj+c[k];
            if(vi>=0&&vi<n&&vj>=0&&vj<m&&a[vi][vj]!='#'&&
                fire[vi][vj]==maxVal)
            {
                fire[vi][vj]=fire[ui][uj]+1;
                q.push(make_pair(vi,vj));
            }
        }
    }
}

void bfs(int ui,int uj)
{
    int k,vi,vj;
    q.push(make_pair(ui,uj));
    visit[ui][uj]=0;
    while(!q.empty())
    {
        ui=(q.front()).first;
        uj=(q.front()).second;
        q.pop();
        for(k=0;k<4;k++)
        {
            vi=ui+r[k];
            vj=uj+c[k];
            if(vi>=0&&vi<n&&vj>=0&&vj<m&&a[vi][vj]!='#'&&
                visit[vi][vj]==maxVal)
            {
                visit[vi][vj]=visit[ui][uj]+1;
                q.push(make_pair(vi,vj));
            }
        }
    }
}

int main()
{
    #ifndef ONLINE_JUDGE
        freopen("2.in","r",stdin);
        //freopen("out.txt","w",stdout);
    #endif
    int t,i,j,z;
    scanf("%d",&t);
    while(t--)
    {
        scanf("%d%d",&n,&m);
        for(i=0;i<n;i++)
        {
            scanf("%s",a[i]);
            for(j=0;j<m;j++)
                fire[i][j]=maxVal,visit[i][j]=maxVal;
        }
        for(i=0;i<n;i++)
            for(j=0;j<m;j++)
            {
                if(a[i][j]=='F')
                {
                    q.push(make_pair(i,j));
                    fire[i][j]=0;
                }
            }
        setFire();
        for(i=0;i<n;i++)
            for(j=0;j<m;j++)
                if(a[i][j]=='J')
                    bfs(i,j);
        z=maxVal;
        for(i=0;i<n;i++)
        {
            if(visit[i][0]<fire[i][0])
                z=min(z,visit[i][0]);
            if(visit[i][m-1]<fire[i][m-1])
                z=min(z,visit[i][m-1]);
        }
        for(j=0;j<m;j++)
        {
            if(visit[0][j]<fire[0][j])
                z=min(z,visit[0][j]);
            if(visit[n-1][j]<fire[n-1][j])
                z=min(z,visit[n-1][j]);
        }
        if(z!=maxVal)
            printf("%d\n",z+1);
        else
            printf("IMPOSSIBLE\n");
    }
    return 0;
}