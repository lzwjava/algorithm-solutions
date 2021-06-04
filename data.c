#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include <string.h>

int main(){
    FILE *f;
    f = fopen("2.in", "w+");
    fprintf(f, "%d\n", 1);
    int N = 200;
    fprintf(f, "%d %d\n", N, N);
    int jx = N/2, jy = N/2;
    int fx = N/2, fy = N/2+1;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N;j++){
            fprintf(f, ".");
            if (jx == i && jy==j){
                fprintf(f, "J");
            }
            if (fx == i && fy==j){
                fprintf(f, "F");
            }            
        }
        fprintf(f, "\n");
    }
    return 0;
}