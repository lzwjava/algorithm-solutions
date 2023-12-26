# https://github.com/ibrahimhabibeg/codeforces/blob/main/1902/E/sol.cpp

import sys
from os import path


class TrieNode:
    def __init__(self):
        self.children = [None] * 26
        self.cnt = 0


def create_node():
    return TrieNode()


def main():
    if path.exists('in.txt'):
        sys.stdin = open('in.txt', 'r')

    n = int(input())
    s = [input() for _ in range(n)]
    sm = 0
    root = create_node()

    for i in range(n):
        sm += len(s[i])
        r = root
        for j in range(len(s[i])):
            v = ord(s[i][j]) - ord('a')
            if not r.children[v]:
                r.children[v] = create_node()
            r = r.children[v]
            r.cnt += 1

    res = 2 * n * sm

    for i in range(n):
        r = root
        for j in range(len(s[i]) - 1, -1, -1):
            ord_a = ord(s[i][j]) - ord('a')
            if not r.children[ord_a]:
                break
            r = r.children[ord_a]
            res -= 2 * r.cnt

    print(res)


if __name__ == "__main__":
    main()
