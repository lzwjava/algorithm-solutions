#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include<string.h>
#include<assert.h>
#include<limits.h>

#define MAX_NODE 50000

struct TreeNode {
    char *v;
    struct TreeNode *left;
    struct TreeNode *right;
};

typedef struct TreeNode Node;

void buildTree(char *s, Node *root) {
    // printf("buildTree\n");
    int len = strlen(s);
    char *lptr;
    lptr = strchr(s, '(');
    if (lptr !=NULL) {
        int vlen = (lptr - 1) - s + 1;
        char *v = malloc(vlen);
        strncpy(v, s, vlen);
        root->v = v;
        char *mptr = lptr+1;
        int depth = 0;
        for (; mptr - s < len; mptr++)
        {
            if (*mptr=='('){
                // printf("depth++\n");
                depth++;
            } else if (*mptr==')'){
                // printf("depth++\n");
                depth--;
            } else if (*mptr == ',') {
                if (depth == 0) {
                    break;
                }
            }
        }
        assert(mptr - s < len);

        char *rptr = strrchr(s, ')');
        assert(rptr != NULL);

        int llen = (mptr - 1) - (lptr + 1) + 1; 

        char *lstr = malloc(llen);
        strncpy(lstr, lptr+1, llen);

        int rlen = (rptr - 1) - (mptr + 1) + 1;
        char *rstr = malloc(rlen);
        strncpy(rstr, mptr + 1, rlen);

        assert(llen > 0);
        assert(rlen > 0);
        root->left = (Node *)malloc(sizeof(Node));
        buildTree(lstr, root->left);

        root->right = (Node *)malloc(sizeof(Node));
        buildTree(rstr, root->right);        
    }
    else
    {
        root->v = s;    
    }
}

void traverse(Node *root) {
    printf("%s,", root->v);    
    if (root->left) {
        traverse(root->left);
    }
    if (root->right) {
        traverse(root->right);
    }
}

int main() {
#ifndef ONLINE_JUDGE    
    freopen("1.in", "r", stdin);    
#endif
    int c;
    scanf("%d", &c);
    int i;
    for (i = 0; i < c;i++){
        char s[MAX_NODE * 5];
        scanf("%s", s);
        // printf("%s\n", s);
        Node root;
        buildTree(s, &root);
        traverse(&root);
    }
    return 0;
}