#include<cstdio>
#include<cstring>
#include<math.h>
#include<stdlib.h>
#include<algorithm>

#define MAXN 1010
#define SZ(X) ((int)(X).size())
#define ALL(X) (X).begin(), (X).end()
#define REP(I, N) for (int I = 0; I < (N); ++I)
#define REPP(I, A, B) for (int I = (A); I < (B); ++I)
#define REPC(I, C) for (int I = 0; !(C); ++I)
#define RI(X) scanf("%d", &(X))
#define RII(X, Y) scanf("%d%d", &(X), &(Y))
#define RIII(X, Y, Z) scanf("%d%d%d", &(X), &(Y), &(Z))
#define DRI(X) int (X); scanf("%d", &X)
#define DRII(X, Y) int X, Y; scanf("%d%d", &X, &Y)
#define DRIII(X, Y, Z) int X, Y, Z; scanf("%d%d%d", &X, &Y, &Z)
#define RS(X) scanf("%s", (X))
#define CASET int ___T, case_n = 1; scanf("%d ", &___T); while (___T-- > 0)
#define MP make_pair
#define PB push_back
#define MS0(X) memset((X), 0, sizeof((X)))
#define MS1(X) memset((X), -1, sizeof((X)))
#define LEN(X) strlen(X)
#define F first
#define S second

using namespace std;

typedef struct
{
	int x,y;
}Line;

Line Ln[MAXN];

int inLine(int x,Line L)
{
	return (x>L.x)&& (x<L.y);
}

int main()
{
#ifndef ONLINE_JUDGE
  //freopen("in1.txt","r",stdin);
#endif
	int n;
	scanf("%d",&n);
	int a,b;
	scanf("%d",&a);
	int i;
	for(i=0;i<n-1;i++)
	{
		scanf("%d",&b);
		bool ok=true;
		int min=a>b ? b:a;
		int max=a>b ? a:b;
		for(int j=0;j<i;j++)
		{
			if(Ln[j].x==min || Ln[j].y==max)
			{
				if(Ln[j].x==min && Ln[j].y==max)
				{
					ok=false;
					break;
				}
			}else if((inLine(min,Ln[j]) && !inLine(max,Ln[j])) ||
				       (inLine(max,Ln[j]) && !inLine(min,Ln[j])))
			{
				ok=false;
				break;
			}
		}
		if(ok)
		{
   		Ln[i].x=min;
	  	Ln[i].y=max;
		  a=b;
		}else
		{
			break;
		}
	}
 	// for(int j=0;j<i;j++)
	// {
	// 	printf("x=%d y=%d\n",Ln[j].x,Ln[j].y);
	// }
	if(i==n-1)
	{
		printf("no\n");
	}else printf("yes\n");
  return 0;
}

/*
*/
