#include<iostream>
#include<vector>
#include<string>
#include<set>
#include<stack>
#include<queue>
#include<map>
#include<algorithm>
#include<cmath>
#include<iomanip>
#include<cstring>
#include<sstream>
#include<cstdio>
#include<deque>
#include<functional>
using namespace std;

int x, y;
int aim[3][3],ans;
pair<int,int> area[3][3];//0 空白 1 红色 2 蓝色 3 白色  上方和前方

int dx[] = {1,-1,0,0};
int dy[] = {0,0,1,-1};

int compare(){//统计有多少个不同
	int amount = 0;
	for (int i = 0; i < 3; i++){
		for (int j = 0; j < 3; j++){
			if (area[i][j].first != aim[i][j]) amount++;
		}
	}
	return amount;
}

void Move(int nx,int ny,int type){
	int curx=nx+dx[type], cury=ny+dy[type];
	if (type == 0 || type == 1){//上下移动
		swap(area[nx][ny].first,area[nx][ny].second);
	}
	else{//左右移动
		int color = 1 ^ 2 ^ 3 ^ area[nx][ny].first^area[nx][ny].second;
		area[nx][ny].first = color;
	}
	swap(area[curx][cury],area[nx][ny]);
}

void dfs(int curx,int cury,int depth){
	int amount2 = compare();
	if (amount2 == 0){
		ans = min(ans,depth);
		return;
	}
	if (amount2 + depth > ans){
		return;
	}
	int tx, ty;
	for (int i = 0; i < 3; i++){
		for (int j = 0; j < 3; j++){
			if (!area[i][j].first){
				tx = i; ty = j;
				break;
			}
		}
	}
	for (int i = 0; i < 4; i++){
		int next_x = tx + dx[i];
		int next_y = ty + dy[i];
		if (next_x >= 0 && next_x < 3 && next_y >= 0
			&& next_y < 3 && (next_x != curx||next_y != cury)){
			Move(next_x, next_y, i ^ 1);
			dfs(tx, ty, depth + 1);
			Move(tx, ty, i);
		}
	}
}

int main(){
	freopen("1.in", "r", stdin);
	while (cin >> y >> x)
	{
		if (x == 0 && y == 0) break;
		x--; y--;
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				char t;
				cin >> t;
				if (t == 'E') aim[i][j] = 0;
				else if (t == 'R') aim[i][j] = 1;
				else if (t == 'B') aim[i][j] = 2;
				else aim[i][j] = 3;
				if (i == x&&j == y) area[i][j] = make_pair(0,0);
				else area[i][j] = make_pair(3,1);
			}
		}
		ans = 31;
		dfs(-1, -1, 0);
		if (ans > 30) ans = -1;
		cout << ans << endl;
	}
	return 0;
}

