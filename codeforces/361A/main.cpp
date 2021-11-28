#include<cstdio>
#include<cstring>
#include<math.h>
#include<stdlib.h>
#include<algorithm>
#include<time.h>
using namespace std;
const int maxn=100+10;

double random()
{
    return (double)rand()/RAND_MAX;
}

int random(int m)
{
    return (int)(random()*(m-1)+0.5);
}

int A[maxn];

void printMatrix(int *A,int n)
{
    for(int i=0;i<n;i++,printf("\n"))
        for(int j=0;j<n;j++)
        {
            if(j) printf(" ");
            int nj=(i+j)%n;
            printf("%d",A[nj]);
        }
}

int getRandom(int a)
{
    int r=random(2);
    if(r==0) return a-1;
    else return a+1;
}

void deal()
{
    int n,k;
    scanf("%d%d",&n,&k);
    int sum=0;
    for(int i=0;i<n-1;i++)
    {
        A[i]=getRandom(k/n);
        sum+=A[i];
    }
    A[n-1]=k-sum;
    printMatrix(A,n);
}

int main()
{
#ifndef ONLINE_JUDGE
  freopen("in.txt","r",stdin);
#endif
    srand(time(NULL));
    deal();
  return 0;
}
