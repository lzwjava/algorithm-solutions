#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include <string.h>
#include <assert.h>

const int MAX_NUM = 100000000;

int minTime;

int Dir[4][2] = {
    {-1, 0}, 
    {1, 0}, 
    {0, 1}, 
    {0, -1}
};

typedef struct {
    int x;
    int y;
    int t;
} Pos;

const int Q_LEN = 1000;
Pos queue[Q_LEN];
int qfront = 0 , qend = 0;

void printQueue() {
    int i = qfront;
    while(i!=qend){
        printf("%d %d %d\n", queue[i].x, queue[i].y, queue[i].t);
        i = (i + 1) % Q_LEN;
    }
}

void enqueue(Pos p) {
    queue[qend] = p;
    qend = (qend + 1) % Q_LEN;
    // printf("%d %d\n", qfront, qend);
}

Pos dequeue() {
    assert(qfront != qend);
    Pos p = queue[qfront];
    qfront = (qfront + 1) % Q_LEN;
    // printf("%d %d\n", qfront, qend);    
    return p;
}

void initQueue(){
    qfront = 0;
    qend = 0;
}

bool empty() {
    return qfront == qend;
}

void print(int R, int C, char grid[][C])
{
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
        
        for(j=0;j<R;j++){    
            scanf("%s", grid[j]);       
        }

        int jx ,jy;

        for (j = 0; j < R;j++) {
            int k;
            for (k = 0; k < C; k++)
            {
                if (grid[j][k] == 'J'){
                    jx = j;
                    jy = j;
                    break;
                }
            }
            if (k < C) {
                break;
            }
        }

        int stateN = 1000;
        char (*states)[R][C] = malloc(stateN * sizeof(char[R][C]));
        memcpy(states[0], grid, sizeof(char[R][C]));
        int k, l;
        // print(R, C, states[0]);
        for (j = 1; j < stateN; j++)
        {
            memcpy(states[j], states[j-1], sizeof(char[R][C]));
            bool spread = false;
            for (k = 0; k < R; k++)
            {
                for (l = 0; l < C;l++){
                    if (states[j-1][k][l] == 'F') {
                        int d;
                        for (d = 0; d < 4; d++)
                        {             
                            int nx = k + Dir[d][0];
                            int ny = l + Dir[d][1];
                            if (states[j-1][nx][ny] == 'J' || states[j-1][nx][ny] == '.') {
                                states[j][nx][ny] = 'F';
                                spread = true;
                            }
                        }
                    }
                }
            }
            if (!spread) {
                break;                
            }
            // print(R, C, states[j]);
        }
        int maxState = j - 1;

        initQueue();

        int vis[R][C];
        memset(vis, 0, R * C * sizeof(int));
        Pos root;
        root.x = jx;
        root.y = jy;
        root.t = 0;
        vis[jx][jy] = 1;
        enqueue(root);        
        // printf("%d %d\n", jx, jy);
        while (!empty())
        {
            Pos p = dequeue();   
            // printf("%d %d\n", p.x, p.y);         
            if (p.x == 0 || p.x == R-1 || p.y==0 || p.y == C-1) {
                // border
                // printf("%d %d\n", p.x, p.y);
                // printf("border\n");
                minTime = p.t;
                break;
            }
            int d;
            for (d = 0; d < 4; d++)
            {               
                int nx = p.x + Dir[d][0];
                int ny = p.y + Dir[d][1];
                int stateI = p.t + 1;
                if (stateI > maxState) {
                    stateI = maxState;
                }
                // print(R, C, states[stateI]);
                // printf("%d %d\n", R, C);
                if (states[stateI][nx][ny] == '#' || states[stateI][nx][ny] == 'F')
                {
                    // printf("state continue\n");
                    continue;
                }
                if (vis[nx][ny]) {
                    // printf("vis continue\n");
                    continue;
                }
                vis[nx][ny] = 1;
                Pos np;
                np.x = nx;
                np.y = ny;
                np.t = p.t + 1;
                // printf("enqueue\n");
                enqueue(np);
            }
        }
        if (minTime == MAX_NUM) {
            printf("IMPOSSIBLE\n");
        } else {
            printf("%d\n", minTime + 1);
        }
        free(grid);
    }
    return 0;
}
