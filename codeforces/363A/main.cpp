#include<cstdio>
#include<cstring>
#include<math.h>
#include<stdlib.h>
#include<algorithm>
using namespace std;
const int maxn=1000;
char s[10][15]={"O-|-OOOO","O-|O-OOO","O-|OO-OO","O-|OOO-O","O-|OOOO-",
								"-O|-OOOO","-O|O-OOO","-O|OO-OO","-O|OOO-O","-O|OOOO-"};

void deal()
{
	int n;
	scanf("%d",&n);
	do
	{
		int a=n%10;
		printf("%s\n",s[a]);
		n=n/10;
	}while(n);
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
