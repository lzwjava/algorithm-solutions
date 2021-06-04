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

#define Q_LEN 10000
#define STATE_N 100

typedef struct 
{
    Pos queue[Q_LEN];
    int qfront;
    int qend;
} Queue;

void printQueue(Queue *q) {
    int i = q->qfront;
    while(i!=q->qend){
        printf("%d %d %d\n", q->queue[i].x, q->queue[i].y, q->queue[i].t);
        i = (i + 1) % Q_LEN;
    }
}

void enqueue(Queue *q, Pos p) {
    q->queue[q->qend] = p;
    q->qend = (q->qend + 1) % Q_LEN;
    // printf("%d %d\n", q->qfront, q->qend);
}

Pos dequeue(Queue *q) {
    assert(q->qfront != q->qend);
    // printf("%d %d\n", q->qfront, q->qend);
    Pos p = q->queue[q->qfront];
    q->qfront = (q->qfront + 1) % Q_LEN;
    return p;
}

void initQueue(Queue *q){
    memset(q->queue, 0, sizeof(int) * Q_LEN);
    q->qfront = 0;
    q->qend = 0;
}

int queueLength(Queue q){
    if (q.qfront <= q.qend) {
        return q.qend - q.qfront;
    } else {
        return q.qend + Q_LEN - q.qfront;
    }
}

bool empty(Queue q) {
    return q.qfront == q.qend;
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

int computeState(int R, int C, char (*states)[R][C], int fn, 
                 Pos fire[], int n, int *maxState, bool *spread) { 
    if (!(*spread)) {
        return *maxState;
    }
    if (*maxState >= STATE_N -1) {
        return *maxState;
    }
    if (n >= STATE_N - 1) {
        return *maxState;
    }
    if (n <= *maxState) {
        return n;
    }
    assert(n == *maxState + 1); 

    memcpy(states[n], states[n-1], sizeof(char[R][C]));
    int k, l;
    Queue fireQ = {};
    initQueue(&fireQ);

    int vis[R][C];
    memset(vis, 0, R * C * sizeof(int));

    for (k = 0; k < fn; k++)
    {
        enqueue(&fireQ, fire[k]);
        vis[fire[k].x][fire[k].y] = 1;        
    }
    bool spread1 = false;
    while (!empty(fireQ))
    {
        Pos firePos = dequeue(&fireQ);
        // printf("dequeue\n");
        int d;
        for (d = 0; d < 4; d++)
        {             
            int nx = firePos.x + Dir[d][0];
            int ny = firePos.y + Dir[d][1];
            if (nx < 0 || nx >=R || ny < 0 || ny>=C) {
                continue;
            }
            if (vis[nx][ny] == 1) {
                continue;
            }
            vis[nx][ny] = 1;

            if (states[n-1][nx][ny] == 'J' || states[n-1][nx][ny] == '.') {
                states[n][nx][ny] = 'F';
                // printf("setf\n");
                // print(R, C, states[n]);
                spread1 = true;
            }
            if (states[n-1][nx][ny]=='F') {
                Pos np;
                np.x = nx;
                np.y = ny;
                // printf("enqueue\n");
                enqueue(&fireQ, np);
            }
        }
    }
    if (!spread1) {
        *spread = false;
    } 
    // printf("n:%d\n", n);
    // print(R, C, states[n]);
    *maxState = n;
    return n;
}

int main() {
    int n;
#ifndef ONLINE_JUDGE    
    freopen("2.in", "r", stdin);
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
        Pos fires[100];
        int fireN = 0;

        for (j = 0; j < R;j++) {
            int k;
            for (k = 0; k < C; k++)
            {
                if (grid[j][k] == 'J'){
                    jx = j;
                    jy = k;
                }
                if (grid[j][k] == 'F') {
                    Pos p;
                    p.x = j;
                    p.y = k;
                    fires[fireN] = p;
                    fireN++;
                }
            } 
        }

        char (*states)[R][C] = malloc(STATE_N * sizeof(char[R][C]));
        memcpy(states[0], grid, sizeof(char[R][C]));

        int maxState = 0;
        bool spread = true;           


        Queue q = {};

        initQueue(&q);

        int vis[R][C];
        memset(vis, 0, R * C * sizeof(int));
        Pos root;
        root.x = jx;
        root.y = jy;
        root.t = 0;
        vis[jx][jy] = 1;
        enqueue(&q, root);
        // printf("%d %d\n", q.qfront, q.qend);
        // int stateI = 1;
        // for (;stateI < 4;stateI++) {
        //     int n = computeState(R, C, states, fireN, fires, stateI, &maxState, &spread);

        //     // print(R, C, states[n]);
        //     // printf("\n");
        //     // printf("%d %d\n", R, C);
        //     // printf("%d\n", n);
        // }

        while (!empty(q))
        {
            Pos p = dequeue(&q);
            // printf("dequeue\n");
            /* printf("%d\n", queueLength()); */
            if (p.x == 0 || p.x == R - 1 || p.y == 0 || p.y == C - 1)
            {
                /*                 border
                printf("%d %d\n", p.x, p.y);
                printf("border\n"); */
                minTime = p.t;
                break;
            }
            int d;
            for (d = 0; d < 4; d++)
            {               
                int nx = p.x + Dir[d][0];
                int ny = p.y + Dir[d][1];
                if (nx < 0 || nx >=R || ny < 0 || ny>=C) {
                    continue;
                }
                if (vis[nx][ny]) {
                    // printf("vis continue\n");
                    continue;
                }
                if (states[n][nx][ny] == '#' || states[n][nx][ny] == 'F')
                {
                    // printf("state continue\n");
                    continue;
                }                
                int stateI = p.t + 1;
                int n = computeState(R, C, states,fireN, fires, stateI, &maxState, &spread);
                // print(R, C, states[stateI]);
                // printf("n:%d\n", n);
                vis[nx][ny] = 1;
                Pos np;
                np.x = nx;
                np.y = ny;
                np.t = p.t + 1;
                // printf("enqueue\n");
                enqueue(&q, np);
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
