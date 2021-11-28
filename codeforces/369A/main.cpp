#include<cstdio>
#include<cstring>
#include<math.h>
#include<stdlib.h>
#include<algorithm>
#include<assert.h>
#include<time.h>

using namespace std;
const int maxn=1000;

void deal()
{
	int n,m,k;
	scanf("%d%d%d",&n,&m,&k);
	int cnt=0;
	for(int i=0;i<n;i++)
	{
		int a;
		scanf("%d",&a);
		if(a==1)
		{
			if(m>0) m--;
			else cnt++;
		}else
		{
			if(k>0) k--;
			else if(m>0) m--;
			else cnt++;
		}
	}
	printf("%d\n",cnt);
}

int main()
{
#ifndef ONLINE_JUDGE
  //freopen("in.txt","r",stdin);
#endif
	deal();
	//printf("%.2lf\n",(double)clock()/CLOCKS_PER_SEC); 
  return 0;
}

/*���ǰ�ǵڶ��࣬��ô������������ӡ�����ǵ�һ�࣬��ôֻ�������롣��ô���죬���������ӣ������룬Ҫ�Եڶ���ʳ���Ҹ����ĸ��ء���������룬��ô�ҿ��ԳԵ�һ���඼�У�����������ӣ���ôֻ���ԳԵڶ��ࡣ
*/
