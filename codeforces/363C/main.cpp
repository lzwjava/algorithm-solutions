#include<cstdio>
#include<cstring>
#include<math.h>
#include<stdlib.h>
#include<algorithm>
using namespace std;
const int maxn=200000+10;
char s[maxn];
char s1[maxn];

void delThree(char *s)
{
	int len=strlen(s);
	int p1=0;
	for(int i=0;i<len;i++)
	{
		char ch=s[i];
		int j;
		for(j=i;j<len;j++)
			if(s[j+1]!=ch) break;
		int len1=j-i+1;
		if(len1>=3)
		{
			s1[p1++]=ch;
			s1[p1++]=ch;
			i=j;
		}else s1[p1++]=ch;
	}
	s1[p1]='\0';
	memcpy(s,s1,sizeof(s1));
}

void delTwo(char *s)
{
	int len=strlen(s);
	bool lastTwo=false;
	int p1=0;
	for(int i=0;i<len;i++)
	{
		char ch=s[i];
		if(i+1<len && s[i]==s[i+1])
		{
			if(lastTwo)
			{
				s1[p1++]=ch;
				i++;
				lastTwo=false;
			}else
			{
				s1[p1++]=ch;
				s1[p1++]=ch;
				i++;
				lastTwo=true;
			}
		}else
		{
			s1[p1++]=ch;
			lastTwo=false;
		}
	}
	s1[p1]='\0';
	memcpy(s,s1,sizeof(s1));
}

void deal()
{
	scanf("%s",s);
	delThree(s);
	delTwo(s);
	printf("%s\n",s);
}

int main()
{
#ifndef ONLINE_JUDGE
  freopen("in1.txt","r",stdin);
#endif
	deal();
  return 0;
}

/*
*/
