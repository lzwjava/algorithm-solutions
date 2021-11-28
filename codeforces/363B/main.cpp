#include<cstdio>
#include<cstring>
#include<math.h>
#include<stdlib.h>
#include<algorithm>
using namespace std;
const int maxn=150000+10;
int A[maxn];
int sum[maxn];

void deal()
{
	int n,k;
	scanf("%d%d",&n,&k);
	sum[0]=0;
	int j,maxS=100*maxn;
	for(int i=1;i<=n;i++)
	{
		scanf("%d",&A[i]);
		sum[i]=sum[i-1]+A[i];
		if(i-k>=0 && maxS>sum[i]-sum[i-k])
		{
			maxS=sum[i]-sum[i-k];
			j=i-k+1;
		}
	}
	printf("%d\n",j);
}

int main()
{
#ifndef ONLINE_JUDGE
  freopen("in.txt","r",stdin);
#endif
	deal();
  return 0;
}

/*
*/
