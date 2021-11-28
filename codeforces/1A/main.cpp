#include<cstdio>
#include<cstring>
#include<math.h>
#include<stdlib.h>
#include<algorithm>
#include<ctime>
#include<iostream>
using namespace std;
const int maxn=1000;

void printlong(long long a)
{
    int t=100000000;
    if(a<=t)
      printf("%d\n",a);
  else 
  {
    printf("%d",a/100000000);
        printf("%08d\n",a%100000000);
  }
}

int main()
{
#ifndef ONLINE_JUDGE
  freopen("in1.txt","r",stdin);
#endif
    int i,j;
    int n,m,a;
    scanf("%d%d%d",&n,&m,&a);
    long long ans=((long long)(n-1)/a+1)*((m-1)/a+1);
    printf("%I64d\n",ans);
    //printlong(ans);
    //printlong(1);
    //printf("%.2lf\n",(double)clock()/CLOCKS_PER_SEC);
  return 0;
}

/*
*/
