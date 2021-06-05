#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include<string.h>
#include<assert.h>
#include<limits.h>

int main() {
#ifndef ONLINE_JUDGE    
    freopen("1.in", "r", stdin);    
#endif
    for (;;){        
        char *content = NULL;
        size_t len = 0;
        int bytes;
        bytes = getdelim(&content, &len, '\0', stdin);
        if (bytes == EOF) {
            break;
        }
        int quotes = 0;
        int i;
        char *nc = malloc(sizeof(char) * len * 2);
        int n = 0;
        for (i = 0; i < len; i++)
        {
            if (content[i] == '"') {
                quotes++;
                if (quotes % 2 ==1) {
                    nc[n++] = '`';
                    nc[n++] = '`';
                } else {
                    nc[n++] = '\'';
                    nc[n++] = '\'';
                }
            } else {
                nc[n++] = content[i];
            }
        }
        nc[n] = '\0';
        printf("%s", nc);
    }
    return 0;
}
