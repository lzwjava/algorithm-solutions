#include<cstdio>
#include<cstring>
#include<math.h>
#include<stdlib.h>
#include<algorithm>
#include<assert.h>
#include<time.h>

using namespace std;
const int maxn=1010;
int A[maxn];
int n,k,l,r,sAll,sk;
int sum;
void makeA()
{
	int sk1=l*k;
	for(int i=0;i<n;i++)
	{
		A[i]=l;
		sum+=l;
	}
	while(sk1<sk)
	{
		for(int i=0;i<k;i++)
		{
			if(A[i]<r && sk1<sk)
			{
				A[i]++;
				sk1++;
				sum++;
			}
			if(sk1==sk)
			{
				break;
			}
		}
	}
	int maxV=A[k-1];
	for(int i=k;i<n;i++)
	{
		while(sum<sAll && A[i]<maxV)
		{
			A[i]++;
			sum++;
		}
		if(sum==sAll)
		{
			break;
		}
	}
}

int getK()
{
	int s=0;
	for(int i=0;i<k;i++) s+=A[i];
	return s;
}

void makeGoodA()
{
	int sk1=getK();
	int dist=sk1-sk;
	int i;
}

void printA()
{
	printf("%d",A[0]);
	for(int i=1;i<n;i++)
		printf(" %d",A[i]);
	printf("\n");
}

void deal()
{	
	scanf("%d%d%d%d%d%d",&n,&k,&l,&r,&sAll,&sk);
	sum=0;
	makeA();
	makeGoodA();
	printA();
}

int main()
{
#ifndef ONLINE_JUDGE
  freopen("in.txt","r",stdin);
#endif
	deal();
	//printf("%.2lf\n",(double)clock()/CLOCKS_PER_SEC); 
  return 0;
}

/*
*/
