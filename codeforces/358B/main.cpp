#include<cstdio>
#include<cstring>
#include<math.h>
#include<stdlib.h>
#include<algorithm>

#define MAXN 100000+10
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
//#define wordMaxn 5000
using namespace std;


char word[MAXN];
int pp[MAXN];
char mess[MAXN];
int messN;
bool findChar(char ch,char *w,int &p)
{
	do
	{
		if(p>=messN)
			return false;
		if(ch==w[p])
		{
			p++;
			return true;
		}else p++;
	}while(1);
}

char w[MAXN];

int main()
{
#ifndef ONLINE_JUDGE
  //freopen("in.txt","r",stdin);
#endif
	int n;
	scanf("%d",&n);
	word[0]='\0';
	pp[0]=0;
	int wn=0;
	for(int i=0;i<n;i++)
	{
		scanf("%s",w);
		int lenW=strlen(w);
		for(int j=0;j<lenW;j++)
		{
			word[wn++]=w[j];
		}
		pp[i+1]=wn;
		//printf("%d\n",pp[i+1]);
	}
	scanf("%s",mess);
	messN=strlen(mess);
	int p=0;
	bool ok=true;
	int i;
	for(i=0;i<n;i++)
	{
		if(!findChar('<',mess,p) || !findChar('3',mess,p))
		{
			ok=false;
			//printf("ok\n");
			break;
		}
		for(int j=pp[i];j<pp[i+1];j++)
		{
			if(!findChar(word[j],mess,p))
			{
				//printf("ok1\n");
				ok=false;
				break;
			}
			//printf("%c\n",word[j]);
		}	
		if(!ok)
		{
			break;
		}
	}
	if(!findChar('<',mess,p) || !findChar('3',mess,p))
		ok=false;
	if(ok)
	{
		printf("yes\n");
	}else
	{
		printf("no\n");
	}
  return 0;
}

/*
*/
