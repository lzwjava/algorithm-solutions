#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include <string.h>

const int MAX_NUM = 100000000;

int minTime;

int Dir[4][2] = {
    {-1, 0}, 
    {1, 0}, 
    {0, 1}, 
    {0, -1}
};

void print(int R, int C, char grid[][C]){
    int i, j;
    for (i = 0; i < R; i++)
    {
        for (j = 0; j < C;j++){
            printf("%c", grid[i][j]);
        }
        printf("\n");
    }
}

void printTrace(int R, int C, int trace[][C]) {
    int i, j;
    for (i = 0; i < R; i++)
    {
        for (j = 0; j < C;j++){
            printf("%d ", trace[i][j]);
        }
        printf("\n");
    }    
}

int dfsN;

void dfs(int R, int C,char grid[][C], int trace[][C], int jx, int jy, int time) {
    printf("R:%d C:%d jx:%d jy:%d time:%d\n", R, C, jx, jy, time);    
    print(R, C, grid);
    printTrace(R, C, trace);
    // dfsN++;
    if (time >= minTime) {
        return;
    }
    if (trace[jx][jy] != -1) {
        if (trace[jx][jy] <= time) {
            return;
        }
        trace[jx][jy] = time;
    } else
    {
        trace[jx][jy] = time;
    }
    if (jx == 0 || jx == R - 1 || jy == 0 || jy == C - 1)
    {
        if (minTime > time) {
            minTime = time;
        }
        return;
    }

    int i, j;
    int fn = 0;
    for (i = 0; i < R; i++)
    {
        for (j =0;j<C;j++){
            if(grid[i][j] == 'F'){
                fn++;
            }
        }
    }
    int fx[fn],fy[fn];
    int k = 0;
    for (i = 0; i < R; i++)
    {
        for (j =0;j<C;j++){
            if(grid[i][j] == 'F'){
                fx[k] = i;
                fy[k] = j;
                k++;
            }
        }
    }      

    for (k = 0; k < fn;k++) {
        int z;
        for (z = 0; z < 4; z++)
        {
            int nfx = fx[k] + Dir[z][0];
            int nfy = fy[k] + Dir[z][1];
            if (nfx < 0 || nfx >= R || nfy < 0 || nfy >= C){
                   continue;
            } 
            if (grid[nfx][nfy] == '#' || grid[nfx][nfy] == 'F') {
                continue;
            }
            grid[nfx][nfy] = 'F';            
        }
    }

    int d;
    for (d = 0; d < 4; d++)
    {    
        char (*ngrid)[C] = malloc(sizeof(char[R][C]));
        memcpy(ngrid, grid, sizeof(char[R][C]));  
        
        int nx = jx + Dir[d][0];
        int ny = jy + Dir[d][1];
        if (nx < 0 || nx >= R || ny < 0 || ny >= C){
            continue;
        }
        if (ngrid[nx][ny] == '#' || ngrid[nx][ny] == 'F') {
                continue;
        }       
        if (ngrid[jx][jy] == 'F')
        {
            ngrid[nx][ny] = 'J';
        } else {
            ngrid[jx][jy] = '.';
            ngrid[nx][ny] = 'J';
        }
        /* print(R, C, ngrid);
          exit(0);
        */
        dfs(R, C, ngrid, trace, nx, ny, time + 1);
        free(ngrid);
    }
}

int main() {
    int n;
#ifndef ONLINE_JUDGE    
    freopen("1.in", "r", stdin);
#endif
    scanf("%d", &n);
    int i, j;
    for (i=0;i<n;i++){
        minTime = MAX_NUM;
        int R, C;
        scanf("%d%d", &R, &C);        
        char (*grid)[C]=malloc(sizeof(char[R][C]));
        int jx = -1 ,jy;
        for(j=0;j<R;j++){    
            scanf("%s", grid[j]);
            if(jx == -1){
                int k;
                for (k = 0; k < C; k++)
                {
                    if(grid[j][k] == 'J'){
                        jx = j;
                        jy = k;
                        break;
                    }
                }
            }
        }
        int trace[R][C];
        memset(trace, -1, R * C * sizeof(int));

        dfsN = 0;
        dfs(R, C, grid, trace, jx, jy, 0);
        if (minTime == MAX_NUM) {
            printf("IMPOSSIBLE\n");
        } else {
            printf("%d\n", minTime + 1);
        }
        free(grid);
    }
    return 0;
}
